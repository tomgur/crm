package com.meytal.crm.backend;

import com.meytal.crm.backend.dal.impl.CompanyDaoImpl;
import com.meytal.crm.backend.dal.impl.PersonDaoImpl;
import com.meytal.crm.backend.dal.pojo.Person;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created by gurt on 16-Feb-17.
 */
public class Main {
    static Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LOG.info("Initializing app");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        PersonDaoImpl personDao = context.getBean(PersonDaoImpl.class);
        CompanyDaoImpl companyDao = context.getBean(CompanyDaoImpl.class);

        //personDao.create("Tom","Gur");
//        Person person = personDao.getPerson("Tom", "Gur");
//        System.out.println(person.toString());

//        personDao.update("Tom","Gur","tz","015549751");
//        System.out.println(personDao.getPerson("Tom", "Gur").toString());
//        companyDao.create("HPE");
//        companyDao.update("HPE","phone","035339999");
//        companyDao.update("HPE","email","admin@hpe.com");
//        companyDao.update("HPE","fax","035399998");
//        Company hpe = companyDao.getCompany("HPE");
//        System.out.println(hpe.toString());
        List<Person> hpe = personDao.getAllByCompany("HPE");
        for(Person p : hpe) {
            System.out.println(p.toString());
        }
    }
}
