package com.example.springboot_project.controller;

import com.example.springboot_project.configuration.ValidatorExmpl;
import com.example.springboot_project.dto.Person_dto;
import com.example.springboot_project.model.Person;
import com.example.springboot_project.service.Servicelayer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Обработчик запросов когда какое то прилоежине делает запрос запрос прийдет в первую очередь на контроллер (фильтр)
@RestController
@RequestMapping("/api")
public class Person_Controller {
    private final Servicelayer servicelayer;
    private final ValidatorExmpl validatorExmpl;

    @Autowired
    public Person_Controller(Servicelayer servicelayer, ValidatorExmpl validatorExmpl) {
        this.servicelayer = servicelayer;
        this.validatorExmpl = validatorExmpl;
    }

    @PostMapping("/save")
    public Person createPerson(@Valid @RequestBody Person_dto person_dto, BindingResult bindingResult) {
        validatorExmpl.validate(person_dto, bindingResult);
        if (!bindingResult.hasErrors()) {
            return servicelayer.save(person_dto);
        } else {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            StringBuilder stringBuilder = new StringBuilder();
            for (FieldError fieldError : fieldErrors) {
                stringBuilder.append(fieldError.getField());
                stringBuilder.append(fieldError.getDefaultMessage());
            }
        }
        return null;
    }


    @GetMapping("/get")
    public List<Person> personList() {
        return servicelayer.getAll();
    }
    @GetMapping("/get_by_Name/{name}")
    private List<Person> personList1(@PathVariable(name = "name") String name){
        return servicelayer.getName(name);
    }
    @GetMapping("/get_by_age")
    private List<Person> personList2(@RequestParam(name = "age") int age){
        return servicelayer.getbyage(age);
    }
    @DeleteMapping("/delete_by_name")
    private void delete(@RequestParam(name = "name") String name){
        servicelayer.deleteName(name);
    }
    @DeleteMapping("/delete_by_id")
    private void deleteid(@RequestParam(name = "id") int id){
        servicelayer.deleteById(id);
    }
    @PutMapping("/update_by_name")
    private void updateByName(@RequestParam(name = "name")String name,@RequestBody() Person_dto person_dto){
        servicelayer.update_By_Name(name,person_dto);
    }
    @GetMapping("/get_age_list/{age}")
    private List<Person> ageList(@PathVariable(name = "age") int age){
        return servicelayer.getAgeList(age);
    }
    @GetMapping("/get_age_avg")
    private Double ageavg(){
        return servicelayer.ageAvg();
    }
    @GetMapping("/get_name/{name}/{age}")
    private Person nameList(@PathVariable(name="name")String name,@PathVariable(name = "age")int age){
        return servicelayer.nameList(name,age);
    }


}
