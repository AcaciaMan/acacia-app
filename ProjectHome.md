Many small start-up Java projects applications require same basic facilities. Applications usually have Properties files. They contain configuration for application objects and variables. This Java library has:
  * ApplicationFactory class containing Map of application configurations, It is not static,  so that it could be extended
  * Application class pointning to it's config.properties file,
  * PropertiesManager and OracleDatabase classes.