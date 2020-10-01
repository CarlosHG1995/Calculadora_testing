package com.example.calculadora_testing.dominio;


import androidx.annotation.VisibleForTesting;

import com.example.calculadora_testing.dominio.Expression;
import com.example.calculadora_testing.dominio.MathCalculator;
import com.example.calculadora_testing.dominio.Resolver;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static junitparams.JUnitParamsRunner.$;
import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnitParamsRunner.class)
public class MathCalculatorTest {


    //pag 50
    /*Ejercicio: Crear un mock paso 2 pag 50*/
    @Test @Parameters(method="addSymbolData")
    public void addSymbolShouldCallAddSymbolInExpression(String to, String symbol) {
        //Arrange
        MockExpression mockedExpression = new MockExpression();
        Resolver dummyResolver = null;
        MathCalculator calculator = new MathCalculator(mockedExpression, dummyResolver);
        //Act
        calculator.addSymbol(to, symbol);
        //Assert
        assertThat(mockedExpression.symbolAdded).isTrue();
        assertThat(mockedExpression.symbolReplaced).isFalse();
    }

    private Object[] addSymbolData() {
        return $($("", "-"),
                 $("2", "+"),
                 $("5", "."),
                 $("4.3", "f"));
    }

    @Test @Parameters(method = "replace_SymbolData")
    public void addSymbolShouldCallReplaceSymbolInExpression( String to, String symbol) {
        //Arrange
        MockExpression mockedExpression = new MockExpression();
        Resolver dummyResolver = null;
        MathCalculator calculator = new MathCalculator(mockedExpression,dummyResolver);
        //Act
        calculator.addSymbol(to, symbol);
        //Assert
        assertThat(mockedExpression.symbolAdded).isFalse();
        assertThat(mockedExpression.symbolReplaced).isTrue();
    }
    private Object[] replace_SymbolData(){
        return $($("-", "+"),
                 $("-5+","x"),
                 $("3x4/","^"),
                 $("3x", "x"),
                 $("3^", "-"),
                 $("+", "+"),
                 $("-", "-"),
                 $("x", "x"),
                 $("/", "/"),
                 $("^", "^"));
    }
/**/


}