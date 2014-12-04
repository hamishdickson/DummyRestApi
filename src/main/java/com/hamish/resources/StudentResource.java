package com.hamish.resources;

import com.google.gson.Gson;
import com.hamish.models.Student;
import com.hamish.utils.HibernateUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiParam;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by hamishdickson on 29/10/14.
 *
 */
@Path("/student")
@Api( value = "/student", description = "Open API to the jobs system" )
public class StudentResource {

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStudents() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction;

        try {
            transaction = session.beginTransaction();
            List<Student> studentList = session.createQuery("FROM Student").list();
            transaction.commit();

            return Response.ok().entity(new Gson().toJson(studentList)).build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecificStudent(@ApiParam(value = "Student id", required = true) @PathParam("id") int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction;

        try {
            transaction = session.beginTransaction();
            List<Student> studentList = session.createQuery("FROM Student WHERE id = " + id).list();
            transaction.commit();

            return Response.ok().entity(new Gson().toJson(studentList)).build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addStudent(Student student) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();

        session.save(student);
        session.getTransaction().commit();

        return Response.ok().build();
    }
}
