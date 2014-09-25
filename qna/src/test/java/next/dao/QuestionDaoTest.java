package next.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import next.config.Config;
import next.model.Question;
import next.support.db.ConnectionManager;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class QuestionDaoTest {
	
	QuestionDao questionDao;
	
	@Before
	public void setup() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("qna.sql"));
		DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
		
		try (AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class)) {
			questionDao = ac.getBean(QuestionDao.class);
		}
	}

	@Test
	public void crud() throws Exception {
		Question expected = new Question("자바지기", "title", "contents");
		questionDao.insert(expected);
		
		List<Question> questions = questionDao.findAll();
		assertTrue(questions.size() > 0);
	}
}
