package com.example.controller.dao.implement;

import com.example.controller.tda.list.LinkedList;

public interface InterfazDao<T> {
    public LinkedList<T> listAll();
    public void merge(T object, Integer index) throws Exception;
    public T get(Integer id) throws Exception;
    public void persist(T object) throws Exception;
} 
