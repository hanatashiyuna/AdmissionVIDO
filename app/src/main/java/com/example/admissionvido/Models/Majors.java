package com.example.admissionvido.Models;

import java.io.Serializable;

public class Majors implements Serializable {
    int majorsId;
    String majors;

    public Majors(String majors) {
        this.majors = majors;
    }

    public int getMajorsId() {
        return majorsId;
    }

    public void setMajorsId(int majorsId) {
        this.majorsId = majorsId;
    }

    public String getMajors() {
        return majors;
    }

    public void setMajors(String majors) {
        this.majors = majors;
    }
}
