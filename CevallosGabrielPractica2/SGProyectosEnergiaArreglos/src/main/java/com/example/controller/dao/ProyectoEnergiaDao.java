package com.example.controller.dao;

import com.example.controller.dao.implement.InterfazDao;
import com.example.controller.dao.implement.AdapterDao;

import com.google.gson.Gson;

import com.example.models.ProyectoEnergia;

public class ProyectoEnergiaDao extends AdapterDao<ProyectoEnergia> {
    private ProyectoEnergia proyectoEnergia;
    private ProyectoEnergia[] arrayAll;

    public ProyectoEnergiaDao () {
        super(ProyectoEnergia.class);
    }

    public ProyectoEnergia getProyectoEnergia() {
        if(this.proyectoEnergia == null) {
            this.proyectoEnergia = new ProyectoEnergia();
        }
        return this.proyectoEnergia;
    }

    public void setProyectoEnergia(ProyectoEnergia proyectoEnergia) {
        this.proyectoEnergia = proyectoEnergia;
    }

    public ProyectoEnergia[] getArray() {
        if(arrayAll == null) {
            this.arrayAll = arrayAll();
        }
        return this.arrayAll;
    }

    public Boolean save() throws Exception {
        Integer id = arrayAll().getSize()+1;
        getProyectoEnergia().setIdProyectoEnergia(id);
        this.persist(this.proyectoEnergia);
        this.arrayAll = arrayAll();
        return true;
    }

    public void updateProyectoEnergia(Integer id) throws Exception {
        this.merge(this.proyectoEnergia, id);
    }

    public void deleteProyectoEnergia(Integer id) throws Exception {
        this.arrayAll = arrayAll();
        delete(id, g.toJson(arrayAll()));
    }

    public String toJson() {
        return g.toJson(getProyectoEnergia());
    }

    public ProyectoEnergia ProyectoEnergiaFromJson(String json) {
        return g.fromJson(json, ProyectoEnergia.class);
    }

}


