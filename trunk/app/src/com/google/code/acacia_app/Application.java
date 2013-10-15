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

import java.io.IOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Application class pointing to its properties file config.properties
 */
public class Application {
    public Application() {
        super();
    }


  public String currentDB;
  public Connection conn;
  public Map<String, OracleDatabase> dbs = new HashMap<String, OracleDatabase>();
  public PropertiesManager propsMg;

  public void setProperties(Properties props) {
      this.propsMg.props = props;
  }

  public Properties getProperties() {
      return this.propsMg.props;
  }

  public void setConnection(Connection conn) {
      this.conn = conn;
  }

  public Connection getConnection() {

      if (conn == null) {
          System.out.println("currentDB = " + currentDB);
          conn = dbs.get(currentDB).getDBConnection();
      }
      return conn;
  }


  public void setCurrentDB(String currentDB) {
      this.currentDB = currentDB;
      conn = null;
  }

  public String getCurrentDB() {
      return currentDB;
  }

  public void setDbs(Map<String,OracleDatabase> dbs) {
      this.dbs = dbs;
  }

  public Map<String, OracleDatabase> getDbs() {
      return dbs;
  }

  public ResultSet select(CharSequence cs) {
      Statement stmt;
      ResultSet rs = null;
      try {
          stmt = getConnection().createStatement();
          rs = stmt.executeQuery(cs.toString());
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return rs;
  }

  public CallableStatement prepareCall(CharSequence cs) {
    CallableStatement stmt = null;
    try {
        stmt = getConnection().prepareCall(cs.toString());
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return stmt;
  }

}
