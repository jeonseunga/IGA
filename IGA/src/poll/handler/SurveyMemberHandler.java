package poll.handler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import handler.CommandHandler;
import poll.PollDao;

@Controller
public class SurveyMemberHandler implements CommandHandler{
	@Resource
	private PollDao pollDao;
	
	@RequestMapping("/surveyMember")
	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int s_num = Integer.parseInt(request.getParameter("s_num"));
		request.setAttribute("s_num", s_num);
		return new ModelAndView("poll/surveyMember");
	}

}
