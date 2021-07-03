package com.khurshed.engineeringuniversityadmissionmanagement;

public class Model2 {
    String id, universityname,facultyname,departments;

    public Model2(String id, String universityname, String facultyname, String departments) {
        this.id = id;
        this.universityname = universityname;
        this.facultyname = facultyname;
        this.departments = departments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
