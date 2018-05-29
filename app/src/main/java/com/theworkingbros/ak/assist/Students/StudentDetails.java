package com.theworkingbros.ak.assist.Students;

public class StudentDetails {
    private String fullname;
    private String email;
    private String department;

    public StudentDetails(String fullname, String email, String department) {
        this.fullname = fullname;
        this.email = email;
        this.department = department;
    }

    public StudentDetails() {
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
