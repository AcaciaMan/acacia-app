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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.URISyntaxException;
import java.net.URL;

import java.util.Enumeration;
import java.util.Properties;

public class PropertiesManager {
    
  public static final String PROP_FILE = "config.properties";
  public static final String DB = "db"; 

  public Properties props;
  public String firstDB;
  public URL url;

  public PropertiesManager() {
      super();
  }

  public void load() {
      props = new Properties();

      try {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
          //load a properties file
          props.load(classLoader.getResourceAsStream(PROP_FILE));
      } catch (IOException ex) {
          ex.printStackTrace();
          store();
      }

  }

  public void loadDBConnections(Application app) {

      OracleDatabase d;

      Enumeration e = props.propertyNames();

      while (e.hasMoreElements()) {

          String name = (String)e.nextElement();

          if (!name.startsWith(DB+ "."))
              continue;


          String[] split = name.split("\\.");
          if (DB.equals(split[0])) {
              if (firstDB == null)
                  firstDB = split[1];
              d = app.getDbs().get(split[1]);
              if (d == null) {
                  d = new OracleDatabase();
                  app.getDbs().put(split[1], d);
              }

              d.setProperty(split[2], props.getProperty(name));

          }

      }

      if (props.getProperty("CurrentDB") != null &&
          props.getProperty("CurrentDB").length() > 0) {
          app.setCurrentDB(props.getProperty("CurrentDB"));
      } else {
        app.setCurrentDB(firstDB);
      }

  }

  public void store() {
    props = new Properties();
      try {
          //set the properties value
          props.setProperty(DB+".XE.database", "jdbc\\:oracle\\:thin\\:@localhost\\:1521\\:XE");
          props.setProperty(DB+".XE.user", "user");
          props.setProperty(DB+".XE.password", "password");

          //save properties to project root folder
          props.store(new FileOutputStream(PROP_FILE), null);

      } catch (IOException ex) {
          ex.printStackTrace();
      }
  }
  
  public URL getUrl(){
      if(url == null) {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    url = classLoader.getResource(PROP_FILE);
      }
    return url;
  }


  /*
   * Get application properties file last modified date
   * If properties file changes, then on next getInstance call application is reinitialized
   */
  public long getPropertiesLastModified() {

    File f;
    try {
      f = new File(getUrl().toURI());
    } catch(URISyntaxException e) {
      f = new File(getUrl().getPath());
    }        
    
    return f.lastModified();
  }

    
}
