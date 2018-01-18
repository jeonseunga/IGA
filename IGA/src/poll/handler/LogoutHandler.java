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
public class LogoutHandler implements CommandHandler {
	@Resource
	private PollDao pollDao;
	
	@RequestMapping("/logout")
	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String id = request.getParameter("id");
		request.getSession().removeAttribute(id);	
		return new ModelAndView("poll/login");
	}
}