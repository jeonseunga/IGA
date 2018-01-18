package poll.handler;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import handler.CommandHandler;
import poll.PollDao;

@Controller
public class SaveSurveyHandler implements CommandHandler{
	
	@Resource
	private PollDao pollDao;
	
	@RequestMapping("/saveSurvey")
	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String id = request.getParameter("id");
		String surveyName = request.getParameter("surveyName");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "id", id );
		map.put( "surveyName", surveyName );
	
		int rs = pollDao.insertSurvey(map);
		
		if(rs !=0 ){
			
			int s_num = pollDao.getS_num(map);
			
			StringBuffer result = new StringBuffer("");
			result.append("{data:[");
			
				result.append("{s_num : '"+ s_num + "'}");
				
			result.append("]}");
			request.setAttribute("result", result.toString());
			
		}else{
			
			StringBuffer result = new StringBuffer("");
			result.append("{data:[");
			
				result.append("{s_num : 'fs'}");
				
			result.append("]}");
			request.setAttribute("result", result.toString());
			
		}
		
		return new ModelAndView("poll/saveSurvey");
	}

}
