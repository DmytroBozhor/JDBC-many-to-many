package org.example.dao;

import org.example.model.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ActorDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public ActorDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Actor get(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Actor.class, id);
    }

    public List<Actor> getALl() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select a from Actor a", Actor.class).getResultList();
    }

    public void save(Actor actor) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(actor);
    }

    public void update(int id, Actor actor) {
        Session session = sessionFactory.getCurrentSession();
        Actor actorToBeUpdated = session.get(Actor.class, id);

        actorToBeUpdated.setName(actor.getName());
        actorToBeUpdated.setAge(actor.getAge());
    }

    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Actor.class, id));
    }
}
