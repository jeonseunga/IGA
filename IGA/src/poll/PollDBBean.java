package poll;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import db.SqlMapClient;


@Component("pollDao")
public class PollDBBean implements PollDao{
	//여기에 세션을 가져온다. 
	private SqlSession session = SqlMapClient.getSession(); //getsession으로 세션을 받아온다

	@Override
	public int check(String id) {
		int count = session.selectOne("Poll.check", id);
		return count;
	}

	@Override
	public int check(String id, String pw) {
		int result = 0;
		int count = check(id);
		if(count == 0) {
			// 아이디가 없다.
			result = 0;
		} else {
			// 아이디가 있다.
			AdminDataBean adminDto = getAdmin(id);
			if(pw.equals(adminDto.getAdmin_pw())) {
				// 비밀번호가 같다.
				result = 1;
			} else {
				// 비밀번호가 다르다.
				result = -1;
			}
		}
		return result;
	}
	
	public AdminDataBean getAdmin(String id) {
		return session.selectOne("Poll.getAdmin", id);
	}

	@Override
	public int surveyCount(String id) {
		return session.selectOne("Poll.surveyCount", id);
	}

	@Override
	public List<SurveyDataBean> getSurvey(String id) {
		return session.selectList("Poll.getSurvey",id);
	}

	@Override
	public int insertSurvey(Map<String, Object> map) {
		return session.insert("Poll.insertSurvey",map);
	}

	@Override
	public int getS_num(Map<String, Object> map) {
		return session.selectOne("Poll.getS_num",map);
	}

	@Override
	public int insertQ(Map<String, Object> map) {
		return session.insert("Poll.insertQ",map);
	}

	@Override
	public int getS_q_num(Map<String, Object> map) {
		return session.selectOne("Poll.getS_q_num",map);
	}

	@Override
	public int insertA(Map<String, Object> map) {
		return session.insert("Poll.insertA",map);
	}

	@Override
	public List<SurveyDataBean> getSurveyInfo(Map<String, Object> map) {
		return session.selectList("Poll.getSurveyInfo",map);
	}

	@Override
	public int checkName(Map<String, Object> map) {
		return session.selectOne("Poll.checkName",map);
	}

	@Override
	public int insertMember(Map<String, Object> map) {
		return session.insert("Poll.insertMember",map);
	}

	@Override
	public List<SurveyQDataBean> getSurveyQ(int s_num) {
		return session.selectList("Poll.getSurveyQ",s_num);
	}

	@Override
	public List<SurveyADataBean> getSurveyA(int q_num) {
		return session.selectList("Poll.getSurveyA",q_num);
	}

	@Override
	public int getCount(int anum) {
		return session.selectOne("Poll.getCount",anum);
	}

	@Override
	public void insertCount(Map<String, Object> map) {
		session.update("Poll.insertCount",map);
	}

	@Override
	public int getQcount(int s_num) {
		return session.selectOne("Poll.getQcount",s_num);
	}

	@Override
	public int memberCount(int s_num) {
		return session.selectOne("Poll.memberCount",s_num);
	}

	@Override
	public List<MemberDataBean> getMember(int s_num) {
		return session.selectList("Poll.getMember",s_num);
	}

	@Override
	public String getTitle(int s_num) {
		return session.selectOne("Poll.getTitle",s_num);
	}

	@Override
	public int todayCount(Map<String, Object> map) {
		return session.selectOne("Poll.todayCount",map);
	}

	@Override
	public List<Object> getq_nums(int s_num) {
		return session.selectList("Poll.getq_nums",s_num);
	}

	@Override
	public void deleteS(int s_num) {
		session.delete("Poll.deleteS",s_num);
	}

	@Override
	public void deleteA(int s_q_num) {
		session.delete("Poll.deleteA",s_q_num);
	}

	@Override
	public void deleteQ(int s_num) {
		session.delete("Poll.deleteQ",s_num);
	}

}