package exam1.src.main.java;

import model.Funcionario;
import repo.FuncionarioRep;
import services.BusinessLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utils.Utils.printTable;

public class App {
    public static void main(String[] args) throws IllegalAccessException {
        FuncionarioRep repo = new FuncionarioRep();

        /*Funcionario f = new Funcionario();
        f.setNum(5);
        f.setNome("Jacinto Pinto");
        f.setIdade(55);

        repo.add(f);*/

        /*List<Funcionario> funcionarios = repo.obterFuncionariosSemTarefas();
        printTable(funcionarios, Funcionario.class);*/

        BusinessLogic services = new BusinessLogic();
        List<Funcionario> funcs = new ArrayList<>(Arrays.asList(
                new Funcionario(5, "jorge", 64),
                new Funcionario(6, "jonas", 44)
        ));
        services.inserirFuncionarios(funcs);
    }
}
