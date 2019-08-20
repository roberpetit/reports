package reports.hpqc.model;

import java.util.Date;
import java.util.List;

public class HPQCGraphData {
	private List<Integer> firstValues;
	private List<Integer> secondValues;
	private List<Integer> thirdValues;
	private List<Date> datesValues;
	public List<Integer> getFirstValues() {
		return firstValues;
	}
	public void setFirstValues(List<Integer> totalesValues) {
		this.firstValues = totalesValues;
	}
	public List<Integer> getSecondValues() {
		return secondValues;
	}
	public void setSecondValues(List<Integer> fbdValues) {
		this.secondValues = fbdValues;
	}
	public List<Integer> getThirdValues() {
		return thirdValues;
	}
	public void setThirdValues(List<Integer> qaValues) {
		this.thirdValues = qaValues;
	}
	public List<Date> getDatesValues() {
		return datesValues;
	}
	public void setDatesValues(List<Date> datesValues) {
		this.datesValues = datesValues;
	}
}
