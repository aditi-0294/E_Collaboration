package com.niit.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
//import org.apache.commons.dbcp.BasicDataSource;
//import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.model.BlogComment;
import com.niit.model.BlogPost;
import com.niit.model.Friend;
import com.niit.model.Job;
import com.niit.model.ProfilePicture;
import com.niit.model.User;

@Configuration
@EnableTransactionManagement
public class DBConfig {

		public DBConfig() {
			
			System.out.println("Default constructor of DBConfig.java") ;
			System.out.println();
			
		}
	
	// create an instance
		@Bean
		public SessionFactory sessionFactory() {
			
			System.out.println("****** Creation of Data Source Bean started .... ******");
			
			LocalSessionFactoryBuilder lsf = 
					new LocalSessionFactoryBuilder(getDataSource()) ;
			
			Properties hibernateProperties = new Properties() ;
			
			hibernateProperties.setProperty(
					"hibernate.dialect" , "org.hibernate.dialect.Oracle10gDialect") ;
			
			hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update") ;
			
			hibernateProperties.setProperty("hibernate.show_sql", "true") ;
			
			lsf.addProperties(hibernateProperties) ;
			
			Class classes[] = new Class[] {User.class , Job.class , BlogPost.class , BlogComment.class , ProfilePicture.class , Friend.class} ;
			
			System.out.println("****** Datasource bean creation process completed .... ******");
			
			return lsf.addAnnotatedClasses(classes).buildSessionFactory();
			
					
		}
		
		
		@Bean
		public DataSource getDataSource() {
			
			BasicDataSource dataSource = new BasicDataSource() ;
			
			System.out.println("****** Connecting to Oracle Application Express .... ******");
			
			//Workspace name : sanaditi , username : sanaditi , password : sanaditi
			
			dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver"); // standard 
			
			dataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:XE"); // standard 
			
			dataSource.setUsername("project_2");

			dataSource.setPassword("sanaditi");
			
			System.out.println("****** Connected to Oracle Application Express .... ******");
			
			return dataSource ;
			
		}
		
		
		@Bean
		public HibernateTransactionManager hibTransManagement() {
			
			return new HibernateTransactionManager(sessionFactory()) ;
			
		}
		
}
