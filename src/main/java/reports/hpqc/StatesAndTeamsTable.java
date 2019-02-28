package reports.hpqc;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import reports.hpqc.model.HPQCState;
import reports.hpqc.model.HPQCTeamColumn;
import reports.hpqc.model.HPQCUser;
import reports.hpqc.model.HpqcRow;

public class StatesAndTeamsTable {
	private String title;
	private Integer total;
	private Date date;
	
	private List<HPQCTeamColumn> teamColumns;
	
	public StatesAndTeamsTable(List<HpqcRow> backlog, String title, List<HPQCUser> users, List<HPQCState> states, List<String> teams, Date date) {
		this.title = title;
		this.total = 0;
		this.date = date;
		this.teamColumns = new ArrayList<HPQCTeamColumn>();
		for (String teamName : teams) {
			this.teamColumns.add(generateColumn(backlog, users, states, teamName));
		}
	}
	
	private HPQCTeamColumn generateColumn(List<HpqcRow> backlog, List<HPQCUser> users,
			List<HPQCState> states, String teamName) {
		HPQCTeamColumn col = new HPQCTeamColumn();
		col.setTitle(teamName);
		Map<String,Long> statesMap = new HashMap<String, Long>();
		
		// Pongo cada estado como un nuevo row
		for (HPQCState state : states) {
			statesMap.put(state.getCode(),0L);
		}
		statesMap.put("Total", 0L);
		
		for (HpqcRow hpqcRow : backlog) {
			if(users.contains(new HPQCUser(hpqcRow.getUser(), teamName)) && states.contains(new HPQCState(hpqcRow.getStatus()))) {
				statesMap.put(hpqcRow.getStatus(), statesMap.get(hpqcRow.getStatus())+1L);
				statesMap.put("Total", statesMap.get("Total")+1L);
				this.total ++;
			}
		}
		col.setState(statesMap);
		return col;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		String teamString = "";
		for (HPQCTeamColumn col : teamColumns) {
			teamString = teamString.concat(col.getTitle() + col.getState().toString() + "; ");
		}
		return "StatesAndTeamsTable [title=" + title + ", date=" + date.toString() + ", total=" + total + ", teams=" + teamString + "]";
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<HPQCTeamColumn> getTeamColumns() {
		return teamColumns;
	}

	public void setTeamColumns(List<HPQCTeamColumn> teamColumns) {
		this.teamColumns = teamColumns;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}