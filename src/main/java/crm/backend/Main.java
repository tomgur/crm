package crm.backend;

import crm.backend.dal.impl.ClientDaoImpl;
import crm.backend.dal.impl.PersonDaoImpl;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by gurt on 16-Feb-17.
 */
public class Main {
    private static Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LOG.info("Initializing app");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        PersonDaoImpl personDao = context.getBean(PersonDaoImpl.class);
        ClientDaoImpl clientDao = context.getBean(ClientDaoImpl.class);

//        personDao.create("Yonatan","Shichel");
//        Person person = personDao.getPerson("Tom", "Gur");
//        System.out.println(person.toString());
//
//        personDao.update("Tom","Gur","tz","015549751");
//        System.out.println(personDao.getPerson("Tom", "Gur").toString());
//        clientDao.create("HPE");
//        clientDao.update("HPE","phone","035339999");
//        clientDao.update("HPE","email","admin@hpe.com");
//        clientDao.update("HPE","fax","035399998");
//        Client hpe = clientDao.getClient("HPE");
//        System.out.println(hpe.toString());
        personDao.update("Tom", "Gur","phone","0507448140");
//        List<Person> hpePeople = personDao.getAll();
//        for(Person p : hpePeople) {
//            System.out.println(p.toString());
//        }
    }
}
