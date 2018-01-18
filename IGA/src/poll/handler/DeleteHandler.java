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

@Controller
public class DeleteHandler implements CommandHandler{
	
	@Resource
	private PollDao pollDao;
	
	@RequestMapping("/delete")
	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int s_num = Integer.parseInt(request.getParameter("s_num"));
		String id = request.getParameter("id");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "s_num", s_num );
		map.put( "id", id );
		
		List<Object> s_q_nums = pollDao.getq_nums(s_num);
		
		for(int i = 0; i<s_q_nums.size(); i++){
			int s_q_num = (int) s_q_nums.get(i);
			pollDao.deleteA(s_q_num);
		}
		pollDao.deleteQ(s_num);
		pollDao.deleteS(s_num);
		
		return new ModelAndView("poll/adminpage");
	}

}
