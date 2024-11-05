package com.example.controller.dao.implement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.example.controller.dao.implement.InterfazDao;

public class AdapterDao<T> implements InterfazDao<T> {
    private Class<?> clazz;
    protected Gson gson;
    public static String URL = "./media/";
    
    public AdapterDao(Class<?> clazz) {
        this.clazz = clazz;
    }

    public T[] getArray() throws Exception {
        gson = new Gson();        
        try {
            String data = readFile();
            Type arrayType = Array.newInstance(clazz,0).getClass();
            @SuppressWarnings("unchecked")
            T[] objects = (gson.fromJson(data, arrayType) != null) ? gson.fromJson(data, arrayType) : (T[])Array.newInstance(clazz, 0);
            return objects;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void merge(T object, Integer index) throws Exception {
        T[] objects = getArray();
        objects[index-1] = object;
        saveFile(objects);
    }

    public  T get(Integer index) throws Exception {
        T[] objects = getArray();
        return objects[index-1];
    }

    public void persist(T object) throws Exception {
        T[] objects = getArray();
    
        @SuppressWarnings("unchecked")
        T[] newObjects = (T[]) Array.newInstance(clazz, objects.length + 1);

        for(int i = 0; i < objects.length; i++) {
            newObjects[i] = objects[i];
        }
    
        newObjects[objects.length] = object;
    
        saveFile(newObjects);
    }

    public void delete(Integer index) throws Exception {
        T[] objects = getArray();
        final Integer ID = index-1;

        @SuppressWarnings("unchecked")
        T[] newObjects = (T[])Array.newInstance(clazz, objects.length-1);

        for(int i = 0; i < objects.length; i++) {
            if(i < ID) {
                newObjects[i] = objects[i];
            } else if(i > ID) {
                newObjects[i - 1] = objects[i];
            }
        }
        
        saveFile(newObjects);
    }

    public void saveFile(T[] object) throws Exception {
        String data = gson.toJson(object);
        
        FileWriter f = new FileWriter(URL+clazz.getSimpleName()+".json");
        f.write(data);
        f.flush();
        f.close();
    }

    public String readFile() throws Exception {
        Scanner in = new Scanner(new FileReader(URL+clazz.getSimpleName()+".json"));
        StringBuilder sb = new StringBuilder();
        while(in.hasNext()) {
            sb.append(in.next());
        }
        in.close();
        return sb.toString();

    }

}


    


    


    

 

  

    




    