package ru.mirea.bsbd.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.mirea.bsbd.common.HibernateSessionFactory;
import ru.mirea.bsbd.entity.ActEntity;
import ru.mirea.bsbd.entity.OrganizationEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/organizations")
public class OrganizationService {
    GsonBuilder gsonBuilder = new GsonBuilder();

    Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();

    @POST
    @Path("create_organization/{type}/{denomination}/{address}/{telephone_number}/{okpo}/{act_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response create_organization(@PathParam("type") String type,
                                        @PathParam("denomination") String denomination,
                                        @PathParam("address") String address,
                                        @PathParam("telephone_number") String telephone_number,
                                        @PathParam("okpo") int okpo,
                                        @PathParam("act_id") int act_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            ActEntity act = session.get(ActEntity.class, act_id);

            OrganizationEntity organization = new OrganizationEntity();

            act.addOrganizationEntities(organization);

            session.saveOrUpdate(act);

            organization.setType(type);
            organization.setDenomination(denomination);
            organization.setAddress(address);
            organization.setTelephoneNumber(telephone_number);
            organization.setOkpo(okpo);

            session.saveOrUpdate(organization);

            session.getTransaction().commit();
            session.close();

            return Response.ok().entity(gson.toJson(organization)).build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }
    }

    @PUT
    @Path("change_organization/{organization_id}/{type}/{denomination}/{address}/{telephone_number}/{okpo}/{act_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response change_organization(@PathParam("organization_id") int organization_id,
                                        @PathParam("type") String type,
                                        @PathParam("denomination") String denomination,
                                        @PathParam("address") String address,
                                        @PathParam("telephone_number") String telephone_number,
                                        @PathParam("okpo") int okpo,
                                        @PathParam("act_id") int act_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            ActEntity act = session.get(ActEntity.class, act_id);

            OrganizationEntity organization = session.get(OrganizationEntity.class, organization_id);

            organization.setType(type);
            organization.setDenomination(denomination);
            organization.setAddress(address);
            organization.setTelephoneNumber(telephone_number);
            organization.setOkpo(okpo);

            act.addOrganizationEntities(organization);

            session.saveOrUpdate(act);

            session.getTransaction().commit();
            session.close();

            return Response.ok().entity(gson.toJson(organization)).build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }
    }

    @GET
    @Path("get_organization/{organization_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response get_organization(@PathParam("organization_id") int organization_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            OrganizationEntity organization = session.get(OrganizationEntity.class, organization_id);

            session.saveOrUpdate(organization);

            session.getTransaction().commit();
            session.close();

            return Response.ok().entity(gson.toJson(organization)).build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }
    }

    @GET
    @Path("get_all_organizations")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response get_all_organizations(){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            Query query = session.createQuery("from OrganizationEntity ");
            List<OrganizationEntity> OrganizationList = query.list();

            session.saveOrUpdate(OrganizationList);

            session.getTransaction().commit();
            session.close();

            return Response.ok().entity(gson.toJson(OrganizationList)).build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }
    }

    @DELETE
    @Path("/delete_organization/{organization_id}")
            @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
            public Response delete_organization(@PathParam("organization_id") int organization_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            OrganizationEntity organization = session.get(OrganizationEntity.class, organization_id);

            session.delete(organization);

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
