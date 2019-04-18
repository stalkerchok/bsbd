package ru.mirea.bsbd.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.mirea.bsbd.common.HibernateSessionFactory;
import ru.mirea.bsbd.entity.ClientEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/clients")
public class ClientService {

    GsonBuilder gsonBuilder = new GsonBuilder();

    Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();

    public ClientService(){}

    @POST
    @Path("/create_client/{name}/{surname}/{patronymic}/{email}/{address}/{telephone_number}/{okpo}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response create_client(@PathParam("name") String name,
                                 @PathParam("surname") String surname,
                                 @PathParam("patronymic") String patronymic,
                                 @PathParam("email") String email,
                                 @PathParam("address") String address,
                                 @PathParam("telephone_number") String telephone_number,
                                 @PathParam("okpo") int okpo){


        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            ClientEntity client = new ClientEntity();

            client.setName(name);
            client.setSurname(surname);
            client.setPatronymic(patronymic);
            client.setEmail(email);
            client.setAddress(address);
            client.setTelephoneNumber(telephone_number);
            client.setOkpo(okpo);

            session.saveOrUpdate(client);

            session.getTransaction().commit();
            session.close();

            return Response.ok().entity(gson.toJson(client)).build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }

    }

    @PUT
    @Path("/change_client/{client_id}/{name}/{surname}/{patronymic}/{email}/{address}/{telephone_number}/{okpo}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response change_client(@PathParam("client_id") int client_id,
                                 @PathParam("name") String name,
                                 @PathParam("surname") String surname,
                                 @PathParam("patronymic") String patronymic,
                                 @PathParam("email") String email,
                                 @PathParam("address") String address,
                                 @PathParam("telephone_number") String telephone_number,
                                 @PathParam("okpo") int okpo){


        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            ClientEntity client = session.get(ClientEntity.class, client_id);

            client.setName(name);
            client.setSurname(surname);
            client.setPatronymic(patronymic);
            client.setEmail(email);
            client.setAddress(address);
            client.setTelephoneNumber(telephone_number);
            client.setOkpo(okpo);

            session.saveOrUpdate(client);

            session.getTransaction().commit();
            session.close();

            return Response.ok().entity(gson.toJson(client)).build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }
    }

    @GET
    @Path("/get_client/{client_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response get_client(@PathParam("client_id") int client_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            ClientEntity client = session.get(ClientEntity.class, client_id);

            session.saveOrUpdate(client);

            session.getTransaction().commit();
            session.close();

            return Response.ok().entity(gson.toJson(client)).build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }
    }

    @GET
    @Path("/get_all_clients")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response get_all_clients(){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("from ClientEntity ");
        List<ClientEntity> ClientList = query.list();

        session.saveOrUpdate(ClientList);

        session.getTransaction().commit();
        session.close();

        return Response.ok().entity(gson.toJson(ClientList)).build();
    }

    @DELETE
    @Path("/delete_client/{client_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response delete_client(@PathParam("client_id") int client_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            ClientEntity client = session.get(ClientEntity.class, client_id);

            session.delete(client);

            session.getTransaction().commit();
            session.close();

            return Response.ok().build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }
    }

}
