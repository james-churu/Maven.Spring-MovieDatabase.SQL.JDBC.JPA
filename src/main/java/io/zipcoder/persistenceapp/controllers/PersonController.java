package io.zipcoder.persistenceapp.controllers;


import io.zipcoder.persistenceapp.models.Person;
import io.zipcoder.persistenceapp.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/people")
    public ResponseEntity<Iterable<Person>> getAllPeople(){
        return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Integer id){
        return new ResponseEntity<>(personService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/people/reverselookup/{mobileNumber}")
    public ResponseEntity<Iterable<Person>> getAllByMobile(@PathVariable String mobile){
        return new ResponseEntity<>(personService.findAllByMobile(mobile), HttpStatus.OK);
    }

    @GetMapping("/people/firstname/{firstName}")
    public ResponseEntity<Iterable<Person>> getAllByFirstName(@PathVariable String firstName){
        return new ResponseEntity<>(personService.findAllByFirst(firstName), HttpStatus.OK);
    }

    @GetMapping("/people/lastname/{lastName}")
    public ResponseEntity<Iterable<Person>> getAllByLastName(@PathVariable String lastName){
        return new ResponseEntity<>(personService.findAllByLast(lastName), HttpStatus.OK);
    }

    @GetMapping("/people/birthday/{birthday}")
    public ResponseEntity<Iterable<Person>> getAllByBirthday(@PathVariable String birthday){
        return new ResponseEntity<>(personService.findAllByBirthday(birthday), HttpStatus.OK);
    }

    @GetMapping("/people/surname")
    public ResponseEntity<Map<String, ArrayList<Person>>> mapSurnames(){
        return new ResponseEntity<>(personService.getSurnameMap(), HttpStatus.OK);
    }

    @GetMapping("/people/firstname/stats")
    public ResponseEntity<Map<String, Integer>> firstNameOccurences(){
        return new ResponseEntity<>(personService.getFirstNameCount(), HttpStatus.OK);
    }

    @PostMapping("/people")
    public ResponseEntity<?> createPerson(@RequestBody Person person){
        personService.createPerson(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person){
        if ((Integer)person.getID() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(personService.updatePerson(person), HttpStatus.OK);
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity<Boolean> deletePerson(@PathVariable Integer id){
        if (personService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(personService.deletePerson(id), HttpStatus.OK);
    }

}