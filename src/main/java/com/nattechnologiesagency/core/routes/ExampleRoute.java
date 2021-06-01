package com.nattechnologiesagency.core.routes;

import com.nattechnologiesagency.core.anotations.GetRoute;

public class ExampleRoute {

    public ExampleRoute() {
    }

    @GetRoute(path = "/example-two", typeFile = "text/html")
    public String example(){
        return "Hola Mundo";
    }

    @GetRoute(path = "/example-3", typeFile = "text/html")
    public String ejemploPerron(){
        return "<h1>Hola Mundo</h1>";
    }
}
