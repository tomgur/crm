package crm.backend.dal.impl;

import crm.backend.dal.dao.CompanyDao;
import crm.backend.dal.mappers.CompanyMapper;
import crm.backend.dal.pojo.Company;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
        String sql = "select * from company";
        List<Map<String, Object>> rows = jdbcTemplateObject.queryForList(sql);
        return iterateRows(rows);
    }

    public List<Company> iterateRows(List<Map<String, Object>> rows) {
        List<Company> companies = new ArrayList<Company>();
        for (Map row : rows) {
            Company company = new Company();
            company.setId((Integer) row.get("id"));
            company.setName((String) row.get("name"));
            company.setContactPerson((String) row.get("contactPerson"));
            company.setAddress((String) row.get("address"));
            company.setEmail((String) row.get("email"));
            company.setFax((String) row.get("fax"));
            company.setPhone((String) row.get("phone"));
            String[] strings = row.get("invoices").toString().split(",");
            ArrayList<Integer> invoiceIntList = new ArrayList<Integer>();
            for (String s : strings){
                invoiceIntList.add(Integer.parseInt(s));
            }
            company.setInvoices(invoiceIntList);
            company.setQuotas(new ArrayList<String>(Arrays.asList(row.get("quotas").toString().split(","))));
            companies.add(company);
        }
        return companies;
    }

    public void update(String name, String column, String value) {
        String sql = "UPDATE company SET " + column + "=" + "? WHERE name='" + name + "'";
        jdbcTemplateObject.update(sql,value);
    }
}
