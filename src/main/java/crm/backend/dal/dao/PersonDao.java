package crm.backend.dal.dao;

import crm.backend.dal.pojo.Person;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by gurt on 16-Feb-17.
 */
public interface PersonDao {
    void setDataSource(DataSource dataSource);

    void create(String firstName, String lastName);

    Person getPerson(String firstName, String lastName);

    void delete(String name);

    List<Person> getAll();

    void update(String firstName, String lastName, String column, String value);

    List<Person> getAllByCompany(String companyName);

}
