package mycs.daos;

import mycs.en.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Transactional
@Repository
public class UserDao {
	
// 	private Session session;
//
//	@Autowired
//	private SessionFactory sessionFactory;
//
//	private Session getSession() {
//		return sessionFactory.getCurrentSession();
//	}

	@Autowired
	private UserMapper userMapper;


	

	public List<User> queryUser(int pageNo, int pageSize){
 
// 		session = getSession();

		pageNo = (pageNo) *  pageSize;

 		List<User> list1 = userMapper.queryUser(pageNo,pageSize);




// 		List<User> list1 = session.createQuery("from User").setFirstResult(pageNo).setMaxResults(pageSize).list();
		 
		
		return list1;
	}

	public int qushangye(int pageNo,int pageSize){

// 		session = getSession();

		Object count = userMapper.qushangye(pageNo,pageSize);
// 		Object count = session.createQuery("select count(*) from User").uniqueResult();//31 / 5 = 6.1
		int a  = Integer.parseInt(count.toString());
		System.out.println(a);
		if(a % pageSize == 0){
			pageNo--;

		}

		return pageNo;
	}
 
	public int sums(int pageSize){

// 		session = getSession();


// 		Object count = session.createQuery("select count(*) from User").uniqueResult();

		Object count = userMapper.sums(pageSize);

		int a  = Integer.parseInt(count.toString());
		int sum = 0;
		if(a % pageSize == 0){
			sum = a / pageSize;
		}else {
			sum = a / pageSize;
			sum++;
		}

		return sum;
	}
//
//
//
//
//
	 public void delete(int id){



//		  session = getSession();

//		  User user = new User();
//
//		  user.setId(id);
//		  session.delete(user);

		 userMapper.delete(id);
		 
	  }
//
//		public List<Gs> qugs() {
//			
//			session = getSession();
//
////			List<Gs> list = userMapper.qugs();
//
// 			List<Gs> list = session.createQuery("from Gs").list();
//
//			return list;
//
//		}
//
//
//
//
//
	 public void update(User user) {


		 
// 		 session = getSession();
////
//		 User user1 = session.get(User.class, user.getId());
//		 user1.setName(user.getName());
//		 user1.setAddr(user.getAddr());
//		 user1.setSex(user.getSex());
//		 user1.setPassword(user.getPassword());
//		 user1.setBirth(user.getBirth());
//		 user1.setHead(user.getHead());
//		 user1.setGs(user.getGs());

		 userMapper.update(user);

		
	 }
//	 
//	 
//	 
// 
//
	 public void add(User user) {
		 
//		 session = getSession();
//
//		 session.persist(user);

		 userMapper.add(user);
	      
	 }
//	 
//	 
	 public User queryid(int id) {


//		 session = getSession();
//
//		 User user = session.get(User.class, id);

		 User user = userMapper.queryid(id);
		 
		 
		 return user;


	 }

	public void addlist(List<User> user) {

		for (User user1 : user) {
			userMapper.add(user1);
		}

	}

	public List<User> querysy() {

		List<User> user= userMapper.querysy();

//		System.out.println(user.toString());
		return user;


	}



 
	 
	
}
