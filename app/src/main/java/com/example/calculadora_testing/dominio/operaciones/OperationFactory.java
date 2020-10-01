package com.example.calculadora_testing.dominio.operaciones;

public class OperationFactory {

    public static Operation create(String operator){
        switch (operator){
            case "+": return new Addition();
            case "-": return new Subtraction();
            case "x": return new Multiplicacion();
            case "/": return new Division();
            case "^": return new Exponentation();
            case "f": return new Factorial();
            case "r": return new SquareRoot();
            default: return new NoOperation();
        }
    }
}
