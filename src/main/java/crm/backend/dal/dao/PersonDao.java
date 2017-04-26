package crm.backend.dal.dao;

import crm.backend.dal.pojo.Person;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by gurt on 16-Feb-17.
 */
public interface PersonDao {
    void setDataSource(DataSource dataSource);

    void create(String firstName, String lastName, String client, String phone, String email, String tz, boolean hasImage);

    Person getPerson(String personId);

    void delete(int personId);

    List<Person> getAll();

    List<Person> getAllByClient(String clientName);

    void update(Person person);
}
