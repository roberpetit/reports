package utils.reportes.hpqc;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.reportes.hpqc.model.HpqcRow;
import utils.reportes.hpqc.model.State;
import utils.reportes.hpqc.model.TeamColumn;
import utils.reportes.hpqc.model.User;

public class StatesAndTeamsTable {
	private String title;
	private Integer total;
	private Date date;
	
	private List<TeamColumn> teamColumns;
	
	public StatesAndTeamsTable(List<HpqcRow> backlog, String title, List<User> users, List<State> states, List<String> teams, Date date) {
		this.title = title;
		this.total = 0;
		this.date = date;
		this.teamColumns = new ArrayList<TeamColumn>();
		for (String teamName : teams) {
			this.teamColumns.add(generateColumn(backlog, users, states, teamName));
		}
	}
	
	private TeamColumn generateColumn(List<HpqcRow> backlog, List<User> users,
			List<State> states, String teamName) {
		TeamColumn col = new TeamColumn();
		col.setTitle(teamName);
		Map<String,Long> statesMap = new HashMap<String, Long>();
		
		// Pongo cada estado como un nuevo row
		for (State state : states) {
			statesMap.put(state.getCode(),0L);
		}
		statesMap.put("Total", 0L);
		
		for (HpqcRow hpqcRow : backlog) {
			if(users.contains(new User(hpqcRow.getUser(), teamName)) && states.contains(new State(hpqcRow.getStatus()))) {
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
		for (TeamColumn col : teamColumns) {
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

	public List<TeamColumn> getTeamColumns() {
		return teamColumns;
	}

	public void setTeamColumns(List<TeamColumn> teamColumns) {
		this.teamColumns = teamColumns;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}