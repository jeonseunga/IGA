package poll.handler;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import handler.CommandHandler;
import poll.MemberDataBean;
import poll.PollDao;
import poll.SurveyDataBean;

@Controller
public class MemberlistHandler implements CommandHandler{
	@Resource
	private PollDao pollDao;
	
	@RequestMapping("/memberlist")
	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int s_num = Integer.parseInt(request.getParameter("s_num"));
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		String title = pollDao.getTitle(s_num);
		request.setAttribute("title", title);
		
		int count = pollDao.memberCount(s_num);
		request.setAttribute("count", count);
		
		if(count!=0){
			List<MemberDataBean> member = pollDao.getMember(s_num);
			request.setAttribute("member", member);
		}
		
		return new ModelAndView("poll/memberlist");
	}

}