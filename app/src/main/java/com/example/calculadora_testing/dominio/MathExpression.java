package com.example.calculadora_testing.dominio;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.calculadora_testing.dominio.MathSymbols.*;

public class MathExpression implements Expression {

    @Override public String read(@NonNull String expression){
        return  expression.replace(SQUARE_ROOT_SCREEN, SQUARE_ROOT)
                .replace(FACTORIAL_SCREEN, FACTORIAL)
                .replaceAll("\\s", "").trim();
    }

    @Override public String write(@NonNull String expression){
        return  expression.replaceAll("(?<=[-fr+x/^)])|(?=[-fr+x/^(])", "$0 ")
                .replace(SQUARE_ROOT, SQUARE_ROOT_SCREEN)
                .replace(FACTORIAL,FACTORIAL_SCREEN);
    }

    @Override public String normalize(@NonNull String expression){
        StringBuilder normalizedExpression = new StringBuilder(expression);
        Pattern pattern = Pattern.compile("(?=[\\d|\\)||\\.][\\(|r|f])|(?=[\\)][\\d|\\.])");
        Matcher matcher = pattern.matcher(expression);
        int offset = 0;
        while (matcher.find()) {
            normalizedExpression.insert(matcher.start() + ++offset, MathSymbols.MULTIPLICATION);
        }
        return normalizedExpression.toString();
    }

    @Override public List <String> tokenize(@NonNull String expression){
        List<String> tokens_list = new ArrayList<>(Arrays.asList(expression.split("(?<=[-fr+x/^])|(?=[-fr+x/^])")));

        List<String> resultado = new ArrayList<>();
        for (int los_tokens = 0; los_tokens < tokens_list.size(); los_tokens++)
        {if (!tokens_list.get(los_tokens).isEmpty()) resultado.add(tokens_list.get(los_tokens));
        }

        if (resultado.size() == 0) resultado.add(""); // Esto lo hago para que pase un test y no devuelva una lista vacía

        return resultado;
        //return new ArrayList <>(Arrays.asList(expression.split("(?=[+x/^rf-])|(?<=[-+x/^rf])")));
    }

    @Override public String addSymbol(@NonNull String expression,@NonNull String symbol) throws ExpressionException{
        throwsIfSymbolIsInvalid(symbol);
        return expression.concat(symbol);
    }

    @Override public String removeSymbol(@NonNull String expression){
        int  START_INDEX = 0;
        int END_INDEX = expression.length() - 1;
        return expression.substring(START_INDEX, END_INDEX);
    }

    @Override public String replaceSymbol(@NonNull String expression,@NonNull String symbol){
        if (expression.length() > 1) {
            expression = removeSymbol(expression);
            return addSymbol(expression, symbol);
        }
        return symbol;
    }

    @Override public boolean containsParenthesis(String expression){
        return expression.contains(MathSymbols.PARENTHESIS_START);
    }

    @Override public String getNextParenthesisExpression(String from){
        return removeParenthesis(getRightmostParenthesis(from));
    }

    private String getRightmostParenthesis(String from) {
        int START_INDEX = getParenthesisStartIndex(from);
        int END_INDEX = getParenthesisEndIndex(from);
        return from.substring(START_INDEX, END_INDEX);
    }
    private int getParenthesisStartIndex(String from) {
        return from.lastIndexOf(MathSymbols.PARENTHESIS_START);
    }
    private int getParenthesisEndIndex(String from) {
        int index = from.indexOf(MathSymbols.PARENTHESIS_END, getParenthesisStartIndex(from));
        if (index == -1) {
            return from.length();
        }
        return ++index;
    }
    private String removeParenthesis(String from) {
        return from.replace(MathSymbols.PARENTHESIS_START,MathSymbols.EMPTY_STRING)
                .replace(MathSymbols.PARENTHESIS_END, MathSymbols.EMPTY_STRING).trim();
    }

    @Override public String replaceParenthesis(String from,String with){
        return  new StringBuilder(from)
                .replace(getParenthesisStartIndex(from),
                         getParenthesisEndIndex(from), with)
                .toString();
    }
    private void throwsIfSymbolIsInvalid(String expression) throws ExpressionException {
        for (char symbol : expression.toCharArray()) {
            if (isSymbolInvalid(String.valueOf(symbol))) {
                throw new ExpressionException(
                        String.format("simbolo %s es invalido", symbol));
            }
        }
    }
    private boolean isSymbolInvalid(String symbol) {
        return !symbol.matches("([0-9]|[-+x/]|[.]|[()^fr])");
    }

    //esto no está en el pdf

    @VisibleForTesting
    String remove_Symbol(String from){
        if(from.isEmpty()) {return from;}
        int START_INDEX = 0;
        int END_INDEX = from.length()-1;
        return  from.substring(START_INDEX,END_INDEX);
    }
}
