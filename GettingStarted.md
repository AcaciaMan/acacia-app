Getting started

# Introduction #

The Application class example.


# Details #
```

    @Test
    public void testGetInstance() {
        ApplicationFactory factory = new ApplicationFactory();
        Application app = factory.getInstance();
        app.select("select * from dual");
    }
```