package next.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.support.init.DatabaseInitializer;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import core.jdbc.JdbcTemplate;

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
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	public AnswerDao answerDao() {
		AnswerDao answerDao = new AnswerDao();
		answerDao.setJdbcTemplate(jdbcTemplate());
		return answerDao;
	}
	
	@Bean
	public QuestionDao questionDao() {
		QuestionDao questionDao = new QuestionDao();
		questionDao.setJdbcTemplate(jdbcTemplate());
		return questionDao;
	}
}