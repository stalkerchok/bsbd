package ru.mirea.bsbd.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.mirea.bsbd.common.HibernateSessionFactory;
import ru.mirea.bsbd.entity.EmployeeEntity;
import ru.mirea.bsbd.entity.InventoryBookEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/inventory_books")
public class InventoryBookService {
    GsonBuilder gsonBuilder = new GsonBuilder();

    Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();

    @POST
    @Path("/create_book/{store_id}/{organization_denomination}/{employee_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response create_book(@PathParam("store_id") int store_id,
                                @PathParam("organization_denomination") String organization_denomination,
                                @PathParam("employee_id") int employee_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            EmployeeEntity employee = session.get(EmployeeEntity.class, employee_id);

            InventoryBookEntity book = new InventoryBookEntity();

            employee.addInventoryBookEntities(book);

            session.saveOrUpdate(employee);

            book.setStoreId(store_id);
            book.setOrganizationDenomination(organization_denomination);

            session.saveOrUpdate(book);

            session.getTransaction().commit();
            session.close();

            return Response.ok().entity(gson.toJson(book)).build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }
    }

    @PUT
    @Path("/change_book/{book_id}/{store_id}/{organization_denomination}/{employee_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response change_book(@PathParam("book_id") int book_id,
                                @PathParam("store_id") int store_id,
                                @PathParam("organization_denomination") String organization_denomination,
                                @PathParam("employee_id") int employee_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            EmployeeEntity employee = session.get(EmployeeEntity.class, employee_id);

            InventoryBookEntity book = session.get(InventoryBookEntity.class, book_id);

            book.setStoreId(store_id);
            book.setOrganizationDenomination(organization_denomination);

            employee.addInventoryBookEntities(book);

            session.saveOrUpdate(employee);

            session.getTransaction().commit();
            session.close();

            return Response.ok().entity(gson.toJson(book)).build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }
    }

    @GET
    @Path("/get_book/{book_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response get_book(@PathParam("book_id") int book_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            InventoryBookEntity book = session.get(InventoryBookEntity.class, book_id);

            session.saveOrUpdate(book);

            session.getTransaction().commit();
            session.close();

            return Response.ok().entity(gson.toJson(book)).build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }
    }

    @GET
    @Path("/get_all_books")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response get_all_books(){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            Query query = session.createQuery("from InventoryBookEntity ");
            List<InventoryBookEntity> BookList = query.list();

            session.saveOrUpdate(BookList);

            session.getTransaction().commit();
            session.close();

            return Response.ok().entity(gson.toJson(BookList)).build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }
    }

    @DELETE
    @Path("/delete_book/{book_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response delete_book(@PathParam("book_id") int book_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            InventoryBookEntity book = session.get(InventoryBookEntity.class, book_id);

            session.delete(book);

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
