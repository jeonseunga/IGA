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
import poll.SurveyQDataBean;

@Controller
public class GetSurveyQHandler implements CommandHandler{
	@Resource
	private PollDao pollDao;
	
	@RequestMapping("/getSurveyQ")
	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int s_num = Integer.parseInt(request.getParameter("s_num"));
		
		List<SurveyQDataBean> surveyQ = pollDao.getSurveyQ(s_num);
		
		StringBuffer result = new StringBuffer("");
		result.append("{data:[");
		
		for(int i = 0; i<surveyQ.size(); i++){
			
			int q_num = surveyQ.get(i).getS_q_num();
			String q = surveyQ.get(i).getS_q();
			int aform = surveyQ.get(i).getS_a_form();
			
			result.append("{q_num : '"+ q_num + "',");
			result.append("q : '"+q+"',");
			result.append("aform : '"+ aform + "'}");
			if(i != surveyQ.size()-1) {
				result.append(",");
			}
			
		}
		result.append("]}");
		request.setAttribute("result", result.toString());
		
		return new ModelAndView("poll/getSurveyQ");
	}

}