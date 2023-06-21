package services;

import data_manager.DataScope;
import jakarta.persistence.EntityManager;
import model.Funcionario;
import repo.FuncionarioRep;

import java.util.List;

public class BusinessLogic {

    public void inserirFuncionarios(List<Funcionario> lf) {
        FuncionarioRep repo = new FuncionarioRep();
        try (DataScope scope = new DataScope()) {
            for (Funcionario f : lf) {
                if (f.getNum() == 6) throw new Exception();
                repo.add(f);
            }
            scope.validateWork();
        } catch (Exception e) {

        }
    }
}
