package com.example.todo.data;

/**
 * Store contact data
 */
public class Contact {
    private String contactName = "";
    private String phoneNumber = "";
    private String email = "";

    public Contact() {
    }

    public Contact(String contactName, String phoneNumber, String email) {
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
