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


import businessLogic.BLService;
import jakarta.persistence.ParameterMode;
import routine_manager.functions.FunctionParameter;
import routine_manager.functions.Functions;
import utils.Utils;
//import utils.associarCrachaProc;

import java.util.Scanner;


public class App {
    public static void main(String[] args) throws Exception {
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
    }
}
