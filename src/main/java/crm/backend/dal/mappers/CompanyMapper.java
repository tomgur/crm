package crm.backend.dal.mappers;

import crm.backend.dal.pojo.Company;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by gurt on 17-Feb-17.
 */
public class CompanyMapper implements RowMapper<Company> {
    public Company mapRow(ResultSet resultSet, int i) throws SQLException {
        Company company = new Company();

        company.setPhone(resultSet.getString("phone"));
        company.setName(resultSet.getString("name"));
        company.setId(resultSet.getInt("id"));
        company.setAddress(resultSet.getString("address"));
        company.setContactPerson(resultSet.getString("contactPerson"));
        company.setEmail(resultSet.getString("email"));
        company.setFax(resultSet.getString("fax"));

        String invoices = resultSet.getString("invoices");
        String quotas = resultSet.getString("quotas");

        if (invoices != null && !invoices.equals("")) {
            company.setInvoices(stringToArray(invoices));
        } else {
            company.setInvoices(null);
        }

        assert invoices != null;
        if (quotas != null && !invoices.equals("")) {
            company.setQuotas(stringToArray(quotas));
        } else {
            company.setQuotas(null);
        }

        return company;

    }

    private ArrayList<String> stringToArray(String input) {
        ArrayList<String> output = new ArrayList<String>();
        Collections.addAll(output, input.split(","));
        return output;
    }
}
