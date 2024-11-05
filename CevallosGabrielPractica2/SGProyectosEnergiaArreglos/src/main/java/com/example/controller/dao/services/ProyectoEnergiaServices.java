package com.example.controller.dao.services;
import com.example.models.ProyectoEnergia;

import com.example.controller.dao.ProyectoEnergiaDao;

public class ProyectoEnergiaServices {
    private ProyectoEnergiaDao obj;

    public ProyectoEnergiaServices() {
        obj = new ProyectoEnergiaDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public ProyectoEnergia[] getArray() {
        return obj.getArray();
    }

    public ProyectoEnergia getProyectoEnergia() {
        return obj.getProyectoEnergia();
    }

    public void setProyectoEnergia(ProyectoEnergia proyectoEnergia) {
        obj.setProyectoEnergia(proyectoEnergia);
    }

    public String toJson() throws Exception {
        return obj.toJson();
    }

    public void fromJson(String json) {
        obj.setProyectoEnergia(obj.ProyectoEnergiaFromJson(json));
    }

    public ProyectoEnergia getProyectoEnergiaById(Integer id) throws Exception  {
        return obj.getProyectoEnergiaById(id);
    }

    public String getProyectoEnergiaJsonById(Integer id) throws Exception {
        return obj.getProyectoEnergiaJsonById(id);
    }

    public void updateProyectoEnergia(Integer id) throws Exception {
        obj.updateProyectoEnergia(id);
    }

    public void deleteProyectoEnergia(Integer id) throws Exception {
        obj.deleteProyectoEnergia(id);
    }

}