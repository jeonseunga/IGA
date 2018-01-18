package poll.handler;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import handler.CommandHandler;

@Controller
public class CompleteHandler implements CommandHandler{
	@RequestMapping("/complete")
	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		return new ModelAndView("poll/complete");
	}

}
