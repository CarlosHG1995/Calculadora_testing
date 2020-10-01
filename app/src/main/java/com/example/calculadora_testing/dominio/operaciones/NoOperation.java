package com.example.calculadora_testing.dominio.operaciones;

import com.example.calculadora_testing.dominio.OperationException;

public class NoOperation extends Operation {

    public NoOperation(){
        super(-1);
    }

    @Override
    public Double getIdentityElement( ){
        return 0.0;
    }

    @Override
    public boolean isBinaryOperation( ){
        return false;
    }

    @Override
    public Double calculate(Double left,Double right){// throws OperationException{
        return 0d;
    }
}
