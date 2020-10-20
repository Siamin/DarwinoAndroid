package com.ahmad.darwino.models;

import com.orm.SugarRecord;

public class Account extends SugarRecord<Account> {

    public int Id;
    public String FirstName = "";
    public String LastName = "";
    public String PhoneNumber = "";
    public String Email = "";
    public String Password = "";
    public String LoginToken = "";
    public String NotificationToken = "";

    public Account(){

    }

}
