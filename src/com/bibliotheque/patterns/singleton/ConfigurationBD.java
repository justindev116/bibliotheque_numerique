package com.bibliotheque.patterns.singleton;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;


public class ConfigurationBD {
    private static ConfigurationBD instance;
    private Connection connexion;
    private String url;
    private String utilisateur;
    private String motDePasse;


    private ConfigurationBD() {
        chargerConfiguration();
    }


    public static synchronized ConfigurationBD getInstance() {
        if (instance == null) {
            instance = new ConfigurationBD();
        }
        return instance;
    }

    private void chargerConfiguration() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("config/database.properties")) {
            props.load(fis);
            this.url = props.getProperty("db.url");
            this.utilisateur = props.getProperty("db.user");
            this.motDePasse = props.getProperty("db.password");
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la configuration: " + e.getMessage());

            this.url = "jdbc:mysql://localhost:3306/bibliotheque";
            this.utilisateur = "root";
            this.motDePasse = "";
        }
    }

    public Connection getConnexion() throws SQLException {
        if (connexion == null || connexion.isClosed()) {
            try {

                Class.forName("com.mysql.cj.jdbc.Driver");

                connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
                System.out.println("Connexion à la base de données établie avec succès.");
            } catch (ClassNotFoundException e) {
                System.err.println("Pilote JDBC introuvable: " + e.getMessage());
                throw new SQLException("Pilote JDBC introuvable", e);
            }
        }
        return connexion;
    }

    public void fermerConnexion() {
        if (connexion != null) {
            try {
                connexion.close();
                System.out.println("Connexion à la base de données fermée.");
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion: " + e.getMessage());
            }
        }
    }
}
