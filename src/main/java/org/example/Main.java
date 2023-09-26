package org.example;

import org.example.model.Actor;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class Main {
    public static void main(String[] args) {

        Configuration configuration = new Configuration().addAnnotatedClass(Actor.class).addAnnotatedClass(Movie.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try (sessionFactory) {
            session.beginTransaction();

            Actor actor = session.get(Actor.class, 1);
            System.out.println(actor);
            List<Movie> movieList = actor.getMovies();
            movieList.forEach(System.out::println);

            Movie movie = session.get(Movie.class, 3);
            System.out.println(movie);
            List<Actor> actorList = movie.getActors();
            actorList.forEach(System.out::println);

            Actor actor1 = new Actor("Bon Trafinn", 31);
            Movie movie1 = new Movie("Cliff", 2021);

            actor1.setMovies(new ArrayList<>(Collections.singletonList(movie1)));
            movie1.setActors(new ArrayList<>(Collections.singletonList(actor1)));

            session.save(actor1);
            session.save(movie1);

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }
}