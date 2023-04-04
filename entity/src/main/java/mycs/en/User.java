package mycs.en;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.util.Date;


@Entity
@Table(name="user")
public class User {
	
	 
	private int id;
	@Length(max=10)
	private String name;
	private String addr;
	private String sex;
	 
	private String head;
	private String money;
	
	private Gs gs; 
	
	private int gs_id;
	
	
	public int getGs_id() {
		return gs_id;
	}
	public void setGs_id(int gs_id) {
		this.gs_id = gs_id;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id; 
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	
	
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
 
	
	
 

	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}





	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date birth;
 
	
	@JoinColumn(name="gs_id")
	@ManyToOne(fetch=FetchType.EAGER)
	public Gs getGs() {
		return gs;
	}
	public void setGs(Gs gs) {
		this.gs = gs;
	}
	
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	private String password;
	
	
    
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", addr=" + addr + ", sex=" + sex + ", head=" + head + ", money="
				+ money + ", gs=" + gs + ", birth=" + birth + ", password=" + password + "]";
	}
 

	

	
	

}
