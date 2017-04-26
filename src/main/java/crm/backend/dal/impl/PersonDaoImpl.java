package crm.backend.dal.impl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import crm.backend.dal.dao.PersonDao;
import crm.backend.dal.mappers.PersonMapper;
import crm.backend.dal.pojo.Person;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by gurt on 16-Feb-17.
 */
public class PersonDaoImpl implements PersonDao {
    private static Logger LOG = Logger.getLogger(PersonDaoImpl.class.getName());
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplateObject = new JdbcTemplate(dataSource);
        LOG.info("Person DAO is ready");
    }

    public void create(String firstName, String lastName, String client, String phone, String email, String tz, boolean hasImage) {
        String sql = "insert into person (firstName,lastName,client,phone,email,tz,hasImage) values (?,?,?,?,?,?,?)";
        jdbcTemplateObject.update(sql,firstName,lastName, client,phone,email,tz,hasImage);
        LOG.info("Added new person [" + firstName + ", " + lastName + "]");
    }

    public Person getPerson(String personId) {
        String sql = "select * from person where personId=?";
        return jdbcTemplateObject.queryForObject(sql,new Object[]{personId},new PersonMapper());
    }

    public void delete(int personId) {
        String sql = "delete from person where personId=?";
        jdbcTemplateObject.update(sql,personId);
        LOG.info("Deleted person with personId [" + personId + "]");
    }

    public List<Person> getAll() {
        String sql = "SELECT * FROM person";
        List<Map<String, Object>> rows = jdbcTemplateObject.queryForList(sql);
        return iterateRows(rows);
    }

    public List<Person> getAllByClient(String clientName){
        String sql = "SELECT * FROM person WHERE client='" + clientName + "'";
        List<Map<String, Object>> rows = jdbcTemplateObject.queryForList(sql);
        return iterateRows(rows);
    }

    private List<Person> iterateRows(List<Map<String, Object>> rows) {
        List<Person> people = new ArrayList<Person>();
        for (Map row : rows) {
            Person person = new Person();
            person.setEmail((String) row.get("email"));
            person.setPersonId((Integer) row.get("personId"));
            person.setClient((String) row.get("client"));
            person.setCourses((String) row.get("courses"));
            person.setFields((String) row.get("fields"));
            person.setFirstName((String) row.get("firstName"));
            person.setLastName((String) row.get("lastName"));
            person.setPhone((String) row.get("phone"));
            person.setType((String) row.get("type"));
            person.setTz((String) row.get("tz"));
            person.setHasImage((Boolean) row.get("hasImage"));
            people.add(person);
        }
        return people;
    }

    public void update(Person person) {
        String sql = "UPDATE person SET  email=?, client=?, firstName=?, lastName=?, phone=?, tz=?, hasImage=? WHERE personId =?";
        jdbcTemplateObject.update(sql, person.getEmail(),person.getClient(),person.getFirstName(),person.getLastName(),person.getPhone(),person.getTz(),person.getHasImage(),person.getPersonId());
    }
}
