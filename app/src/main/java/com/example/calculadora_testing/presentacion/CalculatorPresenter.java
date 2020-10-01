package com.example.calculadora_testing.presentacion;

import com.example.calculadora_testing.dominio.Calculator;
import com.example.calculadora_testing.dominio.ExpressionException;
import com.example.calculadora_testing.dominio.OperationException;

public class CalculatorPresenter {

    private CalculatorView view;
    private Calculator calculator;

    public CalculatorPresenter(CalculatorView view,Calculator calculator){
        this.view=view;
        this.calculator=calculator;
    }

    public void addSymbol(String expression, String symbol) {
        try {
            view.showOperations(calculator.addSymbol(expression, symbol));
        } catch (OperationException | ExpressionException exception) {
            view.showError();
        }
    }
    public void removeSymbol(String expression) {
        //si dar clic al boton CE están vacío debo hacer :
        if(!expression.isEmpty())
        view.showOperations(calculator.removeSymbol(expression));
    }
    public void clearScreen() {
        view.showOperations("");
    }
    public void calculate(String expression) {
        if(expression.isEmpty()) {
            view.showResult(expression);
            return;
        }
        try{
            view.showResult(calculator.calculate(expression));
        } catch (OperationException | ExpressionException |
                NumberFormatException exception) {
            exception.printStackTrace();
            view.showError();
        }
    }
}
