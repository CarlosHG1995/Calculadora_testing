package com.example.calculadora_testing.dominio.operaciones;

import com.example.calculadora_testing.dominio.OperationException;

public class Factorial extends Operation {

    public Factorial(){
        super(3);
    }

    @Override
    public Double getIdentityElement( ){
        return 1.0;
    }

    @Override
    public boolean isBinaryOperation( ){
        return false;
    }

    @Override
    public Double calculate(Double left,Double right) throws OperationException{

        throwsIfValuesAreInvalid(right);

        if (right < 0)
            throw new OperationException("Resultado no es el esperado");

        double result = 1;

        while (right != 0) {
            result = new Multiplicacion().calculate(result, right);
            right--;
        }

        return result;
        /*double result = 1;
        throwsIfValuesAreInvalid(result);
        if(right %1==0){
        if(right<0 ){
            throw  new OperationException("Resultado no es el esperado");
            result = 1;
        }
        else if(  right == 0){
            result = 1.0;
        }
        else{
            for (int n=1; n<=right;n++){
                result = result*n;
                throwsIfValuesAreInvalid(result);
            }
          }
        }
        return result;*/
    }
        /*if(right >=0){
        while (right>0){
            result *= right;
            right--;
        }
            return result;
        }
        else{

        }
        return result;
    }*/
}
