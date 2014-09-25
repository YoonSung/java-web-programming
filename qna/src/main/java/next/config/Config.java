package next.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import next.support.init.DatabaseInitializer;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value="classpath:/application-properties.xml")
public class Config {
	
	@Resource
	private Environment env;
	
	@Bean(initMethod="initialize")
	public DatabaseInitializer databaseInitialize() {
		return new DatabaseInitializer(dataSource());
	}
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
	
		basicDataSource.setDriverClassName(env.getRequiredProperty("database.driverClassName"));
		basicDataSource.setUrl(env.getRequiredProperty("database.url"));
		basicDataSource.setUsername(env.getRequiredProperty("database.username"));
		basicDataSource.setPassword(env.getRequiredProperty("database.password"));
		
		return basicDataSource;
	}
}