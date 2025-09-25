package com.bibliotheque.dao;

import java.sql.*;
import java.time.LocalDateTime;

public class TelechargementDAO extends BaseDAO {

    public void log(Long utilisateurId, Long livreId) throws SQLException {
        String sql = "INSERT INTO telechargements(utilisateur_id, livre_id, telecharge_le) VALUES (?,?,?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, utilisateurId);
            ps.setLong(2, livreId);
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.executeUpdate();
        }
    }

    public int countByLivre(Long livreId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM telechargements WHERE livre_id=?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, livreId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return 0;
    }
}
