package ru.mirea.bsbd.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.mirea.bsbd.common.HibernateSessionFactory;
import ru.mirea.bsbd.entity.ActEntity;
import ru.mirea.bsbd.entity.ProductEntity;
import ru.mirea.bsbd.entity.PurchaseEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/purchases")
public class PurchanceService {
    GsonBuilder gsonBuilder = new GsonBuilder();

    Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();

    @POST
    @Path("create_purchase/{product_denomination}/{product_id}/{unit}/{okei}/{ordered}/{act_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response create_purchase(@PathParam("product_denomination") String product_denomination,
                                    @PathParam("product_id") int product_id,
                                    @PathParam("unit") String unit,
                                    @PathParam("okei") int okei,
                                    @PathParam("ordered") double ordered,
                                    @PathParam("act_id") int act_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            ActEntity act = session.get(ActEntity.class, act_id);

            ProductEntity product = session.get(ProductEntity.class, product_id);

            PurchaseEntity purchase = new PurchaseEntity();

            act.addPurchaseEntities(purchase);

            session.saveOrUpdate(act);

            purchase.setProductDenomination(product_denomination);
            purchase.setProductId(product_id);
            purchase.setUnit(unit);
            purchase.setOkei(okei);
            purchase.setOrdered(ordered);


            Double value = product.getValue();
            Double discount = product.getDiscount();
            Double vat = product.getVat();

            purchase.setValue(value);
            purchase.setDiscount(discount);
            purchase.setVat(vat);

            session.saveOrUpdate(purchase);

            purchase.setSumWithoutVat((value - value * discount / 100) * ordered);

            session.saveOrUpdate(purchase);

            purchase.setVatSum(purchase.getSumWithoutVat() * vat / 100);
            session.saveOrUpdate(purchase);
            purchase.setSumWithVat(purchase.getSumWithoutVat() + purchase.getVatSum());

            session.saveOrUpdate(purchase);

            session.getTransaction().commit();
            session.close();

            return Response.ok().entity(gson.toJson(purchase)).build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }
    }

    @PUT
    @Path("change_purchase/{purchase_id}/{product_denomination}/{product_id}/{unit}/{okei}/{ordered}/{act_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response change_purchase(@PathParam("purchase_id") int purchase_id,
                                    @PathParam("product_denomination") String product_denomination,
                                    @PathParam("product_id") int product_id,
                                    @PathParam("unit") String unit,
                                    @PathParam("okei") int okei,
                                    @PathParam("ordered") double ordered,
                                    @PathParam("act_id") int act_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            ActEntity act = session.get(ActEntity.class, act_id);

            ProductEntity product = session.get(ProductEntity.class, product_id);

            PurchaseEntity purchase = session.get(PurchaseEntity.class, purchase_id);

            purchase.setProductDenomination(product_denomination);
            purchase.setProductId(product_id);
            purchase.setUnit(unit);
            purchase.setOkei(okei);
            purchase.setOrdered(ordered);

            Double value = product.getValue();
            Double discount = product.getDiscount();
            Double vat = product.getVat();

            purchase.setValue(value);
            purchase.setDiscount(discount);
            purchase.setVat(vat);

            session.saveOrUpdate(purchase);

            purchase.setSumWithoutVat((value - value * discount / 100) * ordered);

            session.saveOrUpdate(purchase);

            purchase.setVatSum(purchase.getSumWithoutVat() * vat / 100);
            session.saveOrUpdate(purchase);
            purchase.setSumWithVat(purchase.getSumWithoutVat() + purchase.getVatSum());

            act.addPurchaseEntities(purchase);

            session.saveOrUpdate(act);

            session.getTransaction().commit();
            session.close();

            return Response.ok().entity(gson.toJson(purchase)).build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }
    }


    @GET
    @Path("get_purchase/{purchase_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response get_purchase(@PathParam("purchase_id") int purchase_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            PurchaseEntity purchase = session.get(PurchaseEntity.class, purchase_id);

            session.getTransaction().commit();
            session.close();

            return Response.ok().entity(gson.toJson(purchase)).build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }

    }

    @GET
    @Path("get_all_purchases")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response get_all_purchases(){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            Query query = session.createQuery("from PurchaseEntity ");
            List<PurchaseEntity> PurchaseList = query.list();

            session.getTransaction().commit();
            session.close();

            return Response.ok().entity(gson.toJson(PurchaseList)).build();

        } catch (Exception e){

            session.getTransaction().commit();
            session.close();
            return Response.status(405).build();

        }

    }

    @DELETE
    @Path("delete_purchase/{purchase_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response delete_purchase(@PathParam("purchase_id") int purchase_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            PurchaseEntity purchase = session.get(PurchaseEntity.class, purchase_id);

            session.delete(purchase);

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
