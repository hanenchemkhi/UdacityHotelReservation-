package model;

import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;

    public Customer(String firstName, String lastName, String email){
        this.setEmail(email);
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }


    public void setEmail(String email){
        this.email= email;
    }

    public void setFirstName(String firstName){
        this.firstName= firstName;
    }

    public void setLastName(String lastName){
        this.lastName= lastName;
    }



    public String getEmail(){
        return email;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    @Override
    public String toString(){
        return "First Name: "+ firstName+ " Last Name: "+ lastName+ " Email: "+email;
    }
}
