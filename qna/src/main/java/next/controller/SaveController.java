package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.Controller;
import core.util.ApplicationContextUtils;

public class SaveController implements Controller {
	QuestionDao dao;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		dao = ApplicationContextUtils.getBean(request, QuestionDao.class);
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		Question question = new Question(writer, title, contents);

		dao.insert(question);
		return "redirect:/list.next";
	}

}
