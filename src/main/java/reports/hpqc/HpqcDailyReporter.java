package reports.hpqc;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poiji.bind.Poiji;

import reports.hpqc.model.HPQCState;
import reports.hpqc.model.HPQCUser;
import reports.hpqc.model.HpqcRow;

public class HpqcDailyReporter {
	
	public static final String PATH_HPQC = "src/main/resources/reports/2019-08-14.xlsx";
	public static final String PATH_USERS = "src/main/resources/reports/Users.xlsx";
	public static final String PATH_STATES = "src/main/resources/reports/States.xlsx";
	
	public static String readFromHpqc() {
		
		File usersFile = new File(PATH_USERS);
		File statesFile = new File(PATH_STATES);
		List<HPQCUser> users = Poiji.fromExcel(usersFile, HPQCUser.class);
		List<HPQCState> states = Poiji.fromExcel(statesFile, HPQCState.class);
		
		return getDailyReport(users, states, PATH_HPQC);
	}


	/**
	 * @param users
	 * @param states
	 * @return
	 */
	public static String getDailyReport(List<HPQCUser> users, List<HPQCState> states, String path) {
		HPQCReport report = getReport(users, states, path);
		return printAsJSON(report);
	}

	
	/**
	 * @param users
	 * @param states
	 * @return
	 */
	public static HPQCReport getReport(List<HPQCUser> users, List<HPQCState> states, String path) {
		File incidentsFile = new File(path);
		// lista de todos los incidentes
		List<HpqcRow> hpqcTodos = Poiji.fromExcel(incidentsFile, HpqcRow.class );
		
		List<String> teamNames = new ArrayList<String>();
		teamNames.add("Desarrollo");
		teamNames.add("Testing");
		teamNames.add("ICBC");
		Date date = extractDateFromFileName(path);
		
		//Tabla de incidentes totales
		//Creo bojeto de relacion teams-estados de incidentes
		StatesAndTeamsTable tableTotales = new StatesAndTeamsTable(hpqcTodos, "Backlog Total",users,states, teamNames, date);
				
		//Filtro los incidentes pertenecientes a R2 Comex Pagos
		List<HpqcRow> backlogTabla1 = getDetectedInCycleSubset(hpqcTodos, "R2 Comex Pagos");
		StatesAndTeamsTable table1 = new StatesAndTeamsTable(backlogTabla1, "R2 Comex Pagos",users,states, teamNames, date);

		//Filtro los incidentes pertenecientes a R2 Comex Cobros
		List<HpqcRow> backlogTabla2 = getDetectedInCycleSubset(hpqcTodos, "R2 Comex Cobros");
		StatesAndTeamsTable table2 = new StatesAndTeamsTable(backlogTabla2, "R2 Comex Cobros",users,states, teamNames, date);

		//Filtro los incidentes pertenecientes a R2 Comex Fina
		List<HpqcRow> backlogTabla3 = getDetectedInCycleSubset(hpqcTodos, "R2 Comex Fina");
		StatesAndTeamsTable table3 = new StatesAndTeamsTable(backlogTabla3, "R2 Comex Financiaciones",users,states, teamNames, date);

		//Filtro los incidentes pertenecientes a R2 Comex Pagos
		//Pruebas, R2 Comex Delta, M, General, Permisos
		List<HpqcRow> backlogTabla4 = hpqcTodos.stream()
				.filter(c -> !backlogTabla1.contains(c) && !backlogTabla2.contains(c) && !backlogTabla3.contains(c))
				.collect(Collectors.toList());
		StatesAndTeamsTable table4 = new StatesAndTeamsTable(backlogTabla4, "R2 Delta/General/Mobile/Permisos/Pruebas QA",users,states, teamNames, date);

		
		HPQCReport report = new HPQCReport();
		List<StatesAndTeamsTable> tables = new ArrayList<StatesAndTeamsTable>();
		tables.add(tableTotales);
		tables.add(table1);
		tables.add(table2);
		tables.add(table3);
		tables.add(table4);

		report.setTables(tables);
		return report;
	}
	
	
	
	private static List<HpqcRow> getDetectedInCycleSubset(List<HpqcRow> hpqcTodos, String cycle) {
		List<HpqcRow> backlog = new ArrayList<HpqcRow>();
		for (HpqcRow hpqcRow : hpqcTodos) {
			
			if (StringUtils.contains(hpqcRow.getDetectedInCycle(), cycle) && !"Mejora".equals(hpqcRow.getCategory())) {
				backlog.add(hpqcRow);
			}
		}
		return backlog;
	}
	
	private static Date extractDateFromFileName(String pathHpqc) {
		String pattern = "yyyy-MM-dd";
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
	
	/**
	 * @param rep
	 */
	public static String printAsJSON(Object rep) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(rep);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
