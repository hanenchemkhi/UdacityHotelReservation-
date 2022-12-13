package model;

import java.util.regex.Pattern;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;


    public Customer(String firstName, String lastName, String email){

        Pattern emailPattern = Pattern.compile("^(.+)@(.+).(.+)$");
        if (!emailPattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Enter a Valid Email example@gmail.com");
        }
        this.email = email;
        this.firstName = firstName;
        this.lastName = firstName;
    }


    public final String getEmail(){
        return email;
    }
    public final String getFirstName(){
        return firstName;
    }
    public final String getLastName(){
        return lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Customer customer)) return false;

        return email.equals(customer.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public String toString(){
        return "First Name: "+ firstName+ " Last Name: "+ lastName+ " Email: "+email;
    }
}
