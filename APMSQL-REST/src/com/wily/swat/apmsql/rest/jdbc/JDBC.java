package com.wily.swat.apmsql.rest.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.teiid.jdbc.TeiidDriver;

import com.wily.swat.apmsql.rest.config.Config;

import java.io.IOException;

import javax.sql.DataSource;

public class JDBC 
  {
    //private final static String TeiidDriver= "org.teiid.jdbc.TeiidDriver";
	
	private static JdbcTemplate jdbcTemplate;

    public static <T> T execute(String sql, ResultSetExtractor<T> extractor) throws IOException 
      {
        if(jdbcTemplate == null) 
          jdbcTemplate = init();

        return jdbcTemplate.query(sql, extractor);
      } 

    private static JdbcTemplate init() throws IOException 
     { return new JdbcTemplate(getDs()); }

    private static DataSource getDs() throws IOException 
      {
        String url = Config.getProperty("jdbcurl");
        String user = Config.getProperty("jdbcuser");
        String pass = Config.getProperty("jdbcpass");
        
        System.out.println("Teiid JDBC connection: User: '" + user + "', url: '" + url + "'");

        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(url);
        ds.setUsername(user);
        ds.setPassword(pass);
        //ds.setDriverClassName(TeiidDriver.class.getName());
        ds.setDriver(TeiidDriver.getInstance());

        return ds;
      }
  }
