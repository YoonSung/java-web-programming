package next.support.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import next.config.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class QnaContextLoaderListender implements ServletContextListener {
	
	private static final Logger log = LoggerFactory.getLogger(QnaContextLoaderListender.class);
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(Config.class);
		context.refresh();
		sce.getServletContext().setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, context);
		
		log.info("ApplicationContext Initialize!");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
