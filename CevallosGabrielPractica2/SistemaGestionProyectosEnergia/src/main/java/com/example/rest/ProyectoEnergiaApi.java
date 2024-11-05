package com.example.rest;

import java.lang.reflect.Array;

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
import com.example.controller.excepcion.ListEmptyException;
import com.example.models.ProyectoEnergia;;

@Path("/proyectoEnergia")

public class ProyectoEnergiaApi {
    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public Response getAllProyectoEnergia() throws ListEmptyException {
        String responseJson = "";
        ProyectoEnergiaServices pes = new ProyectoEnergiaServices();
        Gson gson = new Gson();
        
        try {
            responseJson = "{\"data\":\"OK!\",\"informacion\":" + 
            gson.toJson(pes.listAll().toArray()) + "}";            
        } catch (Exception e) {
            e.printStackTrace();
            Object proyectoEnergia = Array.newInstance(ProyectoEnergia.class, 0); 
            responseJson = "{\"data\":\"Error\",\"informacion\":" + 
            gson.toJson(proyectoEnergia) + "}"; 
            
            if(e.getClass().equals(ListEmptyException.class)) {
                return Response.ok(responseJson).build();
            } 
        }

        return Response.ok(responseJson).build();
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public Response getProyectoEnergiaById(@PathParam("id") Integer id) {
        String jsonResponse = "";
        ProyectoEnergiaServices pes = new ProyectoEnergiaServices();
        
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
        ProyectoEnergiaServices pes = new ProyectoEnergiaServices();
        Gson gson = new Gson();
        try {
            ProyectoEnergia p = gson.fromJson(json,ProyectoEnergia.class);
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
        ProyectoEnergiaServices pes = new ProyectoEnergiaServices();
        Gson gson = new Gson();
        try {
            ProyectoEnergia p = gson.fromJson(json,ProyectoEnergia.class);
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
        ProyectoEnergiaServices pes = new ProyectoEnergiaServices();
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