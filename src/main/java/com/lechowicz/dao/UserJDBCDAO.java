package com.lechowicz.dao;

import com.lechowicz.exception.DatabaseException;
import com.lechowicz.exception.NoUserException;
import com.lechowicz.exception.ReadException;
import com.lechowicz.model.User;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class UserJDBCDAO implements UserDAO {
    private String DBUser;
    private String DBUrl;
    private String DBPassword;

    public UserJDBCDAO() throws DatabaseException {
        Properties prop;
        try {
            prop = PropertiesReader.readProperties("src/main/resources/database.properties");
        }catch(IOException ex){
            throw new DatabaseException(ex.getMessage());
        }
        DBUrl = prop.getProperty("db.url");
        DBUser = prop.getProperty("db.user");
        DBPassword = prop.getProperty("db.passwd");
    }


    @Override
    public User getUser(String name, int hashPass) throws ReadException, NoUserException{
        try(Connection con = DriverManager.getConnection(this.DBUrl, this.DBUser, this.DBPassword);
            PreparedStatement pst = con.prepareStatement("SELECT * FROM users WHERE name = ? AND hash_pass = ?")) {
            pst.setString(1, name);
            pst.setInt(2, hashPass);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                return new User(name, hashPass);
            }else{
                throw new NoUserException("There is no user for this name and password.");
            }
        } catch (SQLException ex) {
            throw new ReadException("You cannot access to database." + ex);
        }
    }
}
