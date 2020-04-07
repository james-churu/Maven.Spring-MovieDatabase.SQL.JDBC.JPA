package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet set, int i) throws SQLException {
        Person person = new Person();
        person.setID( set.getInt("ID") );
        person.setFIRST_NAME( set.getString("FIRST_NAME") );
        person.setLAST_NAME( set.getString("LAST_NAME") );
        person.setMOBILE( set.getString( "MOBILE"));
        person.setBIRTHDAY( set.getDate("BIRTHDAY"));
        person.setHOME_ID( set.getInt( "HOME_ID"));

        return person;
    }
}
