/*
 Walter Vieira (2022-04-22)
 Sistemas de Informação - projeto JPAAulas_ex1
 Código desenvolvido para iulustração dos conceitos sobre acesso a dados, concretizados com base na especificação JPA.
 Todos os exemplos foram desenvolvidos com EclipseLinlk (3.1.0-M1), usando o ambientre Eclipse IDE versão 2022-03 (4.23.0).
 
Não existe a pretensão de que o código estaja completo.

Embora tenha sido colocado um esforço significativo na correção do código, não há garantias de que ele não contenha erros que possam 
acarretar problemas vários, em particular, no que respeita à consistência dos dados.  
 
*/

package presentation;

import java.util.List;

import businessLogic.*;
import model.*;
import utils.Utils;


/**
 * Hello world!
 *
 */
public class App 
{
    @SuppressWarnings("unchecked")
	public static void main( String[] args ) throws Exception
    {
        //put instances in an init/setup function.
        BLService srv = new BLService();
    	srv.test();

        //2d...2l in console
        //read user input
        //clear console
        //get function to execute 2d...2l
        //int option = System.in.read();
        Utils.clearConsole();
    }
}
