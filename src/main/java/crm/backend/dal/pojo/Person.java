package crm.backend.dal.pojo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by gurt on 16-Feb-17.
 */
@XmlRootElement
public class Person {
    private int personId;
    private String firstName;
    private String lastName;
    private String client;
    private String phone;
    private String email;
    private String fields;
    private String courses;
    private int imageId;
    private String type;
    private String tz;

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
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

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
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
        return "First Name: " + getFirstName() +
                "\n Last Name: " + getLastName() +
                "\n Teudat Zehut: " + getTz() +
                "\n Client: " + getClient() +
                "\n Phone: " + getPhone() +
                "\n Email: " + getEmail() +
                "\n Fields: " + getFields() +
                "\n Courses: " + getCourses() +
                "\n Type: " + getType();
    }
}
