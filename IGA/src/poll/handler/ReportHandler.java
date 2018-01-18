package poll.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class ReportHandler implements CommandHandler{
	@Resource
	private PollDao pollDao;
	
	@RequestMapping("/report")
	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int s_num = Integer.parseInt(request.getParameter("s_num"));
		request.setAttribute("s_num", s_num);
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		String title = pollDao.getTitle(s_num);
		request.setAttribute("title", title);
		
		int count = pollDao.memberCount(s_num);
		request.setAttribute("count", count);
		
		Date day = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(day);
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "s_num", s_num );
		map.put( "today", today );
		
		int ct = pollDao.todayCount(map);
		request.setAttribute("ct", ct);
		
		
		
		return new ModelAndView("poll/report");
	}

}