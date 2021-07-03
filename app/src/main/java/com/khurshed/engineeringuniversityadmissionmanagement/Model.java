package com.khurshed.engineeringuniversityadmissionmanagement;

public class Model {
    String id,universityName,facultyName,ssc,hsc,total,departments;

    public Model(String id, String universityName, String facultyName, String ssc, String hsc, String total, String departments) {
        this.id = id;
        this.universityName = universityName;
        this.facultyName = facultyName;
        this.ssc = ssc;
        this.hsc = hsc;
        this.total = total;
        this.departments = departments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getSsc() {
        return ssc;
    }

    public void setSsc(String ssc) {
        this.ssc = ssc;
    }

    public String getHsc() {
        return hsc;
    }

    public void setHsc(String hsc) {
        this.hsc = hsc;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDepartments() {
        return departments;
    }

    public void setDepartments(String departments) {
        this.departments = departments;
    }
}
