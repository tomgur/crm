package crm.backend.dal.dao;

import crm.backend.dal.pojo.Client;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by gurt on 16-Feb-17.
 */
public interface ClientDao {
    void setDataSource(DataSource dataSource);

    void create(String name, String email, String phone, String fax, String address, String contactPerson);

    Client getClient(String id);

    void delete(String name);

    List<Client> getAll();

    void update(String name, String column, String value);

}
