package com.example.calculadora_testing.dominio.operaciones;

public class Subtraction extends Operation {

    public Subtraction(){
        super(0);
    }

    @Override
    public Double getIdentityElement( ){
        return 0.0;
    }

    @Override
    public boolean isBinaryOperation( ){
        return true;
    }

    @Override
    public Double calculate(Double n1, Double n2){//throws  OperationException{
       throwsIfValuesAreInvalid(n1,n2);
       return n1 - n2;
     }

}
