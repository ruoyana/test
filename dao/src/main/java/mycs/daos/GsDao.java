package mycs.daos;



import mycs.en.Gs;
import mycs.en.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Transactional
@Repository
public class GsDao {
	

	@Autowired
	private GsMapper gsMapper;



	public List<Gs> queryGs(int pageNo, int pageSize){

//		session = getSession();
		
		List<User> list = gsMapper.getUsers();
		

		pageNo = (pageNo) *  pageSize;

//    	List<Gs> list1 = session.createQuery("from Gs").setFirstResult(pageNo).setMaxResults(pageSize).list();

		List<Gs> list1 = gsMapper.queryGs(pageNo,pageSize);



		return list1;
	}

	public int gsqushangye(int pageNo,int pageSize){

//		session = getSession();

		Object count = gsMapper.gsqushangye(pageNo,pageSize);
// 		Object count = session.createQuery("select count(*) from Gs").uniqueResult();//31 / 5 = 6.1
		int a  = Integer.parseInt(count.toString());

		if(a % pageSize == 0){
			pageNo--;

		}

		return pageNo;
	}
//
//
	public int gssums(int pageSize){

// 	    session = getSession();


// 		Object count = session.createQuery("select count(*) from Gs").uniqueResult();

		Object count = gsMapper.gssums(pageSize);

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
////
////
////
////
////
public List<Gs> qugs() {
//	session = getSession();

	List<Gs> list = gsMapper.qugs();

// 	List<Gs> list = session.createQuery("from Gs").list();

	return list;

}
//
	@Transactional
	public void gsadd(Gs gs) {

//		session = getSession();
//
//		session.persist(gs);

		gsMapper.gsadd(gs);

	}
//
//
	@Transactional
	public Boolean gsdelete(int id){
		
		

		 
		
		List<User> list = gsMapper.getUsers();
		for (User user : list) {
	 
			
			if(user.getGs_id() == id) {
				return false;
			} 
			
		}
		
		gsMapper.gsdelete(id);
		return true;
		
 

	}
//
//
	@Transactional
	public void gsupdate(Gs gs) {

//		session = getSession();
//
//		Gs gs1 = session.get(Gs.class, gs.getSid());
//
//		   gs1.setYuangong(gs.getYuangong());

		 gsMapper.gsupdate(gs);

	}
//	
//	
	public Gs quidgs(int sid) {
		
//		session = getSession();
//
//		Gs gs = session.get(Gs.class,sid);
//
//		 return gs;

		Gs gs = gsMapper.quidgs(sid);

		 return gs;
		 
	 }
	
	

}
