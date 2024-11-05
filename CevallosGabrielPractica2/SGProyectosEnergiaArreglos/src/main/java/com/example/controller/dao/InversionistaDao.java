package com.example.controller.dao;

import com.example.controller.dao.implement.InterfazDao;
import com.example.models.enumerator.TipoIdentificacion;
import com.example.controller.dao.implement.AdapterDao;

import com.google.gson.Gson;

import com.example.models.Inversionista;

public class InversionistaDao extends AdapterDao<Inversionista> {
    private Inversionista inversionista;
    private Inversionista[] arrayAll;

    public InversionistaDao() {
        super(Inversionista.class);
    }

    public Inversionista getInversionista() {
        if(this.inversionista == null) {
            this.inversionista = new Inversionista();
        }
        return this.inversionista;
    }

    public void setInversionista(Inversionista inversionista) {
        this.inversionista = inversionista;
    }

    public Inversionista[] getArray() {
        if(arrayAll == null) {
            this.arrayAll = getArray();
        }
        return this.arrayAll;
    }

    public Boolean save() throws Exception {
        Integer id = getArray().getSize()+1;
        getInversionista().setIdInversionista(id);
        this.persist(this.inversionista);
        this.arrayAll = getArray();
        return true;
    }

    public void updateInversionista(Integer id) throws Exception {
        this.merge(this.inversionista, id);
    }

    public void deleteInversionista(Integer id) throws Exception {
        this.arrayAll = getArray();
        delete(id, g.toJson(getArray()));
    }

    public String toJson() {
        return g.toJson(getInversionista());
    }

    public Inversionista InversionistaFromJson(String json) {
        return g.fromJson(json, Inversionista.class);
    }

    public TipoIdentificacion getTipoIdentificacion(String tipo) {
        return TipoIdentificacion.valueOf(tipo);
    }

    public TipoIdentificacion[] getTipos() {
        return TipoIdentificacion.values();
    }

}   


