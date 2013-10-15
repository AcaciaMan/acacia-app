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

import java.net.URL;

import java.util.HashMap;
import java.util.Map;

/**
 * ApplicationFactory class containing Map of application configurations
 */
public class ApplicationFactory {
    
    public static final Map<String,Application> apps = new HashMap<String,Application>();
    
    public ApplicationFactory() {
        super();
    }
    
    private static Application initApplication() {
      Application app = new Application();
      app.propsMg = new PropertiesManager();
      app.propsMg.load();
      app.propsMg.loadDBConnections(app);
      return app;
    }
    
    public static Application getInstance(){
      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      URL url = classLoader.getResource(PropertiesManager.PROP_FILE);
      
      if(url==null) {
        System.out.println("New resource file created!");
        PropertiesManager pm = new PropertiesManager();
        pm.store();
        url = classLoader.getResource(PropertiesManager.PROP_FILE);
      }
      
      if(!apps.containsKey(url.getPath())) {
          // initialize application
          Application app = initApplication();
          apps.put(url.getPath(), app);
      }
            
      return apps.get(url.getPath());
      
    }
    
}
