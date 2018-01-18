package poll;

import java.util.List;
import java.util.Map;

public interface PollDao {

	public int check(String id); //1

	public int check(String id, String pw);//1-1

	public int surveyCount(String id);

	public List<SurveyDataBean> getSurvey(String id);

	public int insertSurvey(Map<String, Object> map);

	public int getS_num(Map<String, Object> map);

	public int insertQ(Map<String, Object> map);

	public int getS_q_num(Map<String, Object> map);

	public int insertA(Map<String, Object> map);

	public List<SurveyDataBean> getSurveyInfo(Map<String, Object> map);

	public int checkName(Map<String, Object> map);

	public int insertMember(Map<String, Object> map);

	public List<SurveyQDataBean> getSurveyQ(int s_num);

	public List<SurveyADataBean> getSurveyA(int q_num);

	public int getCount(int anum);

	public void insertCount(Map<String, Object> map);

	public int getQcount(int s_num);

	public int memberCount(int s_num);

	public List<MemberDataBean> getMember(int s_num);

	public String getTitle(int s_num);

	public int todayCount(Map<String, Object> map);

	public List<Object> getq_nums(int s_num);

	public void deleteS(int s_num);

	public void deleteA(int s_q_num);

	public void deleteQ(int s_num);




}
