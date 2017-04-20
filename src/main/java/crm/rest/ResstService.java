package crm.rest;

import com.google.gson.Gson;
import crm.backend.SpringConfig;
import crm.backend.dal.impl.CompanyDaoImpl;
import crm.backend.dal.impl.PersonDaoImpl;
import crm.backend.dal.pojo.Company;
import crm.backend.dal.pojo.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.ws.rs.*;
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
    @Path("/getPerson")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@QueryParam("personId") String  personId) {
        Person person = personDao.getPerson(personId);
        String s = new Gson().toJson(person);
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

    @POST
    @Path("/addPerson")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(String json) {
        Person person = new Gson().fromJson(json,Person.class);
        personDao.create(person.getFirstName(),person.getLastName(),person.getCompany(),person.getPhone(),person.getEmail(),person.getTz());
        return Response.status(Response.Status.OK).entity(json).build();
    }

    @POST
    @Path("/deletePerson")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePerson(String json) {
        Person person = new Gson().fromJson(json,Person.class);
        personDao.delete(person.getPersonId());
        return Response.status(Response.Status.OK).entity("Deleted person with ID [" + person.getPersonId() + "]").build();
    }
}
