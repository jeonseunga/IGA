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
public class SaveAHandler implements CommandHandler{
	
	@Resource
	private PollDao pollDao;
	
	@RequestMapping("/saveA")
	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int s_q_num = Integer.parseInt(request.getParameter("s_q_num"));
		String a = request.getParameter("a");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "s_q_num", s_q_num );
		map.put( "a", a );
		
		int rs = pollDao.insertA(map);
		
		if(rs !=0 ){
			
			StringBuffer result = new StringBuffer("");
			result.append("{data:[");
			
				result.append("{state : 'ss'}");
				
			result.append("]}");
			request.setAttribute("result", result.toString());
			
		}else{
			
			StringBuffer result = new StringBuffer("");
			result.append("{data:[");
			
				result.append("{state : 'fs'}");
				
			result.append("]}");
			request.setAttribute("result", result.toString());
			
		}
		
		return new ModelAndView("poll/saveA");
	}

}
