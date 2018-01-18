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
public class LoginProHandler implements CommandHandler{
	@Resource
	private PollDao polldao;
	
	@RequestMapping("/loginPro")
	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		int rs = polldao.check(id,pw);
		
		StringBuffer result = new StringBuffer("");
		result.append("{data:[");
		
			result.append("{rs : '"+ rs + "',");
			result.append("id : '"+ id + "'}");
			
		result.append("]}");
		request.setAttribute("result", result.toString());	
		
		
		return new ModelAndView("poll/loginPro");
	}

}
