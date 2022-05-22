package com.sriharsha.lead.model;

import java.sql.Clob;
import java.util.ArrayList;
import java.util.Map;

public class JsonLeadData {
    private String name;
    private String email;
    private String phoneNumber;
    private Map<String, String> otherFields;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Map<String, String> getOtherFields() {
        return otherFields;
    }

    public void setOtherFields(Map<String, String> otherFields) {
        this.otherFields = otherFields;
    }
}
