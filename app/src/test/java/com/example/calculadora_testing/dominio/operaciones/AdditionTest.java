package com.example.calculadora_testing.dominio.operaciones;

import com.example.calculadora_testing.dominio.OperationException;
import com.example.calculadora_testing.dominio.operaciones.Addition;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class AdditionTest {

    //paso 7 Crear un test que capture excepciones
    private Operation operation;
    @Before
    public void setUp(){
        operation = new Addition();
    }

    //Ejercicio crear un test unitario
    @Test
    public void calculateShouldReturnTenWhenOperandsAreThreeAndSeven() {
        //Arrange
        Addition operation = new Addition();
        double expectedValue = 9;
        double result;

        //Act
        result = operation.calculate(2.0,7.0);
        //Assert
        assertEquals(String.format("Resultado %1s ha de ser igual a %2s", result,expectedValue),expectedValue,result, 0.0);
    }

    //Ejericicio crear un test unitatio con parametros
    @Parameters(method="getValidAdditionInput")
    @Test
    public void calculateShouldReturnExpectedValueWhenOperandsAreRealNumbers (double operand1, double operand2, double expectedValue) {

        //Arragne
        //Addition operation = new Addition();
        //Act
        double result = operation.calculate(operand1,operand2);
        //ASSERT
        assertEquals(String.format("La rta %1s ha de ser igual a %2s",result,expectedValue),expectedValue,result,0.0);
    }
    private  Object[] getValidAdditionInput(){
        return new Object[]{
               new Object[]{1,1,2},
               new Object[]{2,-2,0},
               new Object[]{-3.5,-2.7,-6.2}};
    }

    //Ejercicio crear un test que capture excepciones paso 2
    @Parameters(method= "getInvalidInput")
    @Test(expected = OperationException.class)
    public void calculateShouldThrowsWhenValuesAreInvalid(double operand1, double operand2) {
        //ARRANGE
         Addition operation = new Addition();
        //ACT
        double result = operation.calculate(operand1,operand2);
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