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
public class SaveQHandler implements CommandHandler{
	
	@Resource
	private PollDao pollDao;
	
	@RequestMapping("/saveQ")
	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String qname = request.getParameter("qname");
		String qform = request.getParameter("qform");
		int s_num =  Integer.parseInt(request.getParameter("s_num"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "qname", qname );
		map.put( "qform", qform );
		map.put( "s_num", s_num );
	
		int rs = pollDao.insertQ(map);
		
		if(rs !=0 ){
			
			int s_q_num = pollDao.getS_q_num(map);
			
			StringBuffer result = new StringBuffer("");
			result.append("{data:[");
			
				result.append("{s_q_num : '"+ s_q_num + "'}");
				
			result.append("]}");
			request.setAttribute("result", result.toString());
			
		}else{
			
			StringBuffer result = new StringBuffer("");
			result.append("{data:[");
			
				result.append("{s_q_num : 'fs'}");
				
			result.append("]}");
			request.setAttribute("result", result.toString());
			
		}
		
		return new ModelAndView("poll/saveQ");
	}

}
