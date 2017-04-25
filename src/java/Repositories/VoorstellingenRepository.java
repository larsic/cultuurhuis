/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import JavaFiles.Voorstelling;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class VoorstellingenRepository extends AbstractRepository {

    final String SELECT_ALL_VOORSTELLINGEN
            = "SELECT naam, datum, titel, uitvoerders, prijs, vrijeplaatsen\n"
            + " FROM cultuurhuis.genres JOIN cultuurhuis.voorstellingen ON genres.id=voorstellingen.genreid WHERE genres.naam = ?";

    Voorstelling voorstelling;

    public List<Voorstelling> findAll(String id) {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_ALL_VOORSTELLINGEN)) {
            statement.setString(1, id);
            try(ResultSet resultSet = statement.executeQuery()
            
                ) {
            List<Voorstelling> voorst = new ArrayList<>();
                while (resultSet.next()) {
                    voorstelling = new Voorstelling(resultSet.getTimestamp("datum"),
                            resultSet.getString("titel"),
                            resultSet.getString("uitvoerders"),
                            resultSet.getString("prijs"),
                            resultSet.getInt("vrijeplaatsen"));
                    voorst.add(voorstelling);
                }
                return voorst;
            }

        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
    }
}
