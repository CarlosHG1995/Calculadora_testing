package com.example.calculadora_testing.dominio.operaciones;

import android.os.Build;

import com.example.calculadora_testing.dominio.OperationException;

public abstract class Operation implements Comparable<Operation> {

    public abstract  Double getIdentityElement();
    public abstract boolean isBinaryOperation();

    public abstract Double calculate(Double left, Double right) throws OperationException;

    protected void throwsIfValuesAreInvalid(Double... values) throws OperationException{
        for (Double value: values){
        if(value == null ||value == Double.MAX_VALUE || Double.isInfinite(value)|| Double.isNaN(value))
        throw  new OperationException();
        }
    }//

    //Ejercicio: Crear la clase MathResolver paso 7 pag 42
    private int precedence;
    public Operation(int precedence){
        this.precedence = precedence;
    }

    @Override
    public int compareTo(Operation other){
        /*
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT){
            return Integer.compare(precedence,other.precedence);
        }*/
       return Integer.compare(precedence,other.precedence);
    }
}
