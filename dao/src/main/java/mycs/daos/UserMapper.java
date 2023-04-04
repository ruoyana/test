package mycs.daos;


import mycs.en.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {


	
	public List<User> queryUser(@Param("pageNo") int pageNo, @Param("pageSize")int pageSize);
	
	public int sums(int pageSize);
	
	public int qushangye(@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public void delete(int id);
	
	public void add(User user);
	
	 public User queryid(int id);
	 
	 public void update(User user);

	 public List<User> querysy();



}
