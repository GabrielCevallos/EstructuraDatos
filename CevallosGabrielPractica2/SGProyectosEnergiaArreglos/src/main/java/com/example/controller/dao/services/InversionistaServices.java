package com.example.controller.dao.services;
import com.example.models.Inversionista;
import com.example.models.enumerator.TipoIdentificacion;

import com.example.controller.dao.InversionistaDao;

public class InversionistaServices {
    private InversionistaDao obj;

    public InversionistaServices() {
        obj = new InversionistaDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public Inversionista[] getArray() {
        return obj.getArray();
    }

    public Inversionista getInversionista() {
        return obj.getInversionista();
    }

    public void setInversionista(Inversionista inversionista) {
        obj.setInversionista(inversionista);
    }

    public String toJson() throws Exception {
        return obj.toJson();
    }

    public void fromJson(String json) {
        obj.setInversionista(obj.InversionistaFromJson(json));
    }

    public Inversionista getInversionistaById(Integer id) throws Exception  {
        return obj.getInversionistaById(id);
    }

    public String getInversionistaJsonById(Integer id) throws Exception {
        return obj.getInversionistaJsonById(id);
    }

    public void updateInversionista(Integer id) throws Exception {
        obj.updateInversionista(id);
    }

    public void deleteInversionista(Integer id) throws Exception {
        obj.deleteInversionista(id);
    }

    public TipoIdentificacion getTipoIdentificacion(String tipo) {
        return obj.getTipoIdentificacion(tipo);
    }

    public TipoIdentificacion[] getTipos() {
        return obj.getTipos();
    }

}