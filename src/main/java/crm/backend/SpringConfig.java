package crm.backend;

import crm.backend.dal.impl.CompanyDaoImpl;
import crm.backend.dal.impl.PersonDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by gurt on 16-Feb-17.
 */

@Configuration
public class SpringConfig {
    @Bean
    public DataSource driverManagerDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/CRM?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        return dataSource;
    }

    @Bean
    public PersonDaoImpl personDaoImpl() {
        PersonDaoImpl personDaoImpl = new PersonDaoImpl();
        personDaoImpl.setDataSource(driverManagerDataSource());
        return personDaoImpl;
    }

    @Bean
    public CompanyDaoImpl companyDaoImpl() {
        CompanyDaoImpl companyDaoImpl = new CompanyDaoImpl();
        companyDaoImpl.setDataSource(driverManagerDataSource());
        return companyDaoImpl;
    }
}
