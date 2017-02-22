package crm.backend.dal.dao;

import crm.backend.dal.pojo.Company;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by gurt on 16-Feb-17.
 */
public interface CompanyDao {
    void setDataSource(DataSource dataSource);

    void create(String name);

    Company getCompany(String name);

    void delete(String name);

    List<Company> getAll();

    void update(String name, String column, String value);

}
