package com.example.calculadora_testing.dominio.operaciones;

import com.example.calculadora_testing.dominio.OperationException;

public class Multiplicacion extends Operation {

    public Multiplicacion(){
        super(1);
    }

    @Override
    public Double getIdentityElement( ){
        return 1.0;
    }

    @Override
    public boolean isBinaryOperation( ){
        return true;
    }

    @Override
    public Double calculate(Double left,Double right) throws OperationException{
        throwsIfValuesAreInvalid(left,right);
        double result = left*right;
        throwsIfValuesAreInvalid(result);
        return result;
    }
}
