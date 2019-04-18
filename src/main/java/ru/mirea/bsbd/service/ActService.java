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

    private final static String SMTP_PASSWORD = System.getProperty("mail.smtp.password");


    @POST
    @Path("/create_act/{denomination}/{date}/{structural_subdivision}/{client_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response create_act(@PathParam("denomination") String denomination,
                              @PathParam("date") String date,
                              @PathParam("structural_subdivision") String structural_subdivision,
                              @PathParam("client_id") int client_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            ClientEntity client = session.get(ClientEntity.class, client_id);
            ActEntity act = new ActEntity();
            client.addActEntities(act);

            session.saveOrUpdate(client);

            act.setDenomination(denomination);
            act.setDate(date);
            act.setStructural_subdivision(structural_subdivision);
            act.setStatus("in queue");

            session.saveOrUpdate(act);

            session.getTransaction().commit();
            session.close();

            return Response.ok().entity(gson.toJson(act)).build();

        } catch (Exception e){

            return Response.status(405).build();

        }
    }

    @PUT
    @Path("/add_employees/{act_id}/{employee_id}/{courier_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response add_employees(@PathParam("act_id") int act_id,
                                  @PathParam("employee_id") int employee_id,
                                  @PathParam("courier_id") int courier_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            ActEntity act = session.get(ActEntity.class, act_id);

            EmployeeEntity employee = session.get(EmployeeEntity.class, employee_id);
            EmployeeEntity courier = session.get(EmployeeEntity.class, courier_id);

            employee.getActEntities().add(act);

            session.saveOrUpdate(act);
            session.saveOrUpdate(employee);

            courier.getActEntities().add(act);

            session.saveOrUpdate(act);
            session.saveOrUpdate(courier);

            session.getTransaction().commit();
            session.close();

            return Response.ok().entity(gson.toJson(act)).build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }

    }

    @PUT
    @Path("/change_act/{act_id}/{denomination}/{date}/{structural_subdivision}/{client_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response change_act(@PathParam("act_id") int act_id,
                               @PathParam("denomination") String denomination,
                               @PathParam("date") String date,
                               @PathParam("structural_subdivision") String structural_subdivision,
                               @PathParam("client_id") int client_id) {

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            ActEntity act = session.get(ActEntity.class, act_id);
            ClientEntity client = session.get(ClientEntity.class, client_id);

            act.setDenomination(denomination);
            act.setDate(date);
            act.setStructural_subdivision(structural_subdivision);

            client.addActEntities(act);

            session.saveOrUpdate(client);

            session.getTransaction().commit();
            session.close();

            return Response.ok().entity(gson.toJson(act)).build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }
    }

    @GET
    @Path("/get_act/{act_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response get_act(@PathParam("act_id") int act_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try{

            ActEntity act = session.get(ActEntity.class, act_id);

            session.getTransaction().commit();
            session.close();

            return Response.ok().entity(gson.toJson(act)).build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }
    }

    @GET
    @Path("/get_all_acts")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response get_all_acts(){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            Query query = session.createQuery("from ActEntity ");
            List<ActEntity> ActList = query.list();


            session.getTransaction().commit();
            session.close();

            return Response.ok().entity(gson.toJson(ActList)).build();

        } catch (Exception e){

        session.getTransaction().commit();
        session.close();
        return Response.status(405).build();

    }

    }

    @DELETE
    @Path("/delete_act/{act_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response delete_act(@PathParam("act_id") int act_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            ActEntity act = session.get(ActEntity.class, act_id);
            session.delete(act);

            session.getTransaction().commit();
            session.close();

            return Response.ok().build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }
    }


    @PUT
    @Path("/approve_act/{act_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response approve_act(@PathParam("act_id") int act_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            ActEntity act = session.get(ActEntity.class, act_id);

            act.setStatus("approved");
            session.saveOrUpdate(act);

            String client_name = act.getClientEntity().getName();
            String client_email = act.getClientEntity().getEmail();

            Set<EmployeeEntity> employees = act.getEmployeeEntities();

            String courier_name = "";
            String courier_email = "";

            String organization_email = "";

            for (EmployeeEntity employee : employees) {
                if (employee.getPosition().equals("courier")) {
                    courier_name = employee.getName();
                    courier_email = employee.getEmail();
                } else if (employee.getPosition().equals("manager")) {
                    organization_email = employee.getEmail();
                }
            }

            Email email = EmailBuilder.startingBlank()
                    .from(act.getStructural_subdivision(), organization_email)
                    .to(client_name, client_email)
                    .to(courier_name, courier_email)
                    .withSubject("Информация о заказе")
                    .withPlainText("Акт № " + act.getActId() + " одобрен")
                    .buildEmail();

            MailerBuilder
                    .withSMTPServer("smtp.gmail.com", 25, organization_email, SMTP_PASSWORD)
                    .buildMailer()
                    .sendMail(email);

            session.getTransaction().commit();
            session.close();


            return Response.ok().entity(gson.toJson(act)).build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }
    }

    @PUT
    @Path("/took_act/{act_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response took_act(@PathParam("act_id") int act_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            ActEntity act = session.get(ActEntity.class, act_id);
            Set<PurchaseEntity> purchaseEntities = act.getPurchaseEntities();

            for (PurchaseEntity purchase : purchaseEntities) {

                double ordered = purchase.getOrdered();
                int product_id = purchase.getProductId();
                ProductEntity product = session.get(ProductEntity.class, product_id);
                double amount = product.getAmount();

                if (amount > ordered) {
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
            act.setStatus("taken");
            session.saveOrUpdate(act);


            String client_name = act.getClientEntity().getName();
            String client_email = act.getClientEntity().getEmail();

            Set<EmployeeEntity> employees = act.getEmployeeEntities();

            String courier_name = "";
            String courier_email = "";

            String organization_email = "";

            for (EmployeeEntity employee : employees) {
                if (employee.getPosition().equals("courier")) {
                    courier_name = employee.getName();
                    courier_email = employee.getEmail();
                } else if (employee.getPosition().equals("manager")) {
                    organization_email = employee.getEmail();
                }
            }


            Email email = EmailBuilder.startingBlank()
                    .from(courier_name, courier_email)
                    .to(act.getStructural_subdivision(), organization_email)
                    .withSubject("Информация о заказе")
                    .withPlainText("Курьер начал доставку заказа акта № " + act.getActId())
                    .buildEmail();

            MailerBuilder
                    .withSMTPServer("smtp.gmail.com", 25, organization_email, SMTP_PASSWORD)
                    .buildMailer()
                    .sendMail(email);


            email = EmailBuilder.startingBlank()
                    .from(act.getStructural_subdivision(), organization_email)
                    .to(client_name, client_email)
                    .withSubject("Информация о заказе")
                    .withPlainText("Курьер начал доставку заказа акта № " + act.getActId())
                    .buildEmail();

            MailerBuilder
                    .withSMTPServer("smtp.gmail.com", 25, organization_email, SMTP_PASSWORD)
                    .buildMailer()
                    .sendMail(email);


            session.getTransaction().commit();
            session.close();


            return Response.ok().entity(gson.toJson(act)).build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }

    }


    @PUT
    @Path("/gave_act/{act_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response gave_act(@PathParam("act_id") int act_id) {


        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            ActEntity act = session.get(ActEntity.class, act_id);

            act.setStatus("gave");

            String client_name = act.getClientEntity().getName();
            String client_email = act.getClientEntity().getEmail();

            Set<EmployeeEntity> employees = act.getEmployeeEntities();

            String courier_name = "";
            String courier_email = "";

            String organization_email = "";

            for (EmployeeEntity employee : employees) {
                if (employee.getPosition().equals("courier")) {
                    courier_name = employee.getName();
                    courier_email = employee.getEmail();
                } else if (employee.getPosition().equals("manager")) {
                    organization_email = employee.getEmail();
                }
            }


            Email email = EmailBuilder.startingBlank()
                    .from(courier_name, courier_email)
                    .to(act.getStructural_subdivision(), organization_email)
                    .withSubject("Информация о заказе")
                    .withPlainText("Курьер доставил заказ акта № " + act.getActId())
                    .buildEmail();

            MailerBuilder
                    .withSMTPServer("smtp.gmail.com", 25, organization_email, SMTP_PASSWORD)
                    .buildMailer()
                    .sendMail(email);


            email = EmailBuilder.startingBlank()
                    .from(act.getStructural_subdivision(), organization_email)
                    .to(client_name, client_email)
                    .withSubject("Информация о заказе")
                    .withPlainText("Курьер доставил заказ акта № " + act.getActId())
                    .buildEmail();

            MailerBuilder
                    .withSMTPServer("smtp.gmail.com", 25, organization_email, SMTP_PASSWORD)
                    .buildMailer()
                    .sendMail(email);


            session.getTransaction().commit();
            session.close();


            return Response.ok().entity(gson.toJson(act)).build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }
    }

    @PUT
    @Path("/deny_act/{act_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response deny_act(@PathParam("act_id") int act_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            ActEntity act = session.get(ActEntity.class, act_id);

            act.setStatus("denied");

            session.saveOrUpdate(act);


            String client_name = act.getClientEntity().getName();
            String client_email = act.getClientEntity().getEmail();

            Set<EmployeeEntity> employees = act.getEmployeeEntities();

            String courier_name = "";
            String courier_email = "";

            String organization_email = "";

            for (EmployeeEntity employee : employees) {
                if (employee.getPosition().equals("courier")) {
                    courier_name = employee.getName();
                    courier_email = employee.getEmail();
                } else if (employee.getPosition().equals("manager")) {
                    organization_email = employee.getEmail();
                }
            }


            Email email = EmailBuilder.startingBlank()
                    .from(courier_name, courier_email)
                    .to(act.getStructural_subdivision(), organization_email)
                    .withSubject("Информация о заказе")
                    .withPlainText("Клиент отказался от заказа акта № " + act.getActId())
                    .buildEmail();

            MailerBuilder
                    .withSMTPServer("smtp.gmail.com", 25, organization_email, SMTP_PASSWORD)
                    .buildMailer()
                    .sendMail(email);


            email = EmailBuilder.startingBlank()
                    .from(act.getStructural_subdivision(), organization_email)
                    .to(client_name, client_email)
                    .withSubject("Информация о заказе")
                    .withPlainText("Клиент отказался от заказа акта № " + act.getActId())
                    .buildEmail();

            MailerBuilder
                    .withSMTPServer("smtp.gmail.com", 25, organization_email, SMTP_PASSWORD)
                    .buildMailer()
                    .sendMail(email);


            session.getTransaction().commit();
            session.close();


            return Response.ok().entity(gson.toJson(act)).build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }

    }

    @PUT
    @Path("/return_act/{act_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response return_act(@PathParam("act_id") int act_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            ActEntity act = session.get(ActEntity.class, act_id);
            Set<PurchaseEntity> purchaseEntities = act.getPurchaseEntities();

            for (PurchaseEntity purchase : purchaseEntities) {

                int product_id = purchase.getProductId();
                ProductEntity product = session.get(ProductEntity.class, product_id);
                double amount = product.getAmount();

                product.setAmount(amount + purchase.getReleased());

                purchase.setReleased(0.0);

            }

            act.setStatus("returned");
            session.saveOrUpdate(act);


            Set<EmployeeEntity> employees = act.getEmployeeEntities();

            String courier_name = "";
            String courier_email = "";

            String organization_email = "";

            for (EmployeeEntity employee : employees) {
                if (employee.getPosition().equals("courier")) {
                    courier_name = employee.getName();
                    courier_email = employee.getEmail();
                } else if (employee.getPosition().equals("manager")) {
                    organization_email = employee.getEmail();
                }
            }


            Email email = EmailBuilder.startingBlank()
                    .from(courier_name, courier_email)
                    .to(act.getStructural_subdivision(), organization_email)
                    .withSubject("Информация о заказе")
                    .withPlainText("Курьер вернул заказ акта № " + act.getActId() + " на склад")
                    .buildEmail();

            MailerBuilder
                    .withSMTPServer("smtp.gmail.com", 25, organization_email, SMTP_PASSWORD)
                    .buildMailer()
                    .sendMail(email);


            session.getTransaction().commit();
            session.close();


            return Response.ok().entity(gson.toJson(act)).build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }

    }
}
