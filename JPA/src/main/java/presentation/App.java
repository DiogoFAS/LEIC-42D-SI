package presentation;


import business_logic.BLService;
import routine_manager.routine.RoutineControllers;
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

        while (true) {
            Utils.clearConsole();
            System.out.println("Welcome to GameOn's DB Management\n");
            controllers.printOptions();
            System.out.print("Your option: ");
            String option = scanner.nextLine();
            if (option.isBlank() || !Utils.isNumeric(option)) continue;
            controllers.chooseRoutine(Integer.parseInt(option));
            scanner.nextLine();
        }

        // AssociarCracha
        /*System.out.println(":: Testing associarCrachaProc ::");
        AssociarCrachaProc s = new AssociarCrachaProc();
        s.associarCracha(1, "Xadrez", "XadrezRank4");
        System.out.println(":: end ::");
        */
    }
}