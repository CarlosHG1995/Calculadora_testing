package com.example.calculadora_testing.dominio.operaciones;

import com.example.calculadora_testing.dominio.OperationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class SubtractionTest {

    private Operation resta;
    @Before
    public void setUp(){
        //El nuevo arrange
        resta = new Subtraction();
    }

    @Test
    public void calculate_resta_ShouldReturn_Dos_Cuando_operandos_siete_cinco() {
        //Arrange
         double expectedValue = 2;
        double result;

        //Act
        result = resta.calculate(7.0,5.0);
        //Assert
        assertEquals(String.format("Resultado %1s ha de ser igual a %2s", result,expectedValue),expectedValue,result, 0.0);
    }

    //Ejericicio crear un test unitatio con parametros
    @Parameters(method="getValidAdditionInput")
    @Test
    public void calculate_resta_debe_retornar_cuando_operandos_son_num_reales (double operand1, double operand2, double expectedValue) {

        //Act
        double result = resta.calculate(operand1,operand2);
        //ASSERT
        assertEquals(String.format("La rta %1s ha de ser igual a %2s",result,expectedValue),expectedValue,result,1E-15);
    }
    private  Object[] getValidAdditionInput(){
        return new Object[]{
                new Object[]{1,11,-10},
                new Object[]{2,2,0},
                new Object[]{-3.5,-2.7,-0.8}};
    }

    //Ejercicio crear un test que capture excepciones paso 2
    @Parameters(method= "getInvalidInput")
    @Test(expected = OperationException.class)
    public void calculateShouldThrowsWhenValuesAreInvalid(double operand1, double operand2) {
        //ARRANGE
        //Subtraction operation = new Subtraction();
        //ACT
        double result = resta.calculate(operand1,operand2);
    }
    private Object[] getInvalidInput(){
        return new Object[]{
                new Object[]{12d, Double.MAX_VALUE},
                new Object[]{Double.POSITIVE_INFINITY,1d},
                new Object[]{-12.3,Double.NEGATIVE_INFINITY},
                new Object[]{Double.NaN,12d},
                new Object[]{Math.pow(2,1024),-1d}};
    }

}