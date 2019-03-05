package ru.mirea.bsbd.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.Session;
import ru.mirea.bsbd.common.HibernateSessionFactory;
import ru.mirea.bsbd.entity.ActEntity;
import ru.mirea.bsbd.entity.EmployeeEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/act")
public class ActService {

    GsonBuilder gsonBuilder = new GsonBuilder();

    Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();


    public ActService(){}

    @POST
    @Path("/create_purchase/{actId}/{name}/{employeeId}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response createAct(@PathParam("actId") int actId,
                              @PathParam("name") String name,
                              @PathParam("employeeId") int employeeId){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        ActEntity act = new ActEntity();
        act.setActId(actId);
        act.setName(name);

        EmployeeEntity employee = new EmployeeEntity();
        employee.setEmployeeId(employeeId);

        employee.addActEntities(act);

        session.save(employee);


        session.getTransaction().commit();
        session.close();

        return Response.ok().entity(gson.toJson(employee)).build();
    }
}
