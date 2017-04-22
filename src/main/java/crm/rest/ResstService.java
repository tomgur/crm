package crm.rest;

import com.google.gson.Gson;
import crm.backend.SpringConfig;
import crm.backend.dal.impl.ClientDaoImpl;
import crm.backend.dal.impl.PersonDaoImpl;
import crm.backend.dal.pojo.Client;
import crm.backend.dal.pojo.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by gurt on 4/10/2017.
 */
@Path("/")
public class ResstService {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    PersonDaoImpl personDao = context.getBean(PersonDaoImpl.class);
    ClientDaoImpl clientDao = context.getBean(ClientDaoImpl.class);

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
    public Response getPerson(@QueryParam("personId") String personId) {
        Person person = personDao.getPerson(personId);
        String s = new Gson().toJson(person);
        return Response.status(200).entity(s).build();
    }

    @GET
    @Path("/getClient")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClient(@QueryParam("id") String id) {
        Client client = clientDao.getClient(id);
        String s = new Gson().toJson(client);
        return Response.status(200).entity(s).build();
    }

    @GET
    @Path("/getClients")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllClients() {
        List<Client> all = clientDao.getAll();
        String s = new Gson().toJson(all);
        return Response.status(200).entity(s).build();
    }

    @POST
    @Path("/addPerson")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(String json) {
        Person person = new Gson().fromJson(json, Person.class);
        personDao.create(person.getFirstName(), person.getLastName(), person.getClient(), person.getPhone(), person.getEmail(), person.getTz());
        return Response.status(Response.Status.OK).entity(json).build();
    }

    @POST
    @Path("/addClient")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addClient(String json) {
        Client client = new Gson().fromJson(json, Client.class);
        clientDao.create(client.getName(), client.getEmail(), client.getPhone(), client.getFax(), client.getAddress(), client.getContactPerson());
        return Response.status(Response.Status.OK).entity(json).build();
    }

    @POST
    @Path("/deletePerson")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePerson(String json) {
        Person person = new Gson().fromJson(json, Person.class);
        personDao.delete(person.getPersonId());
        return Response.status(Response.Status.OK).entity(json).build();
    }

    @POST
    @Path("/deleteClient")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteClient(String json) {
        Client client = new Gson().fromJson(json, Client.class);
        clientDao.delete(String.valueOf(client.getId()));
        return Response.status(Response.Status.OK).entity("Deleted client with ID [" + client.getId() + "]").build();
    }

    @POST
    @Path("/updatePerson")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(String json) {
        Person person = new Gson().fromJson(json, Person.class);
        personDao.update(person);
        return Response.status(Response.Status.OK).entity(person).build();
    }
}
