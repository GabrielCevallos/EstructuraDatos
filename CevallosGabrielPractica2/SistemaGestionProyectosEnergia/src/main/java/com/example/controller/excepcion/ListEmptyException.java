package com.example.controller.excepcion;

public class ListEmptyException extends Exception {
    public ListEmptyException() {}
    public ListEmptyException(String msg) {
        super(msg);
    }    
}