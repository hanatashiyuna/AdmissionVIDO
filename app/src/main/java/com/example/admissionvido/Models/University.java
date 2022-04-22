package com.example.admissionvido.Models;

import java.io.Serializable;

public class University implements Serializable {
    int universityId;
    int img;
    String university;

    public University(String university) {
        this.university = university;
    }

    public University(int img, String university) {
        this.img = img;
        this.university = university;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
