package ru.mirea.bsbd.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.mirea.bsbd.common.HibernateSessionFactory;
import ru.mirea.bsbd.entity.EmployeeEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employees")
public class EmployeeService {
    GsonBuilder gsonBuilder = new GsonBuilder();

    Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();

    @POST
    @Path("/create_employee/{position}/{name}/{surname}/{patronymic}/{email}/{telephone_number}/{office_number}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response create_employee(@PathParam("position") String position,
                                    @PathParam("name") String name,
                                    @PathParam("surname") String surname,
                                    @PathParam("patronymic") String patronymic,
                                    @PathParam("email") String email,
                                    @PathParam("telephone_number") String telephone_number,
                                    @PathParam("office_number") int office_number){


        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            EmployeeEntity employee = new EmployeeEntity();

            employee.setPosition(position);
            employee.setName(name);
            employee.setSurname(surname);
            employee.setPatronymic(patronymic);
            employee.setEmail(email);
            employee.setTelephoneNumber(telephone_number);
            employee.setOfficeNumber(office_number);

            session.saveOrUpdate(employee);

            session.getTransaction().commit();
            session.close();

            return Response.ok().entity(gson.toJson(employee)).build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }
    }

    @PUT
    @Path("/change_employee/{employee_id}/{position}/{name}/{surname}/{patronymic}/{email}/{telephone_number}/{office_number}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response change_employee(@PathParam("employee_id") int employee_id,
                                    @PathParam("position") String position,
                                    @PathParam("name") String name,
                                    @PathParam("surname") String surname,
                                    @PathParam("patronymic") String patronymic,
                                    @PathParam("email") String email,
                                    @PathParam("telephone_number") String telephone_number,
                                    @PathParam("office_number") int office_number){


        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            EmployeeEntity employee = session.get(EmployeeEntity.class, employee_id);

            employee.setPosition(position);
            employee.setName(name);
            employee.setSurname(surname);
            employee.setPatronymic(patronymic);
            employee.setEmail(email);
            employee.setTelephoneNumber(telephone_number);
            employee.setOfficeNumber(office_number);

            session.saveOrUpdate(employee);

            session.getTransaction().commit();
            session.close();

            return Response.ok().entity(gson.toJson(employee)).build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }
    }

    @GET
    @Path("/get_employee/{employee_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response get_employee(@PathParam("employee_id") int employee_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            EmployeeEntity employee = session.get(EmployeeEntity.class, employee_id);

            session.saveOrUpdate(employee);

            session.getTransaction().commit();
            session.close();

            return Response.ok().entity(gson.toJson(employee)).build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }
    }

    @GET
    @Path("/get_all_employees")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response get_all_employees(){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            Query query = session.createQuery("from EmployeeEntity ");
            List<EmployeeEntity> EmployeeList = query.list();

            session.saveOrUpdate(EmployeeList);

            session.getTransaction().commit();
            session.close();

            return Response.ok().entity(gson.toJson(EmployeeList)).build();

        } catch (Exception e){

                session.getTransaction().commit();
                session.close();
                return Response.status(405).build();

            }
    }

    @DELETE
    @Path("/delete_employee/{employee_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response delete_employee(@PathParam("employee_id") int employee_id) {

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            EmployeeEntity employee = session.get(EmployeeEntity.class, employee_id);

            session.delete(employee);

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
