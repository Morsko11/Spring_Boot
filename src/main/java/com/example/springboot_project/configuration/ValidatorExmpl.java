package com.example.springboot_project.configuration;

import com.example.springboot_project.dto.Person_dto;
import com.example.springboot_project.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

@Component
public class ValidatorExmpl implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {

        return Person_dto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person_dto person_dto = (Person_dto) target;
        if ( person_dto.getAge() < 12){
            errors.rejectValue("Age","Ошибка возраста","Возраст не подходит");
        }
        if (person_dto.getName().length() < 3){
            errors.rejectValue("Name","Ошибка имени","Короткое имя");
        }
    }




}
