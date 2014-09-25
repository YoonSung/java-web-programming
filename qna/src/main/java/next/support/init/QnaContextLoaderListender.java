package next.support.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import next.config.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class QnaContextLoaderListender implements ServletContextListener {
	
	private static final Logger log = LoggerFactory.getLogger(QnaContextLoaderListender.class);
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class)) {
			context.refresh();
			log.info("ApplicationContext Initialize!");
		} catch(Exception e) {
			log.error("ApplicationContext Initialize Error : {}", e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
