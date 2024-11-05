package com.example.controller.dao;

import com.example.models.ProyectoEnergia;
import com.example.controller.dao.implement.AdapterDao;
import com.example.controller.tda.list.LinkedList;


public class ProyectoEnergiaDao extends AdapterDao<ProyectoEnergia>{
    private ProyectoEnergia proyectoEnergia;
    private LinkedList<ProyectoEnergia> listAll;

    public ProyectoEnergiaDao() {
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

    public LinkedList<ProyectoEnergia> getListAll() {
        if(listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = listAll().getSize()+1;
        getProyectoEnergia().setIdProyecto(id);
        this.persist(this.proyectoEnergia);
        this.listAll = listAll();
        return true;
    }

    public String toJson() {
        return g.toJson(getProyectoEnergia());
    }

    public ProyectoEnergia getProyectoEnergiaById(Integer id) throws Exception {
        return get(id);
    }

    public String getProyectoEnergiaJsonById(Integer id) throws Exception {
        return g.toJson(getProyectoEnergiaById(id));
    }

    public void updateProyectoEnergia(Integer id) throws Exception {
        this.merge(this.proyectoEnergia, id);
    }

    public void deleteProyectoEnergia(Integer id) throws Exception {
        this.listAll = listAll();
        delete(id);
    }

}
