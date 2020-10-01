package com.example.calculadora_testing.dominio;

import com.example.calculadora_testing.dominio.operaciones.NoOperation;
import com.example.calculadora_testing.dominio.operaciones.Operation;
import com.example.calculadora_testing.dominio.operaciones.OperationFactory;

import java.util.List;

public class MathResolver implements Resolver {

    @Override
    public Double resolve(List <String> symbols){
        while (symbols.size()>1){
            Operation operator = new NoOperation();
            int operatorPosition = -1;

            for (int position=0; position<symbols.size();position++){
                String symbol= symbols.get(position);
                if(isNumeric(symbol))continue;
            //TODO 1. Determinar el operador con el mayor  orden de precedencia
                Operation currentOperation = OperationFactory.create(symbol);
                if(currentOperation.compareTo(operator)>0){
                    operator = currentOperation;
                    operatorPosition = position;
                }
        }
              //TODO 2. Extraer los operandos.
            Double rightOperand = extractRightOperand(operator, operatorPosition, symbols);
            Double leftOperand = extractLeftOperand(operator, operatorPosition, symbols);
             //TODO 3. Resolver la operación.
            Double result = operator.calculate(leftOperand,rightOperand);
            //TODO 4. Reemplazar los operandos y el operador por el resultado
            replaceOperands(operator, result.toString(),operatorPosition, symbols);

        }
        return isNumeric(symbols.get(0))? Double.parseDouble(symbols.get(0)) : 0;
    }

        private boolean isNumeric(String symbol) {
            try{
                Double.parseDouble(symbol);
                return true;
            } catch (Exception exception) {
                return false;
            }
        }

    private Double extractRightOperand(Operation operator, int operatorOrder, List<String> symbols) {
        Double operand = operator.getIdentityElement();
        //se modifica para que los test pasen
        if(tiene_operador_derecho(operatorOrder,symbols.size())){
         if(!isNumeric(symbols.get(operatorOrder+1)) && operatorOrder+2<symbols.size()){
        operand = Double.parseDouble(symbols.get(operatorOrder+2))*-1;
         }
        else {
            operand = Double.parseDouble(symbols.get(operatorOrder + 1));
         }
        }
    return operand;
    }
    private Double extractLeftOperand(Operation operator, int operatorOrder, List<String> symbols) {
        Double operand = operator.getIdentityElement();
        //se cambia por motivos que pasen los test
        if (tiene_operador_izquierdo(operatorOrder) && operator.isBinaryOperation())
        {
            if(isNumeric(symbols.get(operatorOrder-1)))
            operand = Double.parseDouble(symbols.get(operatorOrder - 1));
        }
        return operand;
    }

    private void replaceOperands(Operation operator, String result,
                                 int operatorOrder, List<String> symbols) {
        symbols.set(operatorOrder, result);
        //se modifica como dice en el pdf ya que hay unos test que no pasan
        /* if (operatorOrder + 1 < symbols.size()) {
            symbols.remove(operatorOrder + 1);
        }
        if (operatorOrder -1 >= 0 && operator.isBinaryOperation()) {
            symbols.remove(operatorOrder - 1);
        }
       */
        if (tiene_operador_derecho(operatorOrder , symbols.size())) {
            if(!isNumeric(symbols.get(operatorOrder +1)) && operatorOrder + 2 <symbols.size()){
                symbols.remove(operatorOrder + 2);
            }
            symbols.remove(operatorOrder + 1);
        }
        if (tiene_operador_izquierdo(operatorOrder) && operator.isBinaryOperation()) {
            symbols.remove(operatorOrder - 1);
        }
    }

    private boolean tiene_operador_derecho(int operatorPosition, int size) {
        return operatorPosition + 1 < size;
    }

    private boolean tiene_operador_izquierdo(int operatorPosition) {
        return operatorPosition -1 >= 0;
    }
}
