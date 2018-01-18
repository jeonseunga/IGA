package poll.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
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
public class StartSurveyHandler implements CommandHandler{
	
	@Resource
	private PollDao pollDao;
	
	@RequestMapping("/startSurvey")
	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String member_name = request.getParameter("member_name");
		int s_num = Integer.parseInt(request.getParameter("s_num"));
		
		Date day = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(day);
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "member_name", member_name );
		map.put( "s_num", s_num );
		map.put( "today", today );
	
		int check = pollDao.checkName(map);
		
		if(check==0){
			//아이디가 없다
			int rs = pollDao.insertMember(map);
			if(rs!=0){
				StringBuffer result = new StringBuffer("");
				result.append("{data:[");
				
					result.append("{check : '"+ check + "'}");
					
				result.append("]}");
				request.setAttribute("result", result.toString());
			}
		}else{
			StringBuffer result = new StringBuffer("");
			result.append("{data:[");
			
				result.append("{check : '"+ check + "'}");
				
			result.append("]}");
			request.setAttribute("result", result.toString());
		}
		
		return new ModelAndView("poll/startSurvey");
	}

}
