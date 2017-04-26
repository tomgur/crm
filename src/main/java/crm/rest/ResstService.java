package crm.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.core.util.Base64;
import crm.backend.SpringConfig;
import crm.backend.dal.impl.ClientDaoImpl;
import crm.backend.dal.impl.PersonDaoImpl;
import crm.backend.dal.pojo.Client;
import crm.backend.dal.pojo.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.List;

/**
 * Created by gurt on 4/10/2017.
 */


@Path("/")
public class ResstService {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    PersonDaoImpl personDao = context.getBean(PersonDaoImpl.class);
    ClientDaoImpl clientDao = context.getBean(ClientDaoImpl.class);


    //    -----------   PERSON ENDPOINTS    -----------
    @POST
    @Path("/addPerson")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(String json) throws IOException {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        boolean imageSaved = false;
        if (!"".equals(jsonObject.get("imageBlob").getAsString())) {
            String imageDir = "c:\\users\\gurt\\git\\crm\\src\\main\\webapp";
            String uploadedFileLocation = imageDir + File.separator + "images" + File.separator + jsonObject.get("firstName").getAsString() + "_" + jsonObject.get("lastName").getAsString() + ".png";
            imageSaved = saveBase64Image(jsonObject.get("imageBlob").getAsString(), uploadedFileLocation);
        }
        jsonObject.remove("imageBlob");
        Person person = new Gson().fromJson(jsonObject.toString(),Person.class);
        personDao.create(person.getFirstName(),
                person.getLastName(),
                person.getClient(),
                person.getPhone(),
                person.getEmail(),
                person.getTz(),
                imageSaved
                );
        return Response.status(Response.Status.OK).entity(json).build();
    }


    @POST
    @Path("/upload_json")

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public Response receiveJSON(String json) throws IOException {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        String filePath = jsonObject.get("file").getAsString();
        saveBase64Image(filePath, jsonObject.get("file_name").getAsString());
        //Prints my json object
        return Response.status(Response.Status.OK).entity(json).build();
    }

    private boolean saveBase64Image(String file_string, String file_name) {
        String replace = file_string.replace("data:image/png;base64,", "");
        byte[] bytes = Base64.decode(replace);
        File file = new File(file_name);
        FileOutputStream fop = null;
        try {
            fop = new FileOutputStream(file);
            fop.write(bytes);
            fop.flush();
            fop.close();
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
        return true;
    }


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

    @POST
    @Path("/updatePerson")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        boolean imageSaved = false;
        if (!"".equals(jsonObject.get("imageBlob").getAsString())) {
            String imageDir = "c:\\users\\gurt\\git\\crm\\src\\main\\webapp";
            String uploadedFileLocation = imageDir + File.separator + "images" + File.separator + jsonObject.get("firstName").getAsString() + "_" + jsonObject.get("lastName").getAsString() + ".png";
            imageSaved = saveBase64Image(jsonObject.get("imageBlob").getAsString(), uploadedFileLocation);
        }
        jsonObject.remove("imageBlob");
        Person person = new Gson().fromJson(json, Person.class);
        person.setHasImage(imageSaved);
        personDao.update(person);
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


//    -----------   CLIENT ENDPOINTS    -----------

    @POST
    @Path("/addClient")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addClient(String json) {
        Client client = new Gson().fromJson(json, Client.class);
        clientDao.create(client.getName(), client.getEmail(), client.getPhone(), client.getFax(), client.getAddress(), client.getContactPerson());
        return Response.status(Response.Status.OK).entity(json).build();
    }

    @GET
    @Path("/getClients")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllClients() {
        List<Client> all = clientDao.getAll();
        String s = new Gson().toJson(all);
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

    @POST
    @Path("/updateClient")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateClient(String json) {
        Client client = new Gson().fromJson(json, Client.class);
        clientDao.update(client);
        return Response.status(Response.Status.OK).entity(json).build();
    }

    @POST
    @Path("/deleteClient")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteClient(String json) {
        Client client = new Gson().fromJson(json, Client.class);
        clientDao.delete(String.valueOf(client.getId()));
        return Response.status(Response.Status.OK).entity(json).build();
    }

    // save uploaded file to new location
    private void writeToFile(InputStream uploadedInputStream,
                             String uploadedFileLocation) {

        try {
            OutputStream out;
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}
