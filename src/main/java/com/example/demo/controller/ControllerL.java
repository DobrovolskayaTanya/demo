package com.example.demo.controller;

import com.example.demo.form.PersonForm;
import com.example.demo.model.Person;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping
public class ControllerL {
    private static List<Person> persons = new ArrayList<Person>();

    static {
        persons.add(new Person("Alla", "Grizuk", "Alibegova","Minsk", "225655","pr@mail.ru", new Date(2000,02,03),"7384489"));
        persons.add(new Person("Olga", "Zudova", "Biruzova","Minsk", "220102","lala@mail.ru", new Date(1999,03,03),"25698965"));
    }

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @GetMapping(value = {"/","/index"})
    public ModelAndView index(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        model.addAttribute("message", message);
        log.info("index was called");
        return modelAndView;
    }

    @GetMapping(value = {"/personList"})
    public ModelAndView personList(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("personList");
        model.addAttribute("Person", persons);
        log.info("personList was called");
        return modelAndView;

    }

    @GetMapping(value = {"/addPerson"})
    public ModelAndView showAddPersonPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("addPeson");
        PersonForm personForm = new PersonForm();
        model.addAttribute("PersonForm", personForm);
        /*
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("addPeson");
        */
       log.info("addList was called");
        return modelAndView;
    }

    @PostMapping(value = {"/addPerson"})
    public ModelAndView savePerson(Model model,@Valid @ModelAttribute("PersonForm") PersonForm personForm, Errors errors) {
        ModelAndView modelAndView = new ModelAndView();
        if (errors.hasErrors()) {
            modelAndView.setViewName("addPeson");
        }
        else {
            modelAndView.setViewName("personList");
            String firstName = personForm.getFirstName();
            String lastName = personForm.getLastName();
            String street = personForm.getStreet();
            String city = personForm.getCity();
            String zip = personForm.getZip();
            String email = personForm.getEmail();
            Date birthday = personForm.getBirthday();
            String phone = personForm.getPhone();
            //Person newPerson = new Person(firstName, lastName, street, city, zip, email, birthday, phone);
            Person newPerson = new Person (firstName, lastName, street, city, zip, email, birthday, phone);
            persons.add(newPerson);
            model.addAttribute("persons", persons);
            log.info("/addPerson - POST was called");
            return modelAndView;
        }
        return modelAndView;

    }


}
