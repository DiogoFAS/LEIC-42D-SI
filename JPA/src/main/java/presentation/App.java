package presentation;


import business_logic.BLService;
import jpa_routines.AssociarCracha;
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
            if(option.equalsIgnoreCase("exit")) break;
            if (option.isBlank() || !Utils.isNumeric(option)) continue;
            controllers.chooseRoutine(Integer.parseInt(option));
            scanner.nextLine();
        }

        // AssociarCracha s = new AssociarCracha();

        // s.associarCrachaJPA(6, "Xadrez", "XadrezRank4"); // AssociarCrachJPA
        // s.associarCrachaBaseline(6, "Xadrez", "XadrezRank4");   // AssociarCrachaBaseline

        for (int i = 0; i < 3; i++) {
            Thread.sleep(100 * (i+1));
            System.out.print("BYE!!\t\t");
        }
    }
}