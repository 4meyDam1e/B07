package com.example.health.Classes;

import java.util.Map;

public class DoctorInfo extends UserInfo {
    private String proficiency;

    public DoctorInfo() {
        super();
        proficiency = "";
    }

    public DoctorInfo(String email, String firstName, String lastName,
                      String gender, String password) {
        super(email, firstName, lastName, gender, password);
        proficiency = "";
    }

    public String getProficiency() { return proficiency; }

    public void setProficiency(String proficiency) {
        this.proficiency = proficiency;
    }
}
