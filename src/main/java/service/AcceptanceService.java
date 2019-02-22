package service;

import com.google.gson.Gson;
import common.HibernateSessionFactory;
import entity.AcceptanceEntity;
import org.hibernate.Session;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

@Path("/acceptance")
public class AcceptanceService {

    private final Gson gson;

    public AcceptanceService(){gson = new Gson();}

    @GET
    @Path("/get/{acceptanceId}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getAcceptance(@PathParam("acceptanceId") int acceptanceId){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        AcceptanceEntity acceptance = session.get(AcceptanceEntity.class, acceptanceId);
        session.save(acceptance);
        session.getTransaction().commit();
        session.close();

        return Response.ok().entity(gson.toJson(acceptance)).build();
    }
}
