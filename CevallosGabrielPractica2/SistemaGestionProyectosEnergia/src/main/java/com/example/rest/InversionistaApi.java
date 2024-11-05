package com.example.rest;

/* import java.io.ObjectInputFilter.Status; */
import java.lang.reflect.Array;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;

import com.example.controller.dao.services.InversionistaServices;
import com.example.controller.excepcion.ListEmptyException;
import com.example.models.Inversionista;


@Path("/inversionista")
public class InversionistaApi {
    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllInversionistas() throws ListEmptyException {
        String responseJson = "";
        InversionistaServices is = new InversionistaServices();
        Gson gson = new Gson();
        
        try {
            responseJson = "{\"data\":\"OK!\",\"informacion\":" + 
            gson.toJson(is.listAll().toArray()) + "}";            
        } catch (Exception e) {
            e.printStackTrace();
            Object inversionista = Array.newInstance(Inversionista.class, 0); 
            responseJson = "{\"data\":\"Error\",\"informacion\":" + 
            gson.toJson(inversionista) + "}"; 
            
            if(e.getClass().equals(ListEmptyException.class)) {
                return Response.ok(responseJson).build();
            }
        }

        return Response.ok(responseJson).build();
    }

    @Path("/type")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getType() throws ListEmptyException {
        String responseJson = "";
        InversionistaServices is = new InversionistaServices();
        Gson gson = new Gson();
        
        try {
            responseJson = "{\"data\":\"OK!\",\"informacion\":" + 
            gson.toJson(is.getTipos()) + "}";            
        } catch (Exception e) {
            e.printStackTrace();
            Object inversionista = Array.newInstance(Inversionista.class, 0); 
            responseJson = "{\"data\":\"Error\",\"informacion\":\"" + 
            gson.toJson(inversionista) + "\"}"; 
            
            if(e.getClass().equals(ListEmptyException.class)) {
                return Response.ok(responseJson).build();
            }
        }

        return Response.ok(responseJson).build();
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInversionistaById(@PathParam("id") Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        InversionistaServices is = new InversionistaServices();
        try {
            is.setInversionista(is.getInversionistaById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("msg", "OK");
        map.put("informacion", is.getInversionista());

        if(is.getInversionista().getIdInversionista() == null) {
            map.put("msg", "Error");
            map.put("informacion", "No existe un inversionista con ese identificador");
            return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
        }
        return Response.ok(map).build();

    }
    
    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public Response save(String json) {
        String jsonResponse = "";
        InversionistaServices is = new InversionistaServices();
                      
        try {
            is.fromJson(json);
            is.save();
            jsonResponse = "{\"data\":\"Inversionista creado!\", \"informacion\" : \n" + 
            is.toJson() + "}";            
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse = "{\"data\":\"Error\",\"informacion\":\"" + 
            e.getMessage() + "\"}"; 
        }

        return Response.ok(jsonResponse).build();
    }
    
    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response update(String json) {
        String jsonResponse = "";
        InversionistaServices is = new InversionistaServices();
        Gson gson = new Gson();
        try {
            Inversionista inversionista = gson.fromJson(json, Inversionista.class);
            is.setInversionista(inversionista);
            Integer id = is.getInversionista().getIdInversionista();
            is.updateInversionista(id);
            jsonResponse = "{\"data\":\"Inversionista actualizado!\", \"informacion\" : \n" + 
            is.toJson() + "}";
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse = "{\"data\":\"Error\",\"informacion\":\"" + 
            e.getMessage() + "\"}"; 
        }

        return Response.ok(jsonResponse).build();

    }

    @Path("/delete")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(HashMap<String, Integer> json) {
        String jsonResponse = "";
        InversionistaServices is = new InversionistaServices();
        Integer id = json.get("id");
        
        try {
            is.deleteInversionista(id);
            jsonResponse = "{\"data\":\"Inversionista eliminado!\" }";
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse = "{\"data\":\"Error\",\"informacion\":\"" + 
            e.getMessage() + "\"}"; 
        }

        return Response.ok(jsonResponse).build();   
    }
    
}    

    
