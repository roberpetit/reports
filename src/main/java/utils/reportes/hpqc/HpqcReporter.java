package utils.reportes.hpqc;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utils.reportes.hpqc.model.HpqcRow;
import utils.reportes.hpqc.model.State;
import utils.reportes.hpqc.model.User;

import com.poiji.bind.Poiji;

public class HpqcReporter {
	
	private static final String PATH_HPQC = "src/resources/11-20-2018.xlsx";
	private static final String PATH_USERS = "src/resources/Users.xlsx";
	private static final String PATH_STATES = "src/resources/States.xlsx";
	
	public static HPQCReport readFromHpqc() {
		// Leo archivos
		File incidentsFile = new File(PATH_HPQC);
		File usersFile = new File(PATH_USERS);
		File statesFile = new File(PATH_STATES);
		// lista de todos los incidentes
		List<HpqcRow> hpqcTodos = Poiji.fromExcel(incidentsFile, HpqcRow.class );
		List<User> users = Poiji.fromExcel(usersFile, User.class);
		List<State> states = Poiji.fromExcel(statesFile, State.class);
		
		List<String> teamNames = new ArrayList<String>();
		teamNames.add("dev");
		teamNames.add("functional");
		teamNames.add("testing");
		teamNames.add("ICBC");
		
				
		//Filtro los incidentes pertenecientes a r 1.5 y no son mejora
		List<HpqcRow> backlogFBD = getDetectedInCycleSubset(hpqcTodos, "Release 1.5 INTGRA");
		Date date = extractDateFromFileName(PATH_HPQC);
		//Creo bojeto de relacion teams-estados de incidentes
		StatesAndTeamsTable tableFbd15 = new StatesAndTeamsTable(backlogFBD, "Release 1.5 INTGRA",users,states, teamNames, date);
		
		HPQCReport report = new HPQCReport();
		
		List<StatesAndTeamsTable> tables = new ArrayList<StatesAndTeamsTable>();
		tables.add(tableFbd15);
		report.setTables(tables);
		return report;
	}
	
	private static List<HpqcRow> getDetectedInCycleSubset(List<HpqcRow> hpqcTodos, String cycle) {
		List<HpqcRow> backlog = new ArrayList<HpqcRow>();
		for (HpqcRow hpqcRow : hpqcTodos) {
			if (cycle.equals(hpqcRow.getDetectedInCycle()) && !"Mejora".equals(hpqcRow.getCategory())) {
				backlog.add(hpqcRow);
			}
		}
		return backlog;
	}
	
	private static Date extractDateFromFileName(String pathHpqc) {
		String pattern = "MM-dd-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String[] parts = pathHpqc.split("/");
		String dateString = parts[parts.length - 1];
		parts = dateString.split("\\.");
		dateString = parts[0];
		
		try {
			return simpleDateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
