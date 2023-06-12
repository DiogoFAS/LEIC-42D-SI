package presentation;


import business_logic.BLService;
import jakarta.persistence.LockModeType;
import jpa_routines.AssociarCracha;
import routine_manager.routine.RoutineControllers;
import utils.CrachaOptFunctions;
import utils.Utils;

import java.util.Scanner;

/**
 * The program needs to be compiled using "-parameters" java compiling option, so it can preserve the params' names.
 * Otherwise, the input parameters will be unrecognizable
 */

public class App {
    public static void main(String[] args) throws Exception {
        BLService services = new BLService();
        RoutineControllers controllers = new RoutineControllers(services);
        Scanner scanner = new Scanner(System.in);

        /*while (true) {
            try {
                Utils.clearConsole();
                System.out.println("Welcome to GameOn's DB Management\n");
                controllers.printOptions();
                System.out.print("Your option: ");
                String option = scanner.nextLine();
                if (option.isBlank() || !Utils.isNumeric(option)) continue;
                controllers.chooseRoutine(Integer.parseInt(option));
                scanner.nextLine();
            }catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
        }*/

        scanner.nextLine();
        AssociarCracha s = new AssociarCracha();
        //s.associarCrachaJPA(6, "Tetris", "TetrisRank4"); // AssociarCrachaJPA
        s.associarCrachaBaseline(6, "Tetris", "TetrisRank4");   // AssociarCrachaBaseline

        //CrachaOptFunctions.increaseCrachaPontos("TetrisRank4", "Tetris", LockModeType.OPTIMISTIC);
        System.out.println(":: end ::");
    }
}