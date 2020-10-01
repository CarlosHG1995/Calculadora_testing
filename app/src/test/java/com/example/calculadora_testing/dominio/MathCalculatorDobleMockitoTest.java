package com.example.calculadora_testing.dominio;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static com.google.common.truth.Truth.assertThat;
import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class MathCalculatorDobleMockitoTest {

    private MathCalculator calculator;
    private MockExpression Expression_mock;
    //Ejercicio Crear un test doble con mockito pag 52
    @Mock private Expression mockedExpression;
    @Mock private Resolver mockedResolver;

    @Before public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        calculator = new MathCalculator(mockedExpression,mockedResolver);
    }

    /**/
    @Test @Parameters(method = "add_SymbolData")
    public void addSymbolShouldCallAddSymbol(String to, String symbol) {
        when(mockedExpression.read(anyString())).thenReturn(anyString());
        calculator.addSymbol(to, symbol);
        //verify(mockedExpression, times(1)).addSymbol((String) isNull(), anyString());
        verify(mockedExpression, times(1)).addSymbol(anyString(), anyString());
        verify(mockedExpression, times(0)).replaceSymbol(anyString(),anyString());
    }
    @Test @Parameters(method = "replace_SymbolData")
    public void addSymbolShouldCallReplaceSymbol_mockito_doble(String to, String symbol) {
        when(mockedExpression.read(anyString())).thenReturn(to);
        calculator.addSymbol(to, symbol);
        verify(mockedExpression, times(1)).replaceSymbol(anyString(),anyString());
        verify(mockedExpression, times(0)).addSymbol(anyString(), anyString());
    }


    private Object[] add_SymbolData() {
        return $($("", "-"),
                 $("2", "+"),
                 $("5", "."),
                 $("4.3", "f"));
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

    @Parameters(method = "replaceSymbolData")
    @Test
    public void addSymbolShouldReplaceSymbol(String to, String symbol, String expected) {
        calculator = new MathCalculator(new MathExpression(), null);
        String result = calculator.addSymbol(to, symbol);
        assertThat(result).isEqualTo(expected);
    }

    private Object[] replaceSymbolData(){
        return $($("-","+", " + "),
                 $("-5+","x", " - 5 x "),
                 $("3x4/","^", "3 x 4 ^ "),
                 //$("-3+","r", " - 3 r "),
                 $("3^","-", "3 - "),
                 $("+", "+", " + "),
                 $("-", "-", " - "),
                 $("x", "x", " x "),
                 $("/", "/", " / "),
                 $("^", "^", " ^ "));
    }
/**/
    @Parameters(method = "calcular_ecuacion")
    @Test public void debe_resolver_el_calculo_ecuacion_dado(String from, String expected) {
        Expression expression = new MathExpression();
        MathResolver operation = new MathResolver();
        MathCalculator calculator = new MathCalculator(expression, operation);

        assertThat(calculator.calculate(from)).isEqualTo(expected);
    }

    private Object[] calcular_ecuacion() {
        return $(
                $("(2+(2x2))", "6"),
                $("sqrt(3x3)(3+2)", "15"),
                $("-9(-3)", "27"),
                $("-9-3", "-12"),
                $("fact(3+3-1)", "120")
                );
    }

    @Parameters(method = "ecuacion_resolver")
    @Test
    public void debe_resolver_la_ecuacion(String from, String[] tokens, String expected) {
        calculator = new MathCalculator(mockedExpression,new MathResolver());
        when(mockedExpression.tokenize(from)).thenReturn(new ArrayList <>(Arrays.asList(tokens)));
        assertThat(calculator.resolve(from)).isEqualTo(expected);
    }

    private Object[] ecuacion_resolver() {
        return $(
                $("", new String[] {""}, ""),
                $("-", new String[] {"-"}, "0"),
                $("-5", new String[] {"-","5"}, "-5"),
                $("3.4", new String[] {"3.4"}, "3.4"),
                $("3-2", new String[] {"3", "-", "2"}, "1"),
                $("3+2", new String[] {"3", "+", "2"}, "5"),
                $("f3", new String[] {"f", "3"}, "6"),
                $("2f3", new String[] {"2", "x", "f", "3"}, "12"),
                $("9/r9", new String[] {"9", "/", "r", "9"}, "3"),
                $("3^f3", new String[]{"3", "^", "f", "3"}, "729"),
                $("3--4", new String[] {"3", "-", "-","4"}, "7"),
                $("r81", new String[] {"r", "81", }, "9")
                );
    }

}