package utils.reportes.hpqc.model;

import com.poiji.annotation.ExcelCellName;

public class User {
	@ExcelCellName("name")
	private String name;
	@ExcelCellName("code")
	private String code;
	@ExcelCellName("team")
	private String team;
	public User(String user, String team) {
		this.code = user;
		this.team = team;
	}
	public User() {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (code == null) {
			return false;
		} 
		if (other.code == null) {
			return false;
		} 
		if (team == null) {
			return false;
		} 
		if (other.team == null) {
			return false;
		} 
		if (!code.toLowerCase().equals(other.code.toLowerCase())) {
			return false;
		} 
		if (!team.toLowerCase().equals(other.team.toLowerCase())) {
			return false;
		}
		return true;
	}
}
