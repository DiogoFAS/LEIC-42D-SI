/*
 Walter Vieira (2022-04-22)
 Sistemas de Informa��o - projeto JPAAulas_ex1
 C�digo desenvolvido para iulustra��o dos conceitos sobre acesso a dados, concretizados com base na especifica��o JPA.
 Todos os exemplos foram desenvolvidos com EclipseLinlk (3.1.0-M1), usando o ambientre Eclipse IDE vers�o 2022-03 (4.23.0).
 
N�o existe a pretens�o de que o c�digo estaja completo.

Embora tenha sido colocado um esfor�o significativo na corre��o do c�digo, n�o h� garantias de que ele n�o contenha erros que possam 
acarretar problemas v�rios, em particular, no que respeita � consist�ncia dos dados.  
 
*/

package presentation;


import businessLogic.*;
import jakarta.persistence.ParameterMode;
import routine_manager.functions.FunctionParameter;
import routine_manager.functions.Functions;
import utils.Utils;
//import utils.associarCrachaProc;

import java.util.Scanner;


public class App {

    protected interface ITest {
        void test();
    }
    /*public static void main(String[] args) throws Exception {
        BLService services = new BLService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Utils.clearConsole();
            System.out.println("Welcome to GameOn's DB Management\n");
            services.showMenu();
            String option = scanner.nextLine();
            //Utils.clearConsole();
            services.showFunction(option);
            scanner.nextLine();
        }
    }*/

    private static void teste8() throws Exception{
        Scanner imp = new Scanner(System.in );
        System.out.printf("Número de repetições para teste 8? ");
        int nreps = imp.nextInt();
        BLServiceTests srv = new BLServiceTests();
        srv.teste8(nreps);

    }

    @SuppressWarnings("unchecked")
    public static void main( String[] args ) throws Exception
    {   BLServiceTests srv = new BLServiceTests();
        ITest tests[] = new ITest[] {
                () -> {try { srv.teste1(); } catch(Exception e) {}} ,
                () -> {try { srv.teste2(); } catch(Exception e) {}} ,
                () -> {try { srv.teste3(); } catch(Exception e) {}} ,
                () -> {try { srv.teste4(); } catch(Exception e) {}} ,
                () -> {try { srv.teste5(); } catch(Exception e) {}} ,
                () -> {try { srv.teste6(); } catch(Exception e) {}} ,
                () -> {try { srv.teste7(); } catch(Exception e) {}} ,
                () -> {try { teste8(); } catch(Exception e) {}}
        };

        Scanner imp = new Scanner(System.in );
        System.out.printf("Escolha um teste (1-%d)? ",tests.length);
        int option = imp.nextInt();
        if (option >= 1 && option <= tests.length)
            tests[--option].test();



    }
}
