package utils;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import model.CrachaOpt;


// This isn't being used anywhere
public class CrachaOptFunctions {
    @PrePersist
    @PreUpdate
    public void prePersistOrUpdate(CrachaOpt crachaOpt) {
        if (crachaOpt.getVers() == null) {
            crachaOpt.setVers(0);
        } else if (crachaOpt.getVers().equals(crachaOpt.getOldVers())) {
            crachaOpt.setVers(crachaOpt.getVers() + 1);
        }
    }
}