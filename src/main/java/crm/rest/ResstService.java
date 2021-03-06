package crm.rest;

import com.google.gson.Gson;
import crm.backend.SpringConfig;
import crm.backend.dal.impl.CompanyDaoImpl;
import crm.backend.dal.impl.PersonDaoImpl;
import crm.backend.dal.pojo.Company;
import crm.backend.dal.pojo.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;

/**
 * Created by gurt on 4/10/2017.
 */
@Path("/")
public class ResstService {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    PersonDaoImpl personDao = context.getBean(PersonDaoImpl.class);
    CompanyDaoImpl companyDao = context.getBean(CompanyDaoImpl.class);

    @GET
    @Path("/getPeople")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPeople() {
        List<Person> all = personDao.getAll();
        String s = new Gson().toJson(all);
        return Response.status(200).entity(s).build();
    }

    @GET
    @Path("/getCompanies")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCompanies() {
        List<Company> all = companyDao.getAll();
        String s = new Gson().toJson(all);
        return Response.status(200).entity(s).build();
    }
}
