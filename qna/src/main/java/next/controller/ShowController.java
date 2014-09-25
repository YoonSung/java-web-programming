package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import core.mvc.Controller;
import core.util.ApplicationContextUtils;

public class ShowController implements Controller {
	private QuestionDao questionDao;
	private AnswerDao answerDao;
	
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		questionDao = ApplicationContextUtils.getBean(request, QuestionDao.class);
		answerDao = ApplicationContextUtils.getBean(request, AnswerDao.class);
		
		
		long questionId = Long.parseLong(request.getParameter("questionId"));
		Question question;
		List<Answer> answers;
		question = questionDao.findById(questionId);
		answers = answerDao.findAllByQuestionId(questionId);
		request.setAttribute("question", question);
		request.setAttribute("answers", answers);
		
		return "show.jsp";
	}
}
