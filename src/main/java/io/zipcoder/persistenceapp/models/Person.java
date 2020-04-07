package io.zipcoder.persistenceapp.models;
import javax.persistence.*;
import java.sql.Date;

public class Person {

    Integer ID;
    String FIRST_NAME;
    String LAST_NAME;
    String MOBILE;
    Date BIRTHDAY;
    int HOME_ID;

    public Person(){

    }
    public Person(int ID, String FIRST_NAME, String LAST_NAME, String MOBILE, Date BIRTHDAY, int HOME_ID) {
        this.ID = ID;
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.MOBILE = MOBILE;
        this.BIRTHDAY = BIRTHDAY;
        this.HOME_ID = HOME_ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public void setFIRST_NAME(String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }

    public String getMOBILE() {
        return MOBILE;
    }

    public void setMOBILE(String MOBILE) {
        this.MOBILE = MOBILE;
    }
    public Date getBIRTHDAY() {
        return BIRTHDAY;
    }

    public void setBIRTHDAY(Date BIRTHDAY) {
        this.BIRTHDAY = BIRTHDAY;
    }

    public int getHOME_ID() {
        return HOME_ID;
    }

    public void setHOME_ID(int HOME_ID) {
        this.HOME_ID = HOME_ID;
    }
}
