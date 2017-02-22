package com.meytal.crm.backend.dal.pojo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by gurt on 16-Feb-17.
 */
@XmlRootElement
public class Person {
    int id;
    String firstName;
    String lastName;
    String company;
    String phone;
    String email;
    String fields;
    String courses;
    int imageId;
    String type;
    String tz;

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("First Name: ").append(getFirstName());
        s.append("\n Last Name: ").append(getLastName());
        s.append("\n Teudat Zehut: ").append(getTz());
        s.append("\n Company: ").append(getCompany());
        s.append("\n Phone: ").append(getPhone());
        s.append("\n Email: ").append(getEmail());
        s.append("\n Fields: ").append(getFields());
        s.append("\n Courses: ").append(getCourses());
        s.append("\n Type: ").append(getType());
        return s.toString();
    }
}
