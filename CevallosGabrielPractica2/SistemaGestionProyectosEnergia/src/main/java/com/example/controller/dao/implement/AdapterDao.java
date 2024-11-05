package com.example.controller.dao.implement;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.Scanner;
import com.google.gson.Gson;

import com.example.controller.tda.list.LinkedList;

public class AdapterDao<T> implements InterfazDao<T> {
    private Class<?> clazz;
    protected Gson g;
    public String URL = "./media/";

    public AdapterDao(Class<?> clazz) {
        this.clazz = clazz;
        this.g = new Gson();
    }

    public void persist(T object) throws Exception {
        LinkedList<T> list = listAll();
        list.add(object);
        String info = " ";
        try{
            info = g.toJson(list.toArray());
        } catch (Exception e){
            e.printStackTrace();
        }
        saveFile(info);
    }

    public void merge(T object, Integer index) throws Exception {
        LinkedList<T> list = listAll();
        list.update(object, index - 1);
        String info = "";
        try {
            info = g.toJson(list.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        saveFile(info);
    }
    
    public void delete(Integer id) throws Exception {
        LinkedList<T> list = listAll();
        list.delete(id - 1);
        saveFile(g.toJson(list.toArray()));
    }

    public LinkedList<T> listAll() {
        LinkedList<T> list = new LinkedList<>();
        try {
            String data = readFile();
            Type arrayType = Array.newInstance(clazz, 0).getClass();
            T[] arrayObjects = g.fromJson(data, arrayType);
            list = list.toList(arrayObjects);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    } 

    public T get(Integer id) throws Exception {
        return this.listAll().get(id-1);
    }

    private String readFile() throws Exception {
        Scanner in = new Scanner(new FileReader(URL+clazz.getSimpleName()+".json"));
        StringBuilder sb = new StringBuilder();
        while(in.hasNext()) {
            sb.append(in.next());
        }
        in.close();
        return sb.toString();
    }

    private void saveFile(String data) throws Exception {
        FileWriter f = new FileWriter(URL+clazz.getSimpleName()+".json");
        f.write(data);
        f.flush();
        f.close();
    }
}
