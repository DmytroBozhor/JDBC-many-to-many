package org.example.repository;

import org.example.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

    List<Actor> findActorsByNameStartingWith(String name);

    List<Actor> findActorsByAgeBetween(int age, int age2);

    @Query("from Actor")
    List<Actor> customQuery();
}
