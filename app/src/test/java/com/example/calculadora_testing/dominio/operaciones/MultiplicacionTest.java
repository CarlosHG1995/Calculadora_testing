package com.example.calculadora_testing.dominio.operaciones;

import com.example.calculadora_testing.dominio.OperationException;
import com.example.calculadora_testing.dominio.operaciones.Multiplicacion;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class MultiplicacionTest {

    private Operation producto;
    @Before
    public void setUp(){
        //El nuevo arrange
        producto = new Multiplicacion();
    }

    //Ejercicio crear un test unitario
    @Test
    public void calculate_producto_deberia_regresar_decimal() {
        //Arrange
        //Multiplicacion operation = new Multiplicacion();
        double expectedValue = 97.28;
        double result = 0.0;

        //Act
        result = producto.calculate(12.8,7.6);
        //Assert
        assertEquals(String.format("Multiplicación %1s ha de ser igual a %2s", result,expectedValue),expectedValue,result, 0.0);
    }

    //Ejericicio crear un test unitatio con parametros
    @Parameters(method="getValidMultiInput")
    @Test
    public void calculate_producto_debe_retornar_cuando_operandos_son_num_reales (double operand1, double operand2, double expectedValue) {

        //Arragne
        //Multiplicacion operation = new Multiplicacion();
        //Act
        double result = producto.calculate(operand1,operand2);
        //ASSERT
        assertEquals(String.format("El producto %1s ha de ser igual a %2s",result,expectedValue),expectedValue,result,0.0);
    }
    private  Object[] getValidMultiInput(){
        return new Object[]{
                new Object[]{12,12,144},
                new Object[]{2,-2,-4},
                new Object[]{-35,-27,945}};
    }

    //Ejercicio crear un test que capture excepciones paso 2
    @Parameters(method= "getInvalidInput")
    @Test(expected = OperationException.class)
    public void calculate_producto_deberia_regresar_num_sin(double operand1, double operand2) {
        //ARRANGE
        //Multiplicacion operation = new Multiplicacion();
        //ACT
        double result = producto.calculate(operand1,operand2);
    }
    private Object[] getInvalidInput(){
        return new Object[]{
                new Object[]{12d, Double.MAX_VALUE},
                new Object[]{Double.POSITIVE_INFINITY,1d},
                new Object[]{-12.3,Double.NEGATIVE_INFINITY},
                new Object[]{Double.NaN,12d},
                new Object[]{Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY}};
    }


}