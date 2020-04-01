package com.thoughtworks.exception;

import java.sql.SQLException;

public class NameNotMatchPassword extends SQLException {
    public NameNotMatchPassword() {
    }

    public NameNotMatchPassword(String message) {
        super(message);
    }
}
