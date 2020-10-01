package com.example.calculadora_testing.dominio;

public class ExpressionException extends RuntimeException {

    public ExpressionException(){}

    public ExpressionException(String message){
        super(message);
    }

}
