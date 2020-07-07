package com.lechowicz.dao;

import com.lechowicz.exception.NoUserException;
import com.lechowicz.exception.ReadException;
import com.lechowicz.model.User;
import org.mockito.Mock;
import org.postgresql.ds.PGSimpleDataSource;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class UserJDBCDAOTest {

    @Mock
    PGSimpleDataSource mockDataSource;
    @Mock
    Connection mockConn;
    @Mock
    PreparedStatement mockPreparedStmnt;
    @Mock
    ResultSet mockResultSet;

    @org.junit.jupiter.api.Test
    void getUser() throws SQLException {
        mockDataSource = mock(PGSimpleDataSource.class);
        mockPreparedStmnt = mock(PreparedStatement.class);
        mockConn = mock(Connection.class);
        mockResultSet = mock(ResultSet.class);

        when(mockDataSource.getConnection()).thenReturn(mockConn);
        when(mockConn.prepareStatement("SELECT * FROM users WHERE name = ? AND hash_pass = ?"))
                .thenReturn(mockPreparedStmnt);
        when(mockPreparedStmnt.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);

        UserJDBCDAO dao = new UserJDBCDAO(mockDataSource);

        try {
            dao.getUser("simple", 123123);
        } catch (ReadException e) {
            e.printStackTrace();
        } catch (NoUserException e) {
            e.printStackTrace();
        }

        verify(mockConn, times(1)).prepareStatement(anyString());
        verify(mockPreparedStmnt, times(1)).setString(anyInt(), anyString());
        verify(mockPreparedStmnt, times(1)).setInt(anyInt(),anyInt());
        verify(mockPreparedStmnt, times(1)).executeQuery();
        //verify(mockConn, times(1)).commit();
        verify(mockResultSet, times(1)).next();
        //verify(mockResultSet, times(1)).getInt(String.valueOf(Fields.syntheticField()));
    }
}