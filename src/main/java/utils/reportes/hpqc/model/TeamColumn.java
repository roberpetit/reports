package utils.reportes.hpqc.model;

import java.util.Map;

public class TeamColumn {
	private String title;
	private Map<String,Long> state;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Map<String, Long> getState() {
		return state;
	}
	public void setState(Map<String, Long> state) {
		this.state = state;
	}
}
