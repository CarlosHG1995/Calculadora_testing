package com.example.calculadora_testing.dominio;

import androidx.annotation.NonNull;

import com.example.calculadora_testing.dominio.Expression;

import java.util.ArrayList;
import java.util.List;

public class MockExpression implements Expression {

    //Ejercicio crear un mock pag 49
    public boolean symbolAdded = false;
    public boolean symbolRemoved = false;
    public boolean symbolReplaced = false;

    @Override public String read(@NonNull String expression){
         return expression;
    }

    @Override public String write(@NonNull String expression){
        return expression;
    }

    @Override public String normalize(@NonNull String expression){
        return null;
    }

    @Override public List <String> tokenize(@NonNull String expression){
        return new ArrayList <>();
    }

    @Override public String addSymbol(@NonNull String expression,@NonNull String symbol){
        symbolAdded = true;
        return expression;
    }

    @Override public String removeSymbol(@NonNull String expression){
        symbolRemoved = true;
        return expression;
    }

    @Override public String replaceSymbol(@NonNull String expression,@NonNull String symbol){
        symbolReplaced = true;
        return expression;
    }

    @Override public boolean containsParenthesis(String expression){
        return false;
    }

    @Override public String getNextParenthesisExpression(String from){
        return null;
    }

    @Override
    public String replaceParenthesis(String from,String with){
        return null;
    }
}
