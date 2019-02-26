package ru.mirea.bsbd.service;

import com.google.gson.Gson;
import ru.mirea.bsbd.common.HibernateSessionFactory;
import ru.mirea.bsbd.entity.AcceptanceEntity;
import org.hibernate.Session;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        acceptance.getAcceptanceId();
        session.save(acceptance);
        session.getTransaction().commit();
        session.close();

        return Response.ok().entity(gson.toJson(acceptance)).build();
    }
}
