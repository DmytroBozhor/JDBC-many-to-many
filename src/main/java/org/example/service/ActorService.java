package org.example.service;

import org.example.model.Actor;
import org.example.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActorService {

    private final ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public Actor get(int id) {
        return actorRepository.getReferenceById(id);
    }

    public void save(Actor actor) {
        actorRepository.save(actor);
    }

    public void update(int id, Actor updatedActor) {
        updatedActor.setId(id);
        actorRepository.save(updatedActor);
    }

    public void delete(Actor actor) {
        actorRepository.delete(actor);
    }

    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    public List<Actor> findActorsByNameStartingWith(String name){
        return actorRepository.findActorsByNameStartingWith(name);
    }

    public List<Actor> findActorsByAgeBetween(int age, int age2){
        return actorRepository.findActorsByAgeBetween(age, age2);
    }

    public List<Actor> customQuery(){
        return actorRepository.customQuery();
    }
}
