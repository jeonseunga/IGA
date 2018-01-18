package poll.handler;

import java.util.List;

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
public class GetSurveyHandler implements CommandHandler{
	@Resource
	private PollDao pollDao;
	
	@RequestMapping("/getSurvey")
	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String id = request.getParameter("id");
		
		int count = pollDao.surveyCount(id);
		if(count !=0){
			List<SurveyDataBean> survey = pollDao.getSurvey(id);
			
			StringBuffer result = new StringBuffer("");
			result.append("{data:[");
			
			for(int i = 0; i<survey.size(); i++){
				
				String title = survey.get(i).getS_name();
				int s_num = survey.get(i).getS_num();
				
				int ct = pollDao.getQcount(s_num);
				
				result.append("{title : '"+ title + "',");
				result.append("count : '"+count+"',");
				result.append("ct : '"+ct+"',");
				result.append("s_num : '"+ s_num + "'}");
				if(i != survey.size()-1) {
					result.append(",");
				}
				
			}
			result.append("]}");
			request.setAttribute("result", result.toString());
			
		}else{
			StringBuffer result = new StringBuffer("");
			result.append("{data:[");
			
				result.append("{title : '"+ "fs" + "'}");
	
			result.append("]}");
			request.setAttribute("result", result.toString());
		}
		return new ModelAndView("poll/getSurvey");
	}

}