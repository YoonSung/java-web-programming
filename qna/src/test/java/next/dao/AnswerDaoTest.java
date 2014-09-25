package next.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import next.config.Config;
import next.model.Answer;
import next.support.db.ConnectionManager;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class AnswerDaoTest {
	
	AnswerDao answerDao;
	
	@Before
	public void setup() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("qna.sql"));
		DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
		
		try (AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class)) {
			answerDao = ac.getBean(AnswerDao.class);
		}
	}

	@Test
	public void crud() throws Exception {
		long questionId = 1L;
		Answer expected = new Answer("javajigi", "answer contents", questionId);
		answerDao.insert(expected);
		
		List<Answer> answers = answerDao.findAllByQuestionId(questionId);
		assertTrue(answers.size() > 0);
	}
}
