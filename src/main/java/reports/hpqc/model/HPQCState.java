package reports.hpqc.model;

import com.poiji.annotation.ExcelCellName;

public class HPQCState {
	@ExcelCellName("name")
	private String name;
	@ExcelCellName("code")
	private String code;
	public HPQCState(String status) {
		this.code = status;
	}
	public HPQCState() {
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
		HPQCState other = (HPQCState) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.toLowerCase().equals(other.code.toLowerCase()))
			return false;
		return true;
	}
}
