package com.bibliotheque.dao;



import com.bibliotheque.entites.Livre;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LivreDAO extends BaseDAO {

    public Long create(Livre l) throws SQLException {
        String sql = "INSERT INTO livres(titre,auteur,description,categorie,fichier_chemin,date_publication) VALUES (?,?,?,?,?,?)";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, l.getTitre());
            ps.setString(2, l.getAuteur());
            ps.setString(3, l.getDescription());
            ps.setString(4, l.getCategorie());
            ps.setString(5, l.getFichierChemin());
            ps.setTimestamp(6, Timestamp.valueOf(l.getDatePublication() != null ? l.getDatePublication() : LocalDateTime.now()));
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getLong(1);
            }
            return null;
        }
    }

    public Livre findById(Long id) throws SQLException {
        String sql = "SELECT * FROM livres WHERE id=?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    public List<Livre> listAll() throws SQLException {
        List<Livre> out = new ArrayList<>();
        String sql = "SELECT * FROM livres ORDER BY date_publication DESC";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) out.add(map(rs));
        }
        return out;
    }

    private Livre map(ResultSet rs) throws SQLException {
        Livre l = new Livre();
        l.setId(rs.getLong("id"));
        l.setTitre(rs.getString("titre"));
        l.setAuteur(rs.getString("auteur"));
        l.setDescription(rs.getString("description"));
        l.setCategorie(rs.getString("categorie"));
        l.setFichierChemin(rs.getString("fichier_chemin"));
        Timestamp ts = rs.getTimestamp("date_publication");
        l.setDatePublication(ts != null ? ts.toLocalDateTime() : null);
        return l;
    }


}
