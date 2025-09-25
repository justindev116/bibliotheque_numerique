package com.bibliotheque.patterns.singleton;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;



public class GestionnairePersistance {
    private static GestionnairePersistance instance;
    private ConfigurationBD configBD;

    private GestionnairePersistance() {
        configBD = ConfigurationBD.getInstance();
        System.out.println("Instance de GestionnairePersistance créée.");
    }

    public static synchronized GestionnairePersistance getInstance() {
        if (instance == null) {
            instance = new GestionnairePersistance();
        }
        return instance;
    }



    public void fermerConnexion() {
        configBD.fermerConnexion();
    }
}
