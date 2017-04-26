package crm.backend.dal.mappers;

import crm.backend.dal.pojo.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by gurt on 16-Feb-17.
 */
public class PersonMapper implements RowMapper<Person> {
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();
        person.setPersonId(resultSet.getInt("personId"));
        person.setFirstName(resultSet.getString("firstName"));
        person.setLastName(resultSet.getString("lastName"));
        person.setTz(resultSet.getString("tz"));
        person.setClient(resultSet.getString("client"));
        person.setPhone(resultSet.getString("phone"));
        person.setEmail(resultSet.getString("email"));
        person.setCourses(resultSet.getString("courses"));
        person.setFields(resultSet.getString("fields"));
        person.setType(resultSet.getString("type"));
        person.setHasImage(resultSet.getBoolean("hasImage"));
        return person;
    }
}
