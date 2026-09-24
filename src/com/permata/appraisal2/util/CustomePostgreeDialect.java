package com.permata.appraisal2.util;

import org.hibernate.dialect.PostgreSQL82Dialect;

import java.sql.Types;

public class CustomePostgreeDialect extends PostgreSQL82Dialect {

    public CustomePostgreeDialect(){
        super();
        this.registerColumnType(Types.BLOB, "bytea");
    }

}