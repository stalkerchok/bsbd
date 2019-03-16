package ru.mirea.bsbd.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import ru.mirea.bsbd.common.HibernateSessionFactory;
import ru.mirea.bsbd.entity.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@Path("/acts")
public class ActService {

    GsonBuilder gsonBuilder = new GsonBuilder();

    Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();


    @POST
    @Path("/create_act/{act_id}/{denomination}/{date}/{structural_subdivision}/{client_id}/{employee_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response create_act(@PathParam("act_id") int act_id,
                              @PathParam("denomination") String denomination,
                              @PathParam("date") String date,
                              @PathParam("structural_subdivision") String structural_subdivision,
                              @PathParam("client_id") int client_id,
                              @PathParam("employee_id") int employee_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        ActEntity act = new ActEntity();
        act.setActId(act_id);
        act.setDenomination(denomination);
        act.setDate(date);
        act.setStructural_subdivision(structural_subdivision);
        act.setStatus("in queue");

        ClientEntity client = session.get(ClientEntity.class, client_id);
        client.addActEntities(act);

        session.saveOrUpdate(client);

        EmployeeEntity employee = session.get(EmployeeEntity.class, employee_id);
        employee.addActEntities(act);

        session.saveOrUpdate(employee);

        session.getTransaction().commit();
        session.close();

        return Response.ok().entity(gson.toJson(act)).build();
    }

    @PUT
    @Path("/change_act/{act_id}/{denomination}/{date}/{structural_subdivision}/{client_id}/{employee_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response change_act(@PathParam("act_id") int act_id,
                               @PathParam("denomination") String denomination,
                               @PathParam("date") String date,
                               @PathParam("structural_subdivision") String structural_subdivision,
                               @PathParam("client_id") int client_id,
                               @PathParam("employee_id") int employee_id) {

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        ActEntity act = session.get(ActEntity.class, act_id);
        act.setActId(act_id);
        act.setDenomination(denomination);
        act.setDate(date);
        act.setStructural_subdivision(structural_subdivision);

        ClientEntity client = session.get(ClientEntity.class, client_id);
        client.addActEntities(act);

        session.saveOrUpdate(client);

        EmployeeEntity employee = session.get(EmployeeEntity.class, employee_id);
        employee.addActEntities(act);

        session.saveOrUpdate(employee);

        session.getTransaction().commit();
        session.close();

        return Response.ok().entity(gson.toJson(act)).build();
    }

    @GET
    @Path("/get_act/{act_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response get_act(@PathParam("act_id") int act_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        ActEntity act = session.get(ActEntity.class, act_id);

        session.getTransaction().commit();
        session.close();

        /*
        Email email = EmailBuilder.startingBlank()
                .from("Anton", "stalkerchok@gmail.com")
                .to("Drynya", "herzeleidywka@gmail.com")
                .to("D1masta", "semenovdmitry1998@gmail.com")
                .withSubject("Pryvet")
                .withPlainText("Здарова)")
                .buildEmail();

        MailerBuilder
                .withSMTPServer("smtp.gmail.com", 25, "stalkerchok@gmail.com", "Stalkerchok1")
                .buildMailer()
                .sendMail(email);

                */
        return Response.ok().entity(gson.toJson(act)).build();
    }

    @GET
    @Path("/get_all_acts")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response get_all_acts(){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("from ActEntity ");
        List<ActEntity> ActList = query.list();

        session.saveOrUpdate(ActList);

        session.getTransaction().commit();
        session.close();

        return Response.ok().entity(gson.toJson(ActList)).build();

    }

    @DELETE
    @Path("/delete_act/{act_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response delete_act(@PathParam("act_id") int act_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        ActEntity act = session.get(ActEntity.class, act_id);
        session.delete(act);

        session.getTransaction().commit();
        session.close();

        return Response.ok().build();
    }


    @PUT
    @Path("/approve_act/{act_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response approve_act(@PathParam("act_id") int act_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        ActEntity act = session.get(ActEntity.class, act_id);
        Set<PurchaseEntity> purchaseEntities = act.getPurchaseEntities();

        for (PurchaseEntity purchase : purchaseEntities){

            double ordered = purchase.getOrdered();
            int product_id = purchase.getProductId();
            ProductEntity product = session.get(ProductEntity.class, product_id);
            double amount = product.getAmount();

            if (amount > ordered){
                double remainder = amount - ordered;
                purchase.setReleased(ordered);
                product.setAmount(remainder);

            } else {

                purchase.setReleased(amount);
                product.setAmount(0.0);

            }
            session.saveOrUpdate(product);
            session.saveOrUpdate(purchase);

        }
        act.setStatus("approved");
        session.saveOrUpdate(act);

        session.getTransaction().commit();
        session.close();


        return Response.ok().entity(gson.toJson(act)).build();
    }

}
