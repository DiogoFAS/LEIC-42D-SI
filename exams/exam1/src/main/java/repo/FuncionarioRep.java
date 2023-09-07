package exam1.src.main.java.repo;

import data_manager.DataScope;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import exam1.src.main.java.model.Funcionario;
import exam1.src.main.java.model.Funcionario_Tarefa;
import exam1.src.main.java.model.Funcionario_TarefaPK;
import exam1.src.main.java.model.Tarefa;

import java.util.List;
import java.util.NoSuchElementException;

public class FuncionarioRep {
    public void add(Funcionario funcionario) {
        DataScope scope = new DataScope();
        EntityManager em = scope.getEntityManager();
        try {
            Tarefa tarefa = em.createQuery("SELECT t FROM Tarefa t WHERE t.nome = 'Sem tarefa'", Tarefa.class)
                    .getSingleResult();
            if (tarefa == null) throw new NoSuchElementException("Tarefa 'Sem tarefa' não existe");

            Funcionario_TarefaPK ftPK = new Funcionario_TarefaPK();
            ftPK.setNumFunc(funcionario.getNum());
            ftPK.setIdTarefa(tarefa.getId());

            Funcionario_Tarefa ft = new Funcionario_Tarefa();
            ft.setId(ftPK);

            funcionario.addFT(ft);

            em.persist(funcionario);
            scope.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public List<Funcionario> obterFuncionariosSemTarefas() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SI");
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                    "select new Funcionario(f.num, f.nome, f.idade) " +
                            "from Funcionario f " +
                            "join Funcionario_Tarefa ft on ft.funcionario = f " +
                            "join Tarefa t on ft.tarefa = t " +
                            "where t.nome = 'Sem tarefa'",
                    Funcionario.class
            ).getResultList();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
