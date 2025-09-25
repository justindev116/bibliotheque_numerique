package com.bibliotheque.dao;

import com.bibliotheque.entites.Livre;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FavoriDAO extends BaseDAO {

    public boolean add(Long utilisateurId, Long livreId) throws SQLException {
        String sql = "INSERT INTO favoris(utilisateur_id, livre_id, cree_le) VALUES (?,?,?) " +
                "ON DUPLICATE KEY UPDATE cree_le=VALUES(cree_le)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, utilisateurId);
            ps.setLong(2, livreId);
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            return ps.executeUpdate() > 0;
        }
    }

    public boolean remove(Long utilisateurId, Long livreId) throws SQLException {
        String sql = "DELETE FROM favoris WHERE utilisateur_id=? AND livre_id=?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, utilisateurId);
            ps.setLong(2, livreId);
            return ps.executeUpdate() > 0;
        }
    }

    public List<com.bibliotheque.entites.Livre> listFavoris(Long utilisateurId) throws SQLException {
        String sql = "SELECT l.* FROM favoris f JOIN livres l ON l.id=f.livre_id WHERE f.utilisateur_id=?";
        List<Livre> list = new ArrayList<>();
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, utilisateurId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Livre l = new Livre();
                    l.setId(rs.getLong("id"));
                    l.setTitre(rs.getString("titre"));
                    l.setAuteur(rs.getString("auteur"));
                    list.add(l);
                }
            }
        }
        return list;
    }
}
