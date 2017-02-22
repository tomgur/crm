package com.meytal.crm.backend.dal.pojo;

import java.util.ArrayList;

/**
 * Created by gurt on 17-Feb-17.
 */
public class Company {
    int id;
    String name;
    String address;
    String phone;
    String fax;
    String email;
    String contactPerson;
    ArrayList invoices;
    ArrayList quotas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public ArrayList getInvoices() {
        return invoices;
    }

    public void setInvoices(ArrayList invoices) {
        this.invoices = invoices;
    }

    public ArrayList getQuotas() {
        return quotas;
    }

    public void setQuotas(ArrayList quotas) {
        this.quotas = quotas;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ID: " + getId())
                .append("\n Name: " + getName())
                .append("\n Phone: " + getPhone())
                .append("\n Fax: " + getFax())
                .append("\n Address: " + getAddress())
                .append("\n Email: " + getEmail())
                .append("\n Contact: " + getContactPerson())
                .append("\n Invoices: " + getInvoices())
                .append("\n Quotas: " + getQuotas());
        return stringBuilder.toString();
    }
}
