package com.example.calculadora_testing.dominio.operaciones;

import com.example.calculadora_testing.dominio.OperationException;

public class SquareRoot extends Operation {

    public SquareRoot(){
        super(3);
    }

    @Override
    public Double calculate(Double left,Double radicand) {
        throwsIfValuesAreInvalid(1d,radicand);
       double aux= 1;
       double sqrt_ = radicand/2;
       if(radicand<0) {
           throw  new OperationException();//aquí son raíces complejas
        //x=0;
       }
       else if(radicand ==0){
           return 0d;
       }
        do{
            aux = sqrt_;
            sqrt_ =(aux+(radicand/aux))/2;
            throwsIfValuesAreInvalid(sqrt_,0d);
        }while (aux != sqrt_);

       /*if(radicand>=0){

           for (sqrt_=1; sqrt_ < 1000; sqrt_++) {
               aux=(aux + radicand / aux) / 2;
               throwsIfValuesAreInvalid(sqrt_,0d);
           }
       }
        double sqrt_;

       if(radicand >=0){

       }else {
           sqr =0;
       }*/
       return sqrt_;
    }
    @Override
    public Double getIdentityElement( ){
        return 1.0;
    }

    @Override
    public boolean isBinaryOperation( ){
        return false;
    }
}
