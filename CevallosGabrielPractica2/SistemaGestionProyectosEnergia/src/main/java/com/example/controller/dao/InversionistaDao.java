package com.example.controller.dao;

import com.example.models.Inversionista;
import com.example.models.enumerator.TipoIdentificacion;
import com.example.controller.dao.implement.AdapterDao;
import com.example.controller.tda.list.LinkedList;


public class InversionistaDao extends AdapterDao<Inversionista>{
    private Inversionista inversionista;
    private LinkedList<Inversionista> listAll;

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

    public LinkedList<Inversionista> getListAll() {
        if(listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public Inversionista getInversionistaById(Integer id) throws Exception {
        return get(id);
    }

    public String getInversionistaJsonById(Integer id) throws Exception {
        return g.toJson(getInversionistaById(id));
    }

    public Boolean save() throws Exception {
        Integer id = listAll().getSize()+1;
        getInversionista().setIdInversionista(id);
        this.persist(this.inversionista);
        this.listAll = listAll();
        return true;
    }

    public void updateInversionista(Integer id) throws Exception {
        this.merge(this.inversionista, id);
    }

    public void deleteInversionista(Integer id) throws Exception {
        this.listAll = listAll();
        delete(id);
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