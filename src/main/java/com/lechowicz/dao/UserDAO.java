package com.lechowicz.dao;

import com.lechowicz.exception.NoUserException;
import com.lechowicz.exception.ReadException;
import com.lechowicz.model.User;

public interface UserDAO {
    User getUser(String name, int hashPass) throws ReadException, NoUserException;
}
