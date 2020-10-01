package com.example.calculadora_testing.dominio;

import com.example.calculadora_testing.dominio.operaciones.AdditionTest;
import com.example.calculadora_testing.dominio.operaciones.DivisionTest;
import com.example.calculadora_testing.dominio.operaciones.ExponentationTest;
import com.example.calculadora_testing.dominio.operaciones.FactorialTest;
import com.example.calculadora_testing.dominio.operaciones.MultiplicacionTest;
import com.example.calculadora_testing.dominio.operaciones.SquareRootTest;
import com.example.calculadora_testing.dominio.operaciones.SubtractionTest;
import com.example.calculadora_testing.presentacion.CalculatorPresenterTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
                     AdditionTest.class,
                     SubtractionTest.class,
                     MultiplicacionTest.class,
                     DivisionTest.class,
                     ExponentationTest.class,
                     FactorialTest.class,
                     SquareRootTest.class,
                     CalculatorPresenterTest.class,
                     MathExpressionTest.class,
                     MathCalculatorDobleMockitoTest.class,
                     MathCalculatorTest.class,//
                     MathResolverTest.class, //
                      }
)
public class MathSuite {
    //Ejericicio Crear una Suite pag 38

}
