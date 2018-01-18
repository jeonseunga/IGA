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
public class InsertCountHandler implements CommandHandler{
	
	@Resource
	private PollDao pollDao;
	
	@RequestMapping("/insertCount")
	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int anum = Integer.parseInt(request.getParameter("anum"));
		
		int ct = pollDao.getCount(anum);
		int rec = ct+1;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "anum", anum );
		map.put( "rec", rec );
		pollDao.insertCount(map);
		
		
		
		return new ModelAndView("poll/insertCount");
	}

}
