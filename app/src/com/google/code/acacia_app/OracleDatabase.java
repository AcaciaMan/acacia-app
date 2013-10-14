/*
 * Copyright 2013 Acacia Man
 * The program is distributed under the terms of the GNU General Public License
 * 
 * This file is part of acacia-app.
 * 
 * acacia-app is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 * 
 * acacia-app is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with acacia-app.  If not, see <http://www.gnu.org/licenses/>.
 */


package com.google.code.acacia_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;

import oracle.jdbc.pool.OracleDataSource;

public class OracleDatabase {
    public OracleDatabase() {
        super();
    }
  
  String databse;
  String user;
  String password;
  
  
  public void setProperty(String type, String value) {
    if(type.equals("database")) this.setDatabse(value);
    else if (type.equals("user")) this.setUser(value);
    else if (type.equals("password")) this.setPassword(value);
  }
  
  
  public Connection getDBConnection(){
    OracleDataSource ds;
    Connection conn = null;
      try {
          ds = new OracleDataSource();
        ds.setURL(this.getDatabse());
        conn = ds.getConnection(this.getUser(),this.getPassword());


      } catch (SQLException e) {
        e.printStackTrace();
      }
    return conn;
  }
  
  public Connection getConnection() {

    Properties props = new Properties(); 
    props.put("user", this.getUser()); 
    props.put("password", this.getPassword()); 
    Connection conn = null;

      try {
          conn = DriverManager.getConnection (this.getDatabse(), props);
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return conn;
  }

  public void setDatabse(String databse) {
      this.databse = databse;
  }

  public String getDatabse() {
      return databse;
  }

  public void setUser(String user) {
      this.user = user;
  }

  public String getUser() {
      return user;
  }

  public void setPassword(String password) {
      this.password = password;
  }

  public String getPassword() {
      return password;
  }
}
