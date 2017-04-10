package crm.backend;

import crm.backend.dal.impl.CompanyDaoImpl;
import crm.backend.dal.impl.PersonDaoImpl;
import crm.backend.dal.pojo.Company;
import crm.backend.dal.pojo.Person;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created by gurt on 16-Feb-17.
 */
public class Main {
    private static Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LOG.info("Initializing app");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        PersonDaoImpl personDao = context.getBean(PersonDaoImpl.class);
        CompanyDaoImpl companyDao = context.getBean(CompanyDaoImpl.class);

//        personDao.create("Yonatan","Shichel");
//        Person person = personDao.getPerson("Tom", "Gur");
//        System.out.println(person.toString());
//
//        personDao.update("Tom","Gur","tz","015549751");
//        System.out.println(personDao.getPerson("Tom", "Gur").toString());
//        companyDao.create("HPE");
//        companyDao.update("HPE","phone","035339999");
//        companyDao.update("HPE","email","admin@hpe.com");
//        companyDao.update("HPE","fax","035399998");
//        Company hpe = companyDao.getCompany("HPE");
//        System.out.println(hpe.toString());
        personDao.update("Tom", "Gur","company","Slave for Israeli Government");
        List<Person> hpePeople = personDao.getAll();
        for(Person p : hpePeople) {
            System.out.println(p.toString());
        }
    }
}
