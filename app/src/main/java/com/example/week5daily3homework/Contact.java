package com.example.week5daily3homework;

import java.util.List;

public class Contact {
    private String name;
    private List<String> numberList;
    private String email;

    public Contact(String name, List<String> numberList, String email) {
        this.name = name;
        this.numberList = numberList;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getNumberList() {
        return numberList;
    }

    public void setNumberList(List<String> numberList) {
        this.numberList = numberList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", numberList=" + numberList +
                ", email='" + email + '\'' +
                '}';
    }
}
