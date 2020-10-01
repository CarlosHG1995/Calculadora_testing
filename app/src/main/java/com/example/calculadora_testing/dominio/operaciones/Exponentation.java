package com.example.calculadora_testing.dominio.operaciones;

import com.example.calculadora_testing.dominio.OperationException;

public class Exponentation extends Operation {

    public Exponentation(){
        super(2);
    }

    @Override
    public Double calculate(Double base,Double exponent) {
        throwsIfValuesAreInvalid(base,exponent);
        if(exponent > Double.MAX_EXPONENT || exponent <Double.MIN_EXPONENT){
            throw new OperationException("Exponente fuera del alcance");
        }
        if(base==0 && exponent <=0) {
            throw  new OperationException("Error math");
        }

        if((base == 0 || base==1)&& exponent !=0) return base;
        double result =1;
        boolean expoNegativo = exponent <0;
        if(expoNegativo)
            exponent = -exponent;
        //if(base == 0 && exponent !=0){ result = 0;  }
        //if(base == 1){ result =1; }
        while (exponent != 0){
            result = result *base;
            exponent--;
           throwsIfValuesAreInvalid(result);
        }
        return expoNegativo ? (1/result): result;
    }

    @Override
    public Double getIdentityElement( ){
        return 1d;
    }

    @Override
    public boolean isBinaryOperation( ){
        return true;
    }
}
