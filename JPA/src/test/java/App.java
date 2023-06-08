import business_logic.BLService_Tests;

import java.util.Scanner;

public class App {

    protected interface ITest {
        void test();
    }

    private static void teste8() throws Exception{
        Scanner imp = new Scanner(System.in );
        System.out.printf("Número de repetições para teste 8? ");
        int nreps = imp.nextInt();
        BLService_Tests srv = new BLService_Tests();
        srv.teste8(nreps);
    }

    public static void main(String[] args) {
        // Guerra Branch
        BLService_Tests srv = new BLService_Tests();
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
