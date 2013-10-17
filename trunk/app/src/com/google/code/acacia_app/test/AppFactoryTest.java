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


package com.google.code.acacia_app.test;

import com.google.code.acacia_app.Application;

import com.google.code.acacia_app.ApplicationFactory;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AppFactoryTest {
    public AppFactoryTest() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

  /**
     * @see com.google.code.acacia_app.ApplicationFactory#getInstance()
     */
  @Test
  public void testEmpty() {
        ApplicationFactory.apps.clear();
      System.out.println("AppFactory size " + ApplicationFactory.apps.size());
  }

    /**
     * @see com.google.code.acacia_app.ApplicationFactory#getInstance()
     */
    @Test
    public void testGetInstance() {
        ApplicationFactory factory = new ApplicationFactory();
        Application app = factory.getInstance();
        app.select("select * from dual");
    }
}
