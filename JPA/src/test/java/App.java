import business_logic.BLService_Tests;

import java.util.Scanner;

public class App {

    protected interface ITest {
        void test();
    }
    public static void main(String[] args) {
        // Guerra Branch
        BLService_Tests srv = new BLService_Tests();
        ITest tests[] = new ITest[] {
            () -> {try { srv.teste1(); } catch(Exception e) {}} ,
            () -> {try { srv.teste2(); } catch(Exception e) {}}
        };

        Scanner imp = new Scanner(System.in );
        System.out.printf("Escolha um teste (1-%d)? ",tests.length);
        int option = imp.nextInt();
        if (option >= 1 && option <= tests.length)
            tests[--option].test();

    }
}
