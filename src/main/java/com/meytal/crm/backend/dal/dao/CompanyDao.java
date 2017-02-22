package com.meytal.crm.backend.dal.dao;

import com.meytal.crm.backend.dal.pojo.Company;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by gurt on 16-Feb-17.
 */
public interface CompanyDao {
    public void setDataSource(DataSource dataSource);

    public void create(String name);

    public Company getCompany(String name);

    public void delete(String name);

    public List<Company> getAll();

    public void update(String name, String column, String value);

}
