package com.hamish.resources;

import com.wordnik.swagger.annotations.Api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
