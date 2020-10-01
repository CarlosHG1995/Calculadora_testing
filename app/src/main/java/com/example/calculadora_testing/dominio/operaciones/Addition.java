package com.example.calculadora_testing.dominio.operaciones;

import com.example.calculadora_testing.dominio.OperationException;

public class Addition extends Operation{

    public Addition(){
        super(0);
    }

    @Override
    public Double getIdentityElement(){
       //double a=0;
        return 0d;
    }

    @Override
    public boolean isBinaryOperation( ){
        return true;
    }

    @Override
    public Double calculate(Double left, Double right) throws OperationException{
        throwsIfValuesAreInvalid(left, right);
        return left+right;
    }

}












