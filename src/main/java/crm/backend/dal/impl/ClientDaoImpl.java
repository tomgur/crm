package crm.backend.dal.impl;

import crm.backend.dal.dao.ClientDao;
import crm.backend.dal.mappers.ClientMapper;
import crm.backend.dal.pojo.Client;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by gurt on 16-Feb-17.
 */
public class ClientDaoImpl implements ClientDao {
    private static Logger LOG = Logger.getLogger(ClientDaoImpl.class.getName());
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplateObject = new JdbcTemplate(dataSource);
        LOG.info("Client DAO is ready");
    }

    public void create(String name, String email, String phone, String fax, String address, String contactPerson) {
        String sql = "insert into client (name,email,phone,fax,address,contactPerson) values (?,?,?,?,?,?)";
        jdbcTemplateObject.update(sql,name,email,phone,fax,address,contactPerson);
        LOG.info("Added new client [" + name + "]");
    }

    public Client getClient(String name) {
        String sql = "select * from client where name=?";
        return jdbcTemplateObject.queryForObject(sql,new Object[]{name},new ClientMapper());
    }

    public void delete(String name) {

    }

    public List<Client> getAll() {
        String sql = "select * from client";
        List<Map<String, Object>> rows = jdbcTemplateObject.queryForList(sql);
        return iterateRows(rows);
    }

    public List<Client> iterateRows(List<Map<String, Object>> rows) {
        List<Client> clients = new ArrayList<Client>();
        for (Map row : rows) {
            Client client = new Client();
            client.setId((Integer) row.get("id"));
            client.setName((String) row.get("name"));
            client.setContactPerson((String) row.get("contactPerson"));
            client.setAddress((String) row.get("address"));
            client.setEmail((String) row.get("email"));
            client.setFax((String) row.get("fax"));
            client.setPhone((String) row.get("phone"));
            String[] strings = new String[0];
            if (row.get("invoices")!=null) {
                strings = row.get("invoices").toString().split(",");
            }
            ArrayList<Integer> invoiceIntList = new ArrayList<Integer>();
            for (String s : strings){
                invoiceIntList.add(Integer.parseInt(s));
            }
            client.setInvoices(invoiceIntList);
            ArrayList<String> quotasList = new ArrayList<String>();
            if (row.get("quotas")!=null) {
                for (String quota : row.get("quotas").toString().split(","))
                quotasList.add(quota);
            }
            client.setQuotas(quotasList);
            clients.add(client);
        }
        return clients;
    }

    public void update(String name, String column, String value) {
        String sql = "UPDATE client SET " + column + "=" + "? WHERE name='" + name + "'";
        jdbcTemplateObject.update(sql,value);
    }
}
