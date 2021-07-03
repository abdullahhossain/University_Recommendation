package com.khurshed.engineeringuniversityadmissionmanagement;

public class Model1 {
    String id, student_id, universityname,facultyname,departments,email;

    public Model1(String id, String student_id, String universityname, String facultyname, String departments, String email) {
        this.id = id;
        this.student_id = student_id;
        this.universityname = universityname;
        this.facultyname = facultyname;
        this.departments = departments;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getUniversityname() {
        return universityname;
    }

    public void setUniversityname(String universityname) {
        this.universityname = universityname;
    }

    public String getFacultyname() {
        return facultyname;
    }

    public void setFacultyname(String facultyname) {
        this.facultyname = facultyname;
    }

    public String getDepartments() {
        return departments;
    }

    public void setDepartments(String departments) {
        this.departments = departments;
    }
}
