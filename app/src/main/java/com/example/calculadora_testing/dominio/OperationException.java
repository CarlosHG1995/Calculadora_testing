package com.example.calculadora_testing.dominio;

public class OperationException extends RuntimeException {
    //Ejercicio crear un test que capture excepciones
    public OperationException(){}
    public OperationException(String message){
        super(message);
    }
}
