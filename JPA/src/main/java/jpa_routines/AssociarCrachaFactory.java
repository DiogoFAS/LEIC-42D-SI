package jpa_routines;

public class AssociarCrachaFactory {

    public enum Implementations {
        BASELINE,
        JPA
    }

    public static AssociarCracha getAssociarCracha(Implementations impl) {
        switch (impl) {
            case BASELINE -> {
                return new AssociarCrachaBaseline();
            }

            case JPA -> {
                return new AssociarCrachaJPA();
            }
        }
        throw new IllegalArgumentException("Implementation " + impl + " not valid.");
    }
}
