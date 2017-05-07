package Repositories;

import JavaFiles.NieuweKlant;
import JavaFiles.Voorstelling;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VoorstellingenRepository extends AbstractRepository {

    final String SELECT_ALL_VOORSTELLINGEN
            = "SELECT naam, voorstellingen.id AS id, datum, titel, uitvoerders, prijs, vrijeplaatsen\n"
            + " FROM cultuurhuis.genres JOIN cultuurhuis.voorstellingen ON genres.id=voorstellingen.genreid WHERE genres.naam = ?";

    final String SELECT_ONE_VOORSTELLING
            = "SELECT id, datum, titel, uitvoerders, prijs, vrijeplaatsen\n"
            + " FROM cultuurhuis.voorstellingen WHERE id = ?";

    final String CHECK_USER_AND_PASSWORD
            = "SELECT paswoord FROM cultuurhuis.klanten WHERE gebruikersnaam = ?";

    final String NAAM_EN_ADRES
            = "SELECT voornaam, familienaam, straat, huisnr, postcode, gemeente FROM klanten WHERE gebruikersnaam = ?";

    final String USER_EXISTS
            = "SELECT voornaam FROM klanten WHERE gebruikersnaam = ?";

    private static final String NEW_USER
            = "insert into klanten(voornaam, familienaam, straat, huisnr, postcode, gemeente, gebruikersnaam, paswoord) values (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String CHANGE_VRIJEPLAATSEN
            = "UPDATE voorstellingen SET vrijeplaatsen = CASE WHEN vrijeplaatsen - ? >= 0 \n"
            + "                   THEN vrijeplaatsen - ?\n"
            + "                   ELSE vrijeplaatsen END where id = ?";

    private static final String ADD_RESERVATIE
            = "insert into reservaties(klantid, voorstellingsid, plaatsen) values (?, ?, ?)";

    private static final String GET_PID
            = "SELECT id FROM klanten WHERE gebruikersnaam = ?";

    Voorstelling voorstelling;

    public List<Voorstelling> findAll(String id) {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_ALL_VOORSTELLINGEN)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Voorstelling> voorst = new ArrayList<>();
                while (resultSet.next()) {
                    voorstelling = new Voorstelling(resultSet.getTimestamp("datum"),
                            resultSet.getString("titel"),
                            resultSet.getString("uitvoerders"),
                            resultSet.getString("prijs"),
                            resultSet.getInt("vrijeplaatsen"),
                            resultSet.getInt("id"));
                    voorst.add(voorstelling);
                }
                return voorst;
            }

        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
    }

    public Voorstelling findOne(String id2) {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_ONE_VOORSTELLING)) {
            statement.setString(1, id2);
            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    voorstelling = new Voorstelling(resultSet.getTimestamp("datum"),
                            resultSet.getString("titel"),
                            resultSet.getString("uitvoerders"),
                            resultSet.getString("prijs"),
                            resultSet.getInt("vrijeplaatsen"),
                            resultSet.getInt("voorstellingen.id"));

                }
                return voorstelling;
            }

        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
    }

    public boolean checkUserAndPassword(String user, String password) {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(CHECK_USER_AND_PASSWORD)) {
            statement.setString(1, user);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    if (resultSet.getString("paswoord").equals(password)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }

        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
    }

    public String naamEnAdres(String user) {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(NAAM_EN_ADRES)) {
            statement.setString(1, user);
            try (ResultSet resultSet = statement.executeQuery()) {
                String naamEnAdres = "";
                if (resultSet.next()) {

                    naamEnAdres = resultSet.getString("voornaam") + " "
                            + resultSet.getString("familienaam") + " "
                            + resultSet.getString("straat") + " "
                            + resultSet.getString("huisnr") + " "
                            + resultSet.getString("postcode") + " "
                            + resultSet.getString("gemeente");

                }
                return naamEnAdres;
            }

        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
    }

    public boolean checkIfUserExists(String user) {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(USER_EXISTS)) {
            statement.setString(1, user);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    return true;

                } else {
                    return false;
                }
            }

        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
    }

    public void AddNewUser(NieuweKlant nieuweklant) {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        NEW_USER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, nieuweklant.getVoornaam());
            statement.setString(2, nieuweklant.getFamilienaam());
            statement.setString(3, nieuweklant.getStraat());
            statement.setString(4, nieuweklant.getHuisnr());
            statement.setString(5, nieuweklant.getPostcode());
            statement.setString(6, nieuweklant.getGemeente());
            statement.setString(7, nieuweklant.getGebruikersnaam());
            statement.setString(8, nieuweklant.getPaswoord());

            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                resultSet.next();
                nieuweklant.setPersonalId(resultSet.getInt(1));
            }
        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
    }

    public boolean AddReservatie(String id, int aantal, int personalId) {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(CHANGE_VRIJEPLAATSEN)) {
            statement.setInt(1, aantal);
            statement.setInt(2, aantal);
            statement.setString(3, id);

            if (statement.executeUpdate() == 0) {
                return false;
            } else {

                try (PreparedStatement statement2 = connection.prepareStatement(ADD_RESERVATIE)) {

                    statement2.setInt(1, personalId);
                    statement2.setString(2, id);
                    statement2.setInt(3, aantal);

                    if (statement2.executeUpdate() == 0) {
                        return false;
                    } else {
                        return true;
                    }

                } catch (SQLException ex) {

                    return false;
                }
            }

        } catch (SQLException ex) {

            return false;
        }
    }

    public Integer findPID(String user) {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(GET_PID)) {
            statement.setString(1, user);
            try (ResultSet resultSet = statement.executeQuery()) {
                Integer pid = null;
                if (resultSet.next()) {
                    pid = resultSet.getInt("id");

                }
                return pid;
            }

        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
    }
}
