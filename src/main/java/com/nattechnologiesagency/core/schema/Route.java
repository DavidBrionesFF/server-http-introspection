package com.nattechnologiesagency.core.schema;

public class Route {
    private String path;
    private Request request;
    private Response response;
    private String file;
    private boolean annotation = false;
    private String typeFile;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public boolean isAnnotation() {
        return annotation;
    }

    public void setAnnotation(boolean annotation) {
        this.annotation = annotation;
    }

    public String getTypeFile() {
        return typeFile;
    }

    public void setTypeFile(String typeFile) {
        this.typeFile = typeFile;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
