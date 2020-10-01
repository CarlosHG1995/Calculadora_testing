package com.example.calculadora_testing.dominio.operaciones;

import com.example.calculadora_testing.dominio.OperationException;
import com.example.calculadora_testing.dominio.operaciones.SquareRoot;

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
public class SquareRootTest {

    private Operation raiz_cuadrada;
    @Before
    public void setUp(){
        //El nuevo arrange
        raiz_cuadrada = new SquareRoot();
    }

    @Test
    public void calculate_raiz_cuadrada_ShouldReturn_decimal_cuando_es_veintiseite() {
        //Arrange
        double expectedValue = 5.196152422706632;//3.2
        double result = 0.0;

        //Act
        result = raiz_cuadrada.calculate(0.0,27.0);//10.24
        //Assert
        assertEquals(String.format("Resultado %1s ha de ser igual a %2s", result,expectedValue),expectedValue,result, 1E-15);
    }

    //Ejericicio crear un test unitatio con parametros
    @Parameters(method="getValidAdditionInput")
    @Test(timeout=100)
    public void calculate_raiz_cuadrada_debe_retornar_cuando_operandos_son_num_reales (double operand2, double expectedValue) {

        //Act
        double result = raiz_cuadrada.calculate(1d,operand2);
        //ASSERT
        assertEquals(String.format("La raíz cuadrada %1s ha de ser igual a %2s",result,expectedValue),expectedValue,result,1.0e-3);//el delta cambio a 1E-17
    }
    private  Object[] getValidAdditionInput(){
        return new Object[]{
                new Object[]{121,11},
                new Object[]{0,0},
                new Object[]{4,2},
                new Object[]{89E6,9433.981},
                new Object[]{1.253,1.119},
                new Object[]{81,9},
                //new Object[]{0,Double.MAX_VALUE,1.34E154},
                new Object[]{Double.MAX_EXPONENT,31.984},
                new Object[]{Double.MIN_NORMAL, 1.4916E-154} }; //new Object[]{0,Double.MIN_VALUE,Infinity}
    }

    //Ejercicio crear un test que capture excepciones paso 2
   /**/
    @Parameters(method= "getInvalidInputSR")
    @Test(expected = OperationException.class)
    public void calculateShould_square_root_AreInvalid(double operand2) {
       //ACT
         double result = raiz_cuadrada.calculate(1d,operand2);
        //ASSERT
        //assertEquals(String.format("La raíz cuadrada %1s ha de ser igual a %2s",result,expectedValue),expectedValue,result,1.0e-4);//el delta cambio a 1E-17

    }
    private Object[] getInvalidInputSR(){
        return $(
                $(-121),
                $(Double.NaN), // OJO si pongo ambos pasa el timeout y dañan  -->0/0
                $(Double.POSITIVE_INFINITY), //  -->1.0 / 0.0
                $(-2.5)
                );
    }

   /**/ @Test(expected = OperationException.class)
    public void Lanzar_excepciones_son_null() {
        raiz_cuadrada.calculate(1d,null);
    }


}