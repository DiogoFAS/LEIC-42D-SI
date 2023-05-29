package businessLogic;

import annotations.Description;
import dataManagement.DataScope;

import routine_manager.functions.FunctionManager;
import routine_manager.functions.FunctionRegisters;


public class BLService {

    public BLService() {
        try(DataScope scope = new DataScope()) {
            FunctionRegisters.registerAllFunctions(scope.getEntityManager());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Description("Get the total games of a certain player.")
    public Integer totalJogosJogador(Integer idJogador) {
        return (Integer) FunctionManager.executeFunction("totalJogosJogador", idJogador);
    }
}