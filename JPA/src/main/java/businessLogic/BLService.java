package businessLogic;

import annotations.Description;
import jakarta.persistence.ParameterMode;
import routine_manager.ProcedureManager;
import routine_manager.functions.FunctionManager;
import routine_manager.functions.FunctionParameter;
import routine_manager.functions.Functions;
import routine_manager.functions.FunctionsAux;
import utils.Utils;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;


public class BLService {

    public Map<String, String> optionMap1 = Map.of(
            "1", "criarJogador",
            "2", "desativarJogador",
            "3", "banirJogador",
            "4", "totalPontosJogador",
            "5", "totalJogosJogador",
            "6", "PontosJogoPorJogador",
            "7", "associarCracha",
            "8", "iniciarConversa",
            "9", "juntarConversa",
            "10","enviarMensagem"
    );

    @Description("Get the total score of a certain player.")
    public Integer totalPontosJogador() {
        return (Integer) FunctionManager.executeFunction("totalPontosJogador");
    }

    @Description("Get the total games of a certain player.")
    public Integer totalJogosJogador(Integer idJogador) {
        return (Integer) FunctionManager.executeFunction("totalJogosJogador", idJogador);
    }

    @Description("Get the table with the score for each player from a certain game.")
    public Integer PontosJogoPorJogador(String nomeJogo) {
        return (Integer) FunctionManager.executeFunction("PontosJogoPorJogador", nomeJogo);
    }

    private static Object callMethod(String funName) throws Exception {
        FunctionParameter[] functionParameters = Functions.getFunctionParam(funName);
        if (functionParameters == null) throw new NoSuchMethodException("Register the function before calling it.");
        Scanner scanner = new Scanner(System.in);
        Object[] args = new Object[(int) Arrays.stream(functionParameters).filter(it -> it.mode() == ParameterMode.IN).count()];
        for (int i = 0; i < functionParameters.length; i++) {
            FunctionParameter curr = functionParameters[i];
            if (curr.mode() == ParameterMode.IN) {
                System.out.println(curr.paramName() + ":");
                args[i] = Utils.parseObject(curr.clazz(), scanner.nextLine());
            }
        }
        if(Functions.isFunction(funName)) {
            System.out.println("Function");
            return FunctionsAux.executeFunction(funName, args);
        } else {
            System.out.println("Procedure");
            ProcedureManager.executeProcedure(funName, args);
            return null;
        }
    }

    public void showFunction(String option) throws Exception {
        String funName = optionMap1.get(option);
        System.out.println("You selected " + funName);
        Object result = BLService.callMethod(funName);
        System.out.println(funName + ": " + result);
    }

    public void showMenu() {
        for (int i = 1; i <= optionMap1.size(); i++) {
            System.out.println(i + " -> " + optionMap1.get(String.valueOf(i)));
        }
    }
}