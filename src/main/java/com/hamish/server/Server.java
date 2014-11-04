package com.hamish.server;

import com.sun.grizzly.http.embed.GrizzlyWebServer;
import com.sun.grizzly.http.servlet.ServletAdapter;
import com.sun.jersey.api.container.grizzly2.GrizzlyWebContainerFactory;
import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.glassfish.grizzly.http.server.HttpServer;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hamishdickson on 16/10/2014.
 *
 */
public class Server {
    private static final int PORT = 8080;
    private static final URI BASE_URI = getBaseUri();
    public static final String CONTEXT_PATH_URL = "/dummy";
    public static final String RESOURCE_PATH = "com.hamish.resources";
    public static final String RESPONSE_CORS_FILTER = "com.hamish.server.ResponseCorsFilter";
    public static final String JERSEY_BLURB = "Jersey app started. Hit enter to stop it...";

    private static URI getBaseUri() {
        return UriBuilder.fromUri("http://localhost/").port(PORT).build();
    }


    protected static HttpServer startServer() throws IOException {
        final Map<String, String> initParams = new HashMap<String, String>();

        initParams.put("com.sun.jersey.config.property.packages", RESOURCE_PATH);
        initParams.put("com.wordnik.swagger.jersey.listing", RESOURCE_PATH);

        System.out.println("Starting Grizzly server ....");

        return GrizzlyWebContainerFactory.create(BASE_URI, initParams);
    }

    protected static GrizzlyWebServer startGrizzlyServer() throws IOException {
        GrizzlyWebServer grizzlyWebServer = new GrizzlyWebServer(PORT);

        ServletAdapter jerseyAdapter = new ServletAdapter();
        jerseyAdapter.addInitParameter("com.sun.jersey.config.property.packages", RESOURCE_PATH);
        jerseyAdapter.addInitParameter("com.sun.jersey.spi.container.ContainerResponseFilters", RESPONSE_CORS_FILTER);
        jerseyAdapter.addInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");

        jerseyAdapter.setContextPath(CONTEXT_PATH_URL);
        jerseyAdapter.setServletInstance(new ServletContainer());

        grizzlyWebServer.addGrizzlyAdapter(jerseyAdapter, new String[] {CONTEXT_PATH_URL});

        return grizzlyWebServer;
    }

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = startServer();
        System.out.println(String.format(JERSEY_BLURB, BASE_URI));

        GrizzlyWebServer gws = startGrizzlyServer();
        gws.start();

        System.in.read();

        httpServer.stop();
        gws.stop();
    }
}
