package io.zipcoder.persistenceapp.jdbc;
import io.zipcoder.persistenceapp.models.Person;

import org.h2.Driver;
import org.springframework.stereotype.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class JDBCPersonService {

    //**************************************************************************** ADD ************************

    public Person add(Person person) {

        ConnectionClass conClass = new ConnectionClass();
        Connection connection = conClass.getConnection();

        try{
            PreparedStatement prepstat = connection.prepareStatement("INSERT INTO PERSON ( 'ID' , 'FIRST_NAME' , 'LAST_NAME' , 'MOBILE' , 'BIRTHDAY' , 'HOME_ID') VALUES (? , ? , ? , ? , ? , ? ");

            prepstat.setInt( 1 , person.getID());
            prepstat.setString(2 , person.getFIRST_NAME() );
            prepstat.setString(3 , person.getLAST_NAME() );
            prepstat.setString(4 , person.getMOBILE() );
            prepstat.setDate(5 , person.getBIRTHDAY() );
            prepstat.setInt(6 , person.getHOME_ID() );

            int goodUpdate = prepstat.executeUpdate();

            if( goodUpdate == 1) {
                System.out.println("Successfully Added Car");
                return person;
            }

        }catch( SQLException e ){
            e.printStackTrace();
        }
        return null;
    }

    //*************************************************************************** Update *************************

    public Person update(Person person) {
        ConnectionClass conClass = new ConnectionClass();
        Connection connection = conClass.getConnection();

        try{
            PreparedStatement prepstat = connection.prepareStatement("UPDATE PERSON SET 'FIRST_NAME'=?, 'LAST_NAME'=?, 'MOBILE'=? , 'BIRTHDAY'=?, 'HOME_ID'=? WHERE 'ID'=?");
            prepstat.setString(1 , person.getFIRST_NAME() );
            prepstat.setString(2 , person.getLAST_NAME() );
            prepstat.setString(4 , person.getMOBILE() );
            prepstat.setDate(3 , person.getBIRTHDAY() );
            prepstat.setInt(5 , person.getHOME_ID() );

            prepstat.setInt( 6 , person.getID() );

            int goodUpdate = prepstat.executeUpdate();
            if( goodUpdate == 1) {
                System.out.println("Successfully  Updated Car");
                return person;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    //*************************************************************************** Remove *************************

    public void remove(int id) {
        ConnectionClass conClass = new ConnectionClass();
        Connection connection = conClass.getConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM PERSON WHERE ID =" + id);
            System.out.println("Delete successful");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //*************************************************************************** Remove List *************************

    public void remove(List<Integer> ids){
        for(Integer id : ids){
             remove(id);
        }
    }

    //*************************************************************************** Find By Name *************************

    public Person findByFirstName(String first_name){

        ConnectionClass conClass = new ConnectionClass();
        Connection connection = conClass.getConnection();

        try{
            PreparedStatement prepStat = connection.prepareStatement("select * from PERSON where FIRST_NAME = " + first_name);
            ResultSet result = prepStat.executeQuery();
            return getPersonFromResultSet( result );

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    //*************************************************************************** Find By ID *************************

    public Person findById(int id){

        ConnectionClass conClass = new ConnectionClass();
        Connection connection = conClass.getConnection();

        try{
            PreparedStatement prepStat = connection.prepareStatement("select * from PERSON where ID = " + id);
            ResultSet result = prepStat.executeQuery();
            return getPersonFromResultSet( result );

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    //*************************************************************************** Find All *************************

    public List findAll() {

        ConnectionClass conClass = new ConnectionClass();
        Connection connection = conClass.getConnection();

        ArrayList<Person> cars = new ArrayList<Person>();

        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM car");

            while(results.next()) {
                Person person = getPersonFromResultSet( results );
                cars.add( person );
            }
            return cars;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }






    public int getID() {
        return 0;
    }

    public Person getPersonFromResultSet(ResultSet set) throws SQLException{

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
