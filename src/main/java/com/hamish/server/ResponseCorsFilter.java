package com.hamish.server;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

import javax.ws.rs.core.Response;

/**
 * Created by hamishdickson on 16/10/2014.
 *
 */
public class ResponseCorsFilter implements ContainerResponseFilter {

    @Override
    public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {

        Response.ResponseBuilder responseBuilder = Response.fromResponse(response.getResponse());
        responseBuilder
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");

        String reqHead = request.getHeaderValue("Access-Control-Request-Headers");

        if(null != reqHead && !reqHead.equals(null)){
            responseBuilder.header("Access-Control-Allow-Headers", reqHead);
        }

        response.setResponse(responseBuilder.build());

        return response;
    }
}
