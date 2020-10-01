package com.example.calculadora_testing.dominio.operaciones;

import com.example.calculadora_testing.dominio.OperationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.*;
import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnitParamsRunner.class)
public class ExponentationTest {

    private Operation exponentation;
    @Before
    public void setUp(){
        //El nuevo arrange
        exponentation = new Exponentation();
    }

    @Test
    public void calculate_exponentation_ShouldReturn() {
        //Arrange
        double expectedValue = 7.625597484987E12;//3.2
        double result = 0.0;

        //Act
        result = exponentation.calculate(3.0,27.0);//10.24
        //Assert
        assertEquals(String.format("Resultado %1s ha de ser igual a %2s", result,expectedValue),expectedValue,result, 1e-5);
    }

    //Ejericicio crear un test unitario con parametros
    @Parameters(method="getValidExpoInput")
    @Test//(timeout=15000)
    public void calculate_exponentation_debe_retornar_cuando_operandos_son_num_reales (double operand1, double operand2, double expectedValue) {

        //Act
        double result = exponentation.calculate(operand1,operand2);
        //ASSERT
        assertEquals(String.format("La exponenciación %1s ha de ser igual a %2s",result,expectedValue),expectedValue,result, 1e-4);//cuando el delta es 1E-9

    }
    private  Object[] getValidExpoInput(){
        return new Object[]{
                new Object[]{2,3,8},
                new Object[]{0,1E3,0},
                new Object[]{144,14,1.6484466236095125E30},
                new Object[]{-10,3,-1000},
                new Object[]{2.3654,-3,0.07555},
                new Object[]{3,4,81}
                };
    }

    //Ejercicio crear un test que capture excepciones paso 2
    @Parameters(method= "getValidExponentiationInput")
    @Test
    public void calculateShouldThrowsWhenValuesAreIntegers(double base, double expon,double expectedValue) {
        //ARRANGE
        //Exponentation operation = new Exponentation();
        //ACT
        double result = exponentation.calculate(base,expon);
        //assertThat(result,equalTo(expectedValue)); funciona pero 2.3 los decimales deben ser más
        assertThat(result).isWithin(1e-1).of(expectedValue);
    }
    private Object[] getValidExponentiationInput(){
        return $(
//                $(0, 1E9, 0),
                $(2, 0, 1),
                $(2, 1, 2),
                $(2.3, 5, 64.36343),
               // $(9, 0.36, 2.20560),//2999999998
                $(-3, 4, 81),
                $(-3, 3, -27),// $(Double.MAX_VALUE,2,Infinity)
                $(2, -2, 0.25), //Añade este elemento
                $(-3, -5, -0.00411522633)
                );
    }

    //Ejercicio: Completar la clase Exponentiation
    @Parameters(method = "getInvalidExponentiationInput")
    @Test(expected = OperationException.class)
    public void calculateShouldThrowWhenInputIsInvalid(double base, double exponent) {
        exponentation.calculate(base, exponent);
    }
    private Object[] getInvalidExponentiationInput() {
        return $(
                $(0, 0),
                $(5, 3.3), //Añade este elemento
                $(-3, -1.2),
                $(Double.NEGATIVE_INFINITY, 2),
                $(-3, Double.POSITIVE_INFINITY),
                $(Double.NaN, -1),
                $(2, Double.MAX_VALUE),
                $(2, 1024),
                $(2, -1024),
                $(0, 1E9),
                $(1, 1E9)
                //$(0, 1E9),
                //$(1, 1E9)
                );
    }

    @Parameters(method = "getNullInput")
    @Test(expected = OperationException.class)
    public void calculateShouldThrowsWhenValuesAreNull(Double base, Double exponent) {
        exponentation.calculate(base, exponent);
    }

    private Object[] getNullInput() {
        return new Object[]{
                new Object[]{null, 5.0},
                new Object[]{null, null},
                new Object[]{10.0, null}
        };
    }

}