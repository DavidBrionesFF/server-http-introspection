package com.nattechnologiesagency.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nattechnologiesagency.core.routes.ExampleRoute;
import com.nattechnologiesagency.core.schema.ContextPath;
import com.nattechnologiesagency.core.schema.Route;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException, InstantiationException {
        ObjectMapper objectMapper = new ObjectMapper();

        ContextPath contextPath = objectMapper.readValue(
                new File("static/contextPath.json"),
                ContextPath.class);

        System.out.println("Contexto Cargado: " + contextPath.getServerName());

        HttpServer server = HttpServer.create(new InetSocketAddress(contextPath.getPort()), 0);

        System.out.println("Server Iniciado: " +contextPath.getPort());

        UtilsContext.readByClass(ExampleRoute.class, contextPath);

        for(Route route : contextPath.getRoutes()){
            System.out.println("Ruta Agregada: " + route.getPath());
            if (route.isAnnotation()){
                server.createContext(route.getPath(), exchange -> {
                    System.out.println("Ruta Escuchada Context Annotation: " + route.getPath());
                    //La respuesta es un texto
                    System.out.println("Datos:");
                    System.out.println(route.getFile());
                    byte[] data = route.getFile().getBytes(StandardCharsets.UTF_8);

                    System.out.println("Tamaño de los datos " + data.length);
                    System.out.println("Contenido de los datos " +data.toString());

                    //Se declara el header
                    exchange.sendResponseHeaders(200, data.length);
                    //exchange.getResponseHeaders().add("Content-Type", route.getResponse().getType());

                    // Se obtiene el stream de salida
                    OutputStream os = exchange.getResponseBody();

                    //Se escribe
                    os.write(data);

                    //Se cierra
                    os.close();
                });
            } else {
                server.createContext(route.getPath(), exchange -> {
                    System.out.println("Ruta Escuchada Context File: " + route.getPath());

                    String ruta = contextPath.getPathFile()  + route.getResponse().getFile();

                    System.out.println("Ruta: " +ruta);

                    //La respuesta es un texto
                    byte[] data =  Files.readAllBytes(Path.of(ruta));

                    System.out.println("Tamaño de los datos " + data.length);
                    System.out.println("Contenido de los datos " +data.toString());

                    //Se declara el header
                    exchange.sendResponseHeaders(200, data.length);
                    //exchange.getResponseHeaders().add("Content-Type", route.getResponse().getType());

                    // Se obtiene el stream de salida
                    OutputStream os = exchange.getResponseBody();

                    //Se escribe
                    os.write(data);

                    //Se cierra
                    os.close();
                });
            }
        }

        server.setExecutor(null);
        server.start();
    }
}
