package mycs.en;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="gongsi")
public class Gs {
	
	
	private int sid;
	private String yuangong;
	
	private Set<User> user;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getYuangong() {
		return yuangong;
	}
	
	public void setYuangong(String yuangong) {
		this.yuangong = yuangong;
	}
	
	@OneToMany(mappedBy = "gs",fetch=FetchType.EAGER) 
	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Gs{" +
				"sid=" + sid +
				", yuangong='" + yuangong + '\'' +
				", user=" + user +
				'}';
	}
}
