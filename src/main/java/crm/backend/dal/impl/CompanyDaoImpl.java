package crm.backend.dal.impl;

import crm.backend.dal.dao.CompanyDao;
import crm.backend.dal.mappers.CompanyMapper;
import crm.backend.dal.pojo.Company;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by gurt on 16-Feb-17.
 */
public class CompanyDaoImpl implements CompanyDao {
    private static Logger LOG = Logger.getLogger(CompanyDaoImpl.class.getName());
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplateObject = new JdbcTemplate(dataSource);
        LOG.info("Company DAO is ready");
    }

    public void create(String name) {
        String sql = "insert into company (name) values (?)";
        jdbcTemplateObject.update(sql,name);
        LOG.info("Added new company [" + name + "]");
    }

    public Company getCompany(String name) {
        String sql = "select * from company where name=?";
        return jdbcTemplateObject.queryForObject(sql,new Object[]{name},new CompanyMapper());
    }

    public void delete(String name) {

    }

    public List<Company> getAll() {
        return null;
    }

    public void update(String name, String column, String value) {
        String sql = "UPDATE company SET " + column + "=" + "? WHERE name='" + name + "'";
        jdbcTemplateObject.update(sql,value);
    }
}
