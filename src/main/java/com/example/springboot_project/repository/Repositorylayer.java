package com.example.springboot_project.repository;

import com.example.springboot_project.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Repository
public interface Repositorylayer extends JpaRepository<Person, Integer> {
    List<Person> findByName(String name);

    List<Person> findByAge(int age);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from person where name =:name", nativeQuery = true)
    void deleteName(@Param(value = "name") String name);


    @Query(value = "select * from person where age>:age", nativeQuery = true)
    List<Person> getAgeList(@Param(value = "age") int age);

    @Query(value = "select avg(age) as avglist from person ", nativeQuery = true)
    Double ageAvg();

    @Query(value = "SELECT * from person where name=:name and age=:age",nativeQuery = true)
    Person nameList(@Param(value = "name") String name,@Param(value = "age") int age);
}
