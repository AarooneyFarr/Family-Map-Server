package dao;

import model.Authtoken;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * The Authtoken data access object.
 */
public class AuthtokenDAO{
    /**
     * The database connection
     * <p>
     * Type Connection
     */
    private final Connection conn;

    /**
     * Instantiates a new Authtoken dao.
     *
     * @param conn the connection
     */
    public AuthtokenDAO(Connection conn){
        this.conn = conn;
    }

    /**
     * Get the authtoken string for a user.
     *
     * @deprecated This method is not in use but is available for reference
     *
     * @param user the user
     * @return the string
     * @throws DataAccessException the data access exception
     */
//    public String getAuthtoken(User user) throws DataAccessException{
//        Authtoken token;
//        ResultSet rs;
//        String sql = "SELECT * FROM Authtoken WHERE Username = ?;";
//        String username = user.getUsername();
//
//        try(PreparedStatement stmt = conn.prepareStatement(sql)){
//            stmt.setString(1, username);
//            rs = stmt.executeQuery();
//            if(rs.next()){
//
//                return rs.getString("Authtoken");
//            } else{
//                return null;
//            }
//        } catch(SQLException e){
//            e.printStackTrace();
//            throw new DataAccessException("Error encountered while finding an token in the database");
//        }
//
//    }

    /**
     * Create authtoken string for a user.
     *
     * @param user the user
     * @return the string
     * @throws DataAccessException the data access exception
     */
    public String createAuthtoken(User user) throws DataAccessException{
        String authtoken = UUID.randomUUID().toString();
        Authtoken token = new Authtoken(authtoken, user.getUsername());
        this.insert(token);

        return authtoken;
    }

    /**
     * Insert.
     *
     * @param token the token
     * @throws DataAccessException the data access exception
     */
    private void insert(Authtoken token) throws DataAccessException{
        String sql = "INSERT INTO Authtoken (Authtoken, Username) VALUES(?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, token.getAuthtoken());
            stmt.setString(2, token.getUsername());

            stmt.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting a token into the database");
        }
    }

    /**
     * Clear.
     *
     * @throws DataAccessException the data access exception
     */
    public void clear() throws DataAccessException{
        String sql = "DELETE FROM Authtoken";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the authtoken table");
        }
    }

    /**
     * Find authtoken.
     *
     * @param tokenId the token id
     * @return the authtoken
     * @throws DataAccessException the data access exception
     */
    public Authtoken find(String tokenId) throws DataAccessException{
        Authtoken token;
        ResultSet rs;
        String sql = "SELECT * FROM Authtoken WHERE Authtoken = ?;";

        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, tokenId);
            rs = stmt.executeQuery();
            if(rs.next()){
                token = new Authtoken(rs.getString("Authtoken"), rs.getString("Username"));
                return token;
            } else{
                return null;
            }
        } catch(SQLException e){
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an token in the database");
        }
    }
}
