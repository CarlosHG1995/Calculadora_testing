package com.example.calculadora_testing.dominio.operaciones;

import com.example.calculadora_testing.dominio.OperationException;
import com.example.calculadora_testing.dominio.operaciones.Factorial;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static com.google.common.truth.Truth.assertThat;
import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class FactorialTest {

    private Operation factorial;
    @Before
    public void setUp(){
        //El nuevo arrange
        factorial = new Factorial();
    }

    @Parameters(method="getValidAdditionInput")
    @Test
    public void calculate_factorial_debe_retornar_cuando_operandos_son_num_naturales (double operand1, double operand2, double expectedValue) {

        //Act
        double result = factorial.calculate(operand1,operand2);
        //ASSERT
        assertEquals(String.format("El factorial %1s ha de ser igual a %2s",result,expectedValue),expectedValue,result,1E-15);
    }
    private  Object[] getValidAdditionInput(){
        return new Object[]{
                new Object[]{0,0,1},
                new Object[]{0,1,1},
                new Object[]{0,5,120},
                new Object[]{0,9,362880},
                new Object[]{0,11,39916800},
                new Object[]{0,14,87178291.2E3}
               //new Object[]{0,Double.MAX_VALUE,Double.MAX_VALUE} esto pausará la app
        }; //  new Object[]{0,900,6.7526802209645841583879061361801} da infinity
    }

    @Parameters(method= "getInValidFactorialInput")
    @Test(timeout=1000)
    public void calculateShouldThrowsWhenValuesAreIsInvalid(double factor) {
        //ARRANGE
        //Factorial operation = new Factorial();
        //ACT
        try{
            double result = factorial.calculate(1d,factor);
            Assert.fail("Este factorial lanza un excepcion de error");
        }catch(OperationException ex){
            assertThat(ex).isNotNull();
            System.out.println("En Factorial, OperationExcepcion ha sido enviado");
        }

    }
    private Object[] getInValidFactorialInput(){
        double r = 0;
        return $(
                $( -2), //Añade este elemento esto debe ser error al igual que el siguiente
                $( -5),
                $(1.4), //este y el siguiente no deben realizarse
                $(Double.NaN),//ya que el factorial es numeros enteros positivos de 0, infinito
                $(Double.MAX_EXPONENT),//el resultado es infinito pero el throwsIfValuesAreInvalid en el for no me funciona
                $(Double.MAX_VALUE) //rta es infinito pero el throwsIfValuesAreInvalid en el for no me funciona
                );
    }

    @Test(expected=OperationException.class)
    public  void calculate_cuando_son_null(){
        factorial.calculate(null,null);
    }

}