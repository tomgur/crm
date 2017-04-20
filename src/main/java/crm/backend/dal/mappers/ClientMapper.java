package crm.backend.dal.mappers;

import crm.backend.dal.pojo.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by gurt on 17-Feb-17.
 */
public class ClientMapper implements RowMapper<Client> {
    public Client mapRow(ResultSet resultSet, int i) throws SQLException {
        Client client = new Client();

        client.setPhone(resultSet.getString("phone"));
        client.setName(resultSet.getString("name"));
        client.setId(resultSet.getInt("id"));
        client.setAddress(resultSet.getString("address"));
        client.setContactPerson(resultSet.getString("contactPerson"));
        client.setEmail(resultSet.getString("email"));
        client.setFax(resultSet.getString("fax"));

        String invoices = resultSet.getString("invoices");
        String quotas = resultSet.getString("quotas");

        if (invoices != null && !invoices.equals("")) {
            client.setInvoices(stringToArray(invoices));
        } else {
            client.setInvoices(null);
        }

        assert invoices != null;
        if (quotas != null && !invoices.equals("")) {
            client.setQuotas(stringToArray(quotas));
        } else {
            client.setQuotas(null);
        }

        return client;

    }

    private ArrayList<String> stringToArray(String input) {
        ArrayList<String> output = new ArrayList<String>();
        Collections.addAll(output, input.split(","));
        return output;
    }
}
