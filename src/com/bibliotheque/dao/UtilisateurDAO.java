package com.bibliotheque.dao;

import com.bibliotheque.entites.Utilisateur;
import java.sql.*;
import java.time.LocalDateTime;

public class UtilisateurDAO extends BaseDAO {

    public Long create(Utilisateur u) throws SQLException {
        String sql = "INSERT INTO utilisateurs(nom,email,mot_de_passe_hash,date_creation) VALUES(?,?,?,?)";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, u.getNom());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getMotDePasseHash());
            ps.setTimestamp(4, Timestamp.valueOf(u.getDateCreation()!=null?u.getDateCreation():LocalDateTime.now()));
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getLong(1);
            }
            return null;
        }
    }

    public Utilisateur findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM utilisateurs WHERE email=?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    private Utilisateur map(ResultSet rs) throws SQLException {
        Utilisateur u = new Utilisateur();
        u.setId(rs.getLong("id"));
        u.setNom(rs.getString("nom"));
        u.setEmail(rs.getString("email"));
        u.setMotDePasseHash(rs.getString("mot_de_passe_hash"));
        Timestamp ts = rs.getTimestamp("date_creation");
        u.setDateCreation(ts!=null?ts.toLocalDateTime():null);
        return u;
    }
}
