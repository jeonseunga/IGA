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
import poll.SurveyADataBean;

@Controller
public class GetSurveyAHandler implements CommandHandler{
	@Resource
	private PollDao pollDao;
	
	@RequestMapping("/getSurveyA")
	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int q_num = Integer.parseInt(request.getParameter("q_num"));
		
		List<SurveyADataBean> surveyA = pollDao.getSurveyA(q_num);
		
		StringBuffer result = new StringBuffer("");
		result.append("{data:[");
		
		for(int i = 0; i<surveyA.size(); i++){
			
			int a_num = surveyA.get(i).getS_a_num();
			String a = surveyA.get(i).getS_a();
			int count = surveyA.get(i).getS_a_count();
			
			result.append("{a_num : '"+ a_num + "',");
			result.append("count : '"+ count + "',");
			result.append("a : '"+ a +"'}");
			
			if(i != surveyA.size()-1) {
				result.append(",");
			}
		}
		result.append("]}");
		request.setAttribute("result", result.toString());
		
		return new ModelAndView("poll/getSurveyA");
	}

}