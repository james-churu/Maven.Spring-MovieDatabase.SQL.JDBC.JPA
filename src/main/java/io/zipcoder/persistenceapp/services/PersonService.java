package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.controllers.PersonMapper;
import io.zipcoder.persistenceapp.models.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonService {

    DriverManagerDataSource data = new DriverManagerDataSource("jdbc:h2:mem:testdb", "sa","");
    JdbcTemplate jdbcTemplate = new JdbcTemplate(data);

    public void createPerson(Person person){
        jdbcTemplate.execute("INSERT INTO PERSON(id, first_name, last_name, birthday, mobile, home_id)" +
                "VALUES ("+ person.getID() + ", '" + person.getFIRST_NAME()+ "','" + person.getLAST_NAME() + "','" +
                person.getBIRTHDAY() + "', '" + person.getMOBILE() + "', " + person.getHOME_ID() + ");");
    }

    //******************************************************************************************************************

    public Person findById(Integer id){
        return (Person) jdbcTemplate.queryForObject("SELECT * FROM PERSON WHERE ID = "+ id+ ";", new Object[]{id}, new PersonMapper());
    }

    //******************************************************************************************************************

    public List<Person> findAll(){
        return jdbcTemplate.query("SELECT * FROM PERSON", new PersonMapper());
    }

    //******************************************************************************************************************

    public Person updatePerson(Person person){
        jdbcTemplate.execute("UPDATE PERSON SET first_name = '" + person.getFIRST_NAME() + "', last_name = '" + person.getLAST_NAME()
                + "', mobile = '" + person.getMOBILE() + "', birthday = '" + person.getBIRTHDAY() + "', home_id = " + person.getHOME_ID()
                + "WHERE id = " + person.getID());
        return findById(person.getID());
    }

    //******************************************************************************************************************

    public boolean deletePerson(Integer id){
        if (jdbcTemplate.update("DELETE FROM PERSON WHERE id = " + id) == 1)
            return true;
        return false;
    }

    //******************************************************************************************************************

    public boolean deleteListPerson(List<Person> people){
        boolean[] checks = new boolean[people.size()];
        int count = 0;
        for (Person p : people){
            int tempId = p.getID();
            checks[count++] = deletePerson(tempId);
        }
        for (boolean b : checks) {
            if (!b)
                return false;
        }
        return true;
    }

    //******************************************************************************************************************

    public List<Person> findAllByFirst(String first){
        return jdbcTemplate.query("SELECT * FROM PERSON WHERE FIRST_NAME = " + first, new PersonMapper());
    }

    //******************************************************************************************************************

    public List<Person> findAllByLast(String last){
        return jdbcTemplate.query("SELECT * FROM PERSON WHERE LAST_NAME = " + last, new PersonMapper());
    }

    //******************************************************************************************************************

    public List<Person> findAllByBirthday(String birthday){
        return jdbcTemplate.query("SELECT * FROM PERSON WHERE BIRTHDAY " + birthday, new PersonMapper());
    }

    public Map<String, ArrayList<Person>> getSurnameMap(){
        Map<String, ArrayList<Person>> map = new HashMap<>();
        findAll().forEach(person -> {
            if (!map.containsKey(person.getLAST_NAME()))
                map.put(person.getLAST_NAME(), new ArrayList<>(Arrays.asList(person)));
            else
                map.get(person.getLAST_NAME()).add(person);
        });
        return map;
    }

    //******************************************************************************************************************

    public Map<String, Integer> getFirstNameCount(){
        Map<String, Integer> map = new HashMap<>();
        findAll().forEach(person -> map.compute(person.getFIRST_NAME(), (name, count) -> (name == null) ? 1 : count++));
        return map;
    }

    //******************************************************************************************************************

    public List<Person> findAllByMobile(String mobile){
        return jdbcTemplate.query("SELECT * FROM PERSON WHERE MOBILE = " + mobile, new PersonMapper());
    }


}
