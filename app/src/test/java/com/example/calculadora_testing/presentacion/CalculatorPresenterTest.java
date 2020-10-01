package com.example.calculadora_testing.presentacion;

import com.example.calculadora_testing.dominio.Calculator;
import com.example.calculadora_testing.dominio.ExpressionException;
import com.example.calculadora_testing.dominio.OperationException;
import com.example.calculadora_testing.presentacion.CalculatorPresenter;
import com.example.calculadora_testing.presentacion.CalculatorView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import junitparams.JUnitParamsRunner;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class CalculatorPresenterTest {

    private CalculatorPresenter presenter;
    @Mock private CalculatorView mockedView;
    @Mock private Calculator mockedCalculator;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new CalculatorPresenter(mockedView, mockedCalculator);
    }

    @Test public void addSymbolShouldCallShowOperationsWhenInputIsValid( ) {
        when(mockedCalculator.addSymbol(anyString(),anyString())).thenReturn("");
        presenter.addSymbol( anyString(),  anyString());
        verify(mockedView, times(1)).showOperations(anyString());
        verify(mockedView, times(0)).showError();
    }

    @Test
    public void
    addSymbolShouldCallShowErrorWhenCalculatorThrowsAnOperationException() {
        when(mockedCalculator.addSymbol(anyString(),  anyString())).thenThrow(OperationException.class);
        presenter.addSymbol(anyString(), anyString());
        verify(mockedView, times(0)).showOperations(anyString());
        verify(mockedView, times(1)).showError();
    }
    @Test
    public void
    addSymbolShouldCallShowErrorWhenCalculatorThrowsAnExpressionException() {
        when(mockedCalculator.addSymbol(anyString(),anyString())).thenThrow(ExpressionException.class);
        presenter.addSymbol(anyString(),  anyString());
        verify(mockedView, times(0)).showOperations(anyString());
        verify(mockedView, times(1)).showError();
    }

    @Test public void eliminar_simbolo_debe_llamar_ShowOperations(){
        when(mockedCalculator.removeSymbol("2+2")).thenReturn("2+");
        presenter.removeSymbol("2+2");;
        verify(mockedView,times(1)).showOperations("2+");
    }


}