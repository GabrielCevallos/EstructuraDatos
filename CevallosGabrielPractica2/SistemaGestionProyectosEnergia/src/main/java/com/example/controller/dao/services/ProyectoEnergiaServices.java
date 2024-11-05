package com.example.controller.dao.services;

import com.example.controller.dao.ProyectoEnergiaDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.ProyectoEnergia;

public class ProyectoEnergiaServices {
    private ProyectoEnergiaDao obj;

    public ProyectoEnergiaServices() {
        obj = new ProyectoEnergiaDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public LinkedList<ProyectoEnergia> listAll() {
        return obj.getListAll();
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
