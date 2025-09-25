package com.bibliotheque.dao;

import com.bibliotheque.patterns.singleton.ConfigurationBD;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseDAO {
    protected Connection getConnection() throws SQLException {
        return ConfigurationBD.getInstance().getConnexion();
    }
}

