package com.example.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;

import com.example.controller.dao.services.ProyectoEnergiaServices;
import com.example.models.ProyectoEnergia;

@Path("/proyectoEnergia")
public class ProyectoEnergiaApi {
    private ProyectoEnergiaServices pes = new ProyectoEnergiaServices();
    private Gson gson = new Gson();

    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProyectoEnergia() {
        String responseJson = "";
        try {
            responseJson = "{\"data\":\"OK!\",\"informacion\":" + 
            gson.toJson(pes.listAll().toArray()) + "}";            
        } catch (Exception e) {
            e.printStackTrace();
            Object proyectoEnergia = Array.newInstance(ProyectoEnergia.class, 0); 
            responseJson = "{\"data\":\"Error\",\"informacion\":" + 
            gson.toJson(proyectoEnergia) + "}"; 
        }
        return Response.ok(responseJson).build();
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProyectoEnergiaById(@PathParam("id") Integer id) {
        String jsonResponse = "";
        try {
            jsonResponse = "{\"data\":\"OK!\",\"informacion\":" + 
            pes.getProyectoEnergiaJsonById(id) + "}";            
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse = "{\"data\":\"Error\",\"informacion\":\"" + 
            e.getMessage() + "\"}"; 
        }
        return Response.ok(jsonResponse).build();
    }

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProyectoEnergia(String json) {
        String jsonResponse = "";
        try {
            ProyectoEnergia p = gson.fromJson(json, ProyectoEnergia.class);
            pes.setProyectoEnergia(p);
            pes.save();
            jsonResponse = "{\"data\":\"ProyectoEnergia creado!\", \"informacion\" : \n" 
            + pes.toJson() + "}";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            jsonResponse = "{\"data\":\"Error\",\"informacion\":\"" + 
            e.getMessage() + "\"}"; 
        }
        return Response.ok(jsonResponse).build();
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProyectoEnergia(String json) {
        String jsonResponse = "";
        try {
            ProyectoEnergia p = gson.fromJson(json, ProyectoEnergia.class);
            pes.setProyectoEnergia(p);
            Integer id = pes.getProyectoEnergia().getIdProyecto();
            pes.updateProyectoEnergia(id);
            jsonResponse = "{\"data\":\"ProyectoEnergia actualizado!\", \"informacion\" : \n" 
            + pes.toJson() + "}";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            jsonResponse = "{\"data\":\"Error\",\"informacion\":\"" + 
            e.getMessage() + "\"}"; 
        }
        return Response.ok(jsonResponse).build();
    }

    @Path("/delete/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProyectoEnergia(@PathParam("id") Integer id) {
        String jsonResponse = "";
        try {
            pes.deleteProyectoEnergia(id);
            jsonResponse = "{\"data\":\"ProyectoEnergia eliminado!\" }";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            jsonResponse = "{\"data\":\"Error\",\"informacion\":\"" + 
            e.getMessage() + "\"}"; 
        }
        return Response.ok(jsonResponse).build();
    }
}
