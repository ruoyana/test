package mycs.daos;


import mycs.en.Gs;
import mycs.en.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GsMapper {
	
	public List<Gs> queryGs(@Param("pageNo")int pageNo, @Param("pageSize") int pageSize);
	
	public int gssums(int pageSize);
	
	public List<Gs> qugs();
	
	public int gsqushangye(@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public Boolean gsdelete(int id);
	
	public void gsadd(Gs gs);
	
	public void gsupdate(Gs gs);
	
	
	public Gs quidgs(int sid);
	
	public List<User> getUsers();
}
