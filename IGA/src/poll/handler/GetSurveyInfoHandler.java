package poll.handler;

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
import poll.PollDao;
import poll.SurveyDataBean;

@Controller
public class GetSurveyInfoHandler implements CommandHandler{
	@Resource
	private PollDao pollDao;
	
	@RequestMapping("/getSurveyInfo")
	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int s_num = Integer.parseInt(request.getParameter("s_num"));
		String id = request.getParameter("id");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "s_num", s_num );
		map.put( "id", id );
		
		List<SurveyDataBean> survey = pollDao.getSurveyInfo(map);
		
		StringBuffer result = new StringBuffer("");
		result.append("{data:[");
			
			String title = survey.get(0).getS_name();
			
			result.append("{title : '"+ title + "'}");
			
		result.append("]}");
		request.setAttribute("result", result.toString());
		
		return new ModelAndView("poll/getSurveyInfo");
	}

}
