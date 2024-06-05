package com.example.springboot_project.service;

import com.example.springboot_project.dto.Person_dto;
import com.example.springboot_project.model.Person;
import com.example.springboot_project.repository.Repositorylayer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Servicelayer {
private final Repositorylayer repositorylayer;


    public Servicelayer(Repositorylayer repositorylayer) {
        this.repositorylayer = repositorylayer;
    }

    public Person save(Person_dto person_dto) {
        Person person = new Person(person_dto.getName(),person_dto.getAge(),"User");
        return repositorylayer.save(person);
    }

    public List<Person> getAll() {
        return repositorylayer.findAll();
    }

    public List<Person> getName(String name) {
        return repositorylayer.findByName(name);
    }

    public List<Person> getbyage(int age) {
        return repositorylayer.findByAge(age);
    }

    public void deleteName(String name) {
        repositorylayer.deleteName(name);
    }

    public void deleteById(int id) {
        repositorylayer.deleteById(id);
    }

    public void update_By_Name(String name, Person_dto personDto) {
        List<Person> list_Person = repositorylayer.findByName(name);
        for (Person list_person :list_Person      ) {
            list_person.setName(personDto.getName());
            list_person.setAge(personDto.getAge());
        repositorylayer.save(list_person);
        }
    }

    public List<Person> getAgeList(int age) {
        return repositorylayer.getAgeList(age);
    }

    public Double ageAvg() {
        return repositorylayer.ageAvg();
    }

    public Person nameList(String name, int age) {
        return repositorylayer.nameList(name,age);
    }
}
