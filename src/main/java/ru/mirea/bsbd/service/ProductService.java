package ru.mirea.bsbd.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.mirea.bsbd.common.HibernateSessionFactory;
import ru.mirea.bsbd.entity.InventoryBookEntity;
import ru.mirea.bsbd.entity.ProductEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/products")
public class ProductService {
    GsonBuilder gsonBuilder = new GsonBuilder();

    Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();

    @POST
    @Path("create_product/{product_denomination}/{unit}/{amount}/{value}/{okei}/{vat}/{discount}/{book_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response create_product(@PathParam("product_denomination") String product_denomination,
                                   @PathParam("unit") String unit,
                                   @PathParam("amount") double amount,
                                   @PathParam("value") double value,
                                   @PathParam("okei") int okei,
                                   @PathParam("vat") double vat,
                                   @PathParam("discount") double discount,
                                   @PathParam("book_id") int book_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        ProductEntity product = new ProductEntity();

        InventoryBookEntity book = session.get(InventoryBookEntity.class, book_id);
        book.addProductEntities(product);

        session.saveOrUpdate(book);

        product.setProductDenomination(product_denomination);
        product.setUnit(unit);
        product.setAmount(amount);
        product.setValue(value);
        product.setOkei(okei);
        product.setVat(vat);
        product.setDiscount(discount);

        session.saveOrUpdate(product);

        session.getTransaction().commit();
        session.close();

        return Response.ok().entity(gson.toJson(product)).build();
    }

    @PUT
    @Path("change_product/{product_id}/{product_denomination}/{unit}/{amount}/{value}/{okei}/{vat}/{discount}/{book_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response change_product(@PathParam("product_id") int product_id,
                                   @PathParam("product_denomination") String product_denomination,
                                   @PathParam("unit") String unit,
                                   @PathParam("amount") double amount,
                                   @PathParam("value") double value,
                                   @PathParam("okei") int okei,
                                   @PathParam("vat") double vat,
                                   @PathParam("discount") double discount,
                                   @PathParam("book_id") int book_id){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        ProductEntity product = session.get(ProductEntity.class, product_id);

        product.setProductDenomination(product_denomination);
        product.setUnit(unit);
        product.setAmount(amount);
        product.setValue(value);
        product.setOkei(okei);
        product.setVat(vat);
        product.setDiscount(discount);

        InventoryBookEntity book = session.get(InventoryBookEntity.class, book_id);
        book.addProductEntities(product);

        session.saveOrUpdate(book);

        session.getTransaction().commit();
        session.close();

        return Response.ok().entity(gson.toJson(product)).build();
    }

    @GET
    @Path("get_product/{product_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response get_product(@PathParam("product_id") int product_id){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        ProductEntity product = session.get(ProductEntity.class, product_id);

        session.getTransaction().commit();
        session.close();

        return Response.ok().entity(gson.toJson(product)).build();
    }

    @GET
    @Path("get_all_products")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response get_all_products(){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("from ProductEntity ");
        List<ProductEntity> ProductList = query.list();

        session.getTransaction().commit();
        session.close();

        return Response.ok().entity(gson.toJson(ProductList)).build();
    }

    @DELETE
    @Path("delete_product/{product_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response delete_product(@PathParam("product_id") int product_id){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        ProductEntity product = session.get(ProductEntity.class, product_id);

        session.delete(product);

        session.getTransaction().commit();
        session.close();

        return Response.ok().build();
    }

}
