package io.zipcoder.persistenceapp.jpa;

import io.zipcoder.persistenceapp.models.Person;

import java.util.*;

public interface JpaPersonService {

    public void createPerson(Person person);

    // Find a single person by ID
    public Person findById(Integer id);

    // FindAll
    public List<Person> showAll();

    // Update an existing Person in the database
    public void updatePerson(Integer id, Person person);

    // Remove a person from the database
    public Boolean deletePerson(Integer id);

    // Find all people with a mobile number
    public List<Person> findAllWithMobile(String mobile);

    // find all people with a particular first name
    public List<Person> findAllWithFirstName(String firstName);

    // find all people with a particular last name
    public List<Person> findAllWithLastName(String lastName);

    // find all people with a particular birthdate
    public List<Person> findAllWithBirthday(String bday);

    // Generate a map of surnames to lists of people with that surname
    public Map<String, ArrayList<Person>> getSurnameMap();

    // Generate a map of first names to the number of times they occur.
    public Map<String, Integer> getFirstNameMap();
}
