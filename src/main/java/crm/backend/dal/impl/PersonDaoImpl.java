package crm.backend.dal.impl;

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

    public void create(String firstName, String lastName) {
        String sql = "insert into person (firstName,lastName) values (?,?)";
        jdbcTemplateObject.update(sql,firstName,lastName);
        LOG.info("Added new person [" + firstName + ", " + lastName + "]");
    }

    public Person getPerson(String firstName, String lastName) {
        String sql = "select * from person where firstName=? and lastName=?";
        return jdbcTemplateObject.queryForObject(sql,new Object[]{firstName,lastName},new PersonMapper());
    }

    public void delete(String name) {

    }

    public List<Person> getAll() {
        String sql = "SELECT * FROM person";

        List<Person> people = new ArrayList<Person>();

        List<Map<String, Object>> rows = jdbcTemplateObject.queryForList(sql);
        for (Map row : rows) {
            Person person = new Person();
            person.setEmail((String) row.get("email"));
            person.setId((Integer) row.get("id"));
            person.setCompany((String) row.get("company"));
            person.setCourses((String) row.get("courses"));
            person.setFields((String) row.get("fields"));
            person.setFirstName((String) row.get("firstName"));
            person.setLastName((String) row.get("lastName"));
            person.setPhone((String) row.get("phone"));
            person.setType((String) row.get("type"));
            person.setTz((String) row.get("tz"));
            person.setImageId(row.get("imageId")!=null ? (Integer)row.get("imageId") : 0);
            people.add(person);
        }

        return people;
    }

    public void update(String firstName, String lastName, String column, String value) {
        String sql = "UPDATE person SET " + column + "=" + "? WHERE firstName='" + firstName + "' AND lastName='" + lastName + "'";
        jdbcTemplateObject.update(sql,value);
    }

    public List<Person> getAllByCompany(String companyName){

        String sql = "SELECT * FROM person WHERE company='" + companyName + "'";

        List<Person> people = new ArrayList<Person>();

        List<Map<String, Object>> rows = jdbcTemplateObject.queryForList(sql);
        for (Map row : rows) {
            Person person = new Person();
            person.setEmail((String) row.get("email"));
            person.setId((Integer) row.get("id"));
            person.setCompany((String) row.get("company"));
            person.setCourses((String) row.get("courses"));
            person.setFields((String) row.get("fields"));
            person.setFirstName((String) row.get("firstName"));
            person.setLastName((String) row.get("lastName"));
            person.setPhone((String) row.get("phone"));
            person.setType((String) row.get("type"));
            person.setTz((String) row.get("tz"));
            person.setImageId(row.get("imageId")!=null ? (Integer)row.get("imageId") : 0);
            people.add(person);
        }

        return people;
    }
}
