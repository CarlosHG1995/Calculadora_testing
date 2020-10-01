package com.example.calculadora_testing.dominio.operaciones;

import com.example.calculadora_testing.dominio.OperationException;

public class Division extends Operation {

    public Division(){
        super(1);
    }

    @Override
    public Double getIdentityElement( ){
        return 1d;
    }

    @Override
    public boolean isBinaryOperation( ){
        return true;
    }

    @Override
    public Double calculate(Double left,Double right) throws OperationException{
        throwsIfValuesAreInvalid(left,right);
        double rta =left/right;
        throwsIfValuesAreInvalid(rta,0d);
        return rta;
    }
}
