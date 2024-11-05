package com.example.rest;

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
import com.example.models.Inversionista;
import com.example.models.enumerator.TipoIdentificacion;

@Path("/inversionista")
public class InversionistaApi {
    private InversionistaServices inversionistaServices = new InversionistaServices();
    private Gson gson = new Gson();

    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllInversionistas() {
        String responseJson;
        try {
            responseJson = "{\"data\":\"OK!\",\"informacion\":" + 
            gson.toJson(inversionistaServices.listAll().toArray()) + "}";            
        } catch (ListEmptyException e) {
            Object inversionista = Array.newInstance(Inversionista.class, 0); 
            responseJson = "{\"data\":\"Error\",\"informacion\":" + 
            gson.toJson(inversionista) + "}"; 
            return Response.ok(responseJson).build();
        } catch (Exception e) {
            e.printStackTrace();
            responseJson = "{\"data\":\"Error\",\"informacion\":\"" + e.getMessage() + "\"}";
        }
        return Response.ok(responseJson).build();
    }

    @Path("/type")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getType() {
        String responseJson;
        try {
            responseJson = "{\"data\":\"OK!\",\"informacion\":" + 
            gson.toJson(inversionistaServices.getTipos()) + "}";            
        } catch (ListEmptyException e) {
            Object inversionista = Array.newInstance(Inversionista.class, 0); 
            responseJson = "{\"data\":\"Error\",\"informacion\":\"" + 
            gson.toJson(inversionista) + "\"}"; 
            return Response.ok(responseJson).build();
        } catch (Exception e) {
            e.printStackTrace();
            responseJson = "{\"data\":\"Error\",\"informacion\":\"" + e.getMessage() + "\"}";
        }
        return Response.ok(responseJson).build();
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInversionistaById(@PathParam("id") Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            Inversionista inversionista = inversionistaServices.getInversionistaById(id);
            map.put("msg", "OK");
            map.put("informacion", inversionista);

            if (inversionista.getIdInversionista() == null) {
                map.put("msg", "Error");
                map.put("informacion", "No existe un inversionista con ese identificador");
                return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "Error");
            map.put("informacion", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
        return Response.ok(map).build();
    }

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(String json) {
        String jsonResponse;
        try {
            inversionistaServices.fromJson(json);
            inversionistaServices.save();
            jsonResponse = "{\"data\":\"Inversionista creado!\", \"informacion\" : \n" + 
            inversionistaServices.toJson() + "}";            
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
        String jsonResponse;
        try {
            Inversionista inversionista = gson.fromJson(json, Inversionista.class);
            inversionistaServices.setInversionista(inversionista);
            Integer id = inversionista.getIdInversionista();
            inversionistaServices.updateInversionista(id);
            jsonResponse = "{\"data\":\"Inversionista actualizado!\", \"informacion\" : \n" + 
            inversionistaServices.toJson() + "}";
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
        String jsonResponse;
        Integer id = json.get("id");
        try {
            inversionistaServices.deleteInversionista(id);
            jsonResponse = "{\"data\":\"Inversionista eliminado!\" }";
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse = "{\"data\":\"Error\",\"informacion\":\"" + 
            e.getMessage() + "\"}"; 
        }
        return Response.ok(jsonResponse).build();   
    }
}
