package com.example.calculadora_testing.dominio.operaciones;

import com.example.calculadora_testing.dominio.OperationException;
import com.example.calculadora_testing.dominio.operaciones.Division;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static java.lang.Double.NaN;
import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class DivisionTest {

    private Operation division;
    @Before
    public void setUp(){
        //El nuevo arrange
        division = new Division();
    }

    //Ejercicio crear un test unitario
    @Test
    public void calculate_division_deberia_regresar_decimal() {
        //Arrange Division operation = new Division();
        double expectedValue = 0.6666;
        //0/0= NaN ok
        //2.1515151515151515; 71.0, 33.0
        // 1.684210526315789; 12.8 , 7.6
        // 3 / 2= 1.5 ok, 2/3= 0.6666666666666666 OK, 71/33 =2.151515151515 OK
        double result;

        //Act
        result = division.calculate(2.0,3.0);
        //Assert
        assertEquals(String.format("Multiplicación %1s ha de ser igual a %2s", result,expectedValue),expectedValue,result, 1e-4);
    }

    //Ejericicio crear un test unitatio con parametros
    @Parameters(method="getValidDiviInput")
    @Test
    public void calculate_division_debe_retornar_cuando_operandos_son_num_reales (double operand1, double operand2, double expectedValue) {

        //Arragne  Division operation = new Division();
        //Act
        double result = division.calculate(operand1,operand2);
        //ASSERT
        assertEquals(String.format("La división %1s ha de ser igual a %2s",result,expectedValue),expectedValue,result,0.0);
    }
    private  Object[] getValidDiviInput(){
        return new Object[]{
                new Object[]{12,12,1},
                new Object[]{8,-2,-4},
                new Object[]{-70,-2,35}};
    }

    //Ejercicio crear un test que capture excepciones paso 2
    @Parameters(method= "getInvalidInput")
    @Test(expected = OperationException.class)
    public void calculate_division_deberia_regresar_num_sin(double operand1, double operand2) {
        //ARRANGE Division operation = new Division();
        //ACT  double result =
                division.calculate(operand1,operand2);
    }
    private Object[] getInvalidInput(){
        return new Object[]{
                new Object[]{12d, Double.MAX_VALUE},
                new Object[]{Double.POSITIVE_INFINITY,1d},
                new Object[]{-12.3,Double.NEGATIVE_INFINITY},
                new Object[]{NaN,12d},
                new Object[]{0,0},//error ya que es un NaN
                new Object[]{1,0},//error ya que es un infinito
                new Object[]{Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY}};
    }

}