package utils.reportes.hpqc.model;

import java.util.Date;
import java.util.List;

public class GraphData {
	private List<Integer> totalesValues;
	private List<Integer> fbdValues;
	private List<Integer> qaValues;
	private List<Date> datesValues;
	public List<Integer> getTotalesValues() {
		return totalesValues;
	}
	public void setTotalesValues(List<Integer> totalesValues) {
		this.totalesValues = totalesValues;
	}
	public List<Integer> getFbdValues() {
		return fbdValues;
	}
	public void setFbdValues(List<Integer> fbdValues) {
		this.fbdValues = fbdValues;
	}
	public List<Integer> getQaValues() {
		return qaValues;
	}
	public void setQaValues(List<Integer> qaValues) {
		this.qaValues = qaValues;
	}
	public List<Date> getDatesValues() {
		return datesValues;
	}
	public void setDatesValues(List<Date> datesValues) {
		this.datesValues = datesValues;
	}
}
