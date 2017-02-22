package com.meytal.crm.backend.dal.dao;

import com.meytal.crm.backend.dal.pojo.Person;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by gurt on 16-Feb-17.
 */
public interface PersonDao {
    public void setDataSource(DataSource dataSource);

    public void create(String firstName, String lastName);

    public Person getPerson(String firstName, String lastName);

    public void delete(String name);

    public List<Person> getAll();

    public void update(String firstName, String lastName, String column, String value);

    public List<Person> getAllByCompany(String companyName);

}
