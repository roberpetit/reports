package reports.hpqc;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poiji.bind.Poiji;

import reports.hpqc.model.HPQCGraphData;
import reports.hpqc.model.HPQCState;
import reports.hpqc.model.HPQCUser;

public class HpqcAllTimesReporter {
	
	private static final String PATH_USERS = "src/main/resources/reports/Users.xlsx";
	private static final String PATH_STATES = "src/main/resources/reports/States.xlsx";
	private static final String PATH_FILES = "src/main/resources/reports/bajadas";
	
	public static List<String> readFilesNames() {
		final File folder = new File(PATH_FILES);
		List<String> fileNames = new ArrayList<String>();
	    for (final File fileEntry : folder.listFiles()) {
            fileNames.add(PATH_FILES + "/" + fileEntry.getName());
	    }
	    return orderByDate(fileNames);
	}

	private static List<String> orderByDate(List<String> datestring) {

	    Collections.sort(datestring, (s1, s2) -> getDateFromString(s1).
	            compareTo(getDateFromString(s2)));
	    return datestring;
	}

	private static Date getDateFromString(String s2) {
	    SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");

		try {
			return sm.parse(s2.substring(s2.lastIndexOf('/') + 1, s2.lastIndexOf('.')));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<HPQCReport> getAllReports() {
			File usersFile = new File(PATH_USERS);
			File statesFile = new File(PATH_STATES);
			List<HPQCUser> users = Poiji.fromExcel(usersFile, HPQCUser.class);
			List<HPQCState> states = Poiji.fromExcel(statesFile, HPQCState.class);
			
			List<String> fileNames = readFilesNames();
			List<HPQCReport> reports = new ArrayList<HPQCReport>();
			
			for (String fileName : fileNames) {
				HPQCReport rep = HpqcDailyReporter.getReport(users, states, fileName);
				reports.add(rep);
			}
			return reports;
	}

	public static String getTotalsGraphData() {
		List<HPQCReport> reports = getAllReports();
		return getTotalGraphDataFromReports(reports);
	}

	/**
	 * @param reports
	 * @return
	 */
	public static String getTotalGraphDataFromReports(List<HPQCReport> reports) {
		List<Integer> totalesValues = new ArrayList<Integer>();
		List<Integer> cobrosValues = new ArrayList<Integer>();
		List<Integer> pagosValues = new ArrayList<Integer>();
		List<Integer> finaValues = new ArrayList<Integer>();
		List<Integer> otrosValues = new ArrayList<Integer>();
		List<Date> datesValues = new ArrayList<Date>();
		HPQCGraphData graph = new HPQCGraphData();
		
		for (HPQCReport rep : reports) {
			totalesValues.add(rep.getTables().get(0).getTotal());
			pagosValues.add(rep.getTables().get(1).getTotal());
			cobrosValues.add(rep.getTables().get(2).getTotal());
			finaValues.add(rep.getTables().get(3).getTotal());
			otrosValues.add(rep.getTables().get(4).getTotal());
			datesValues.add(rep.getTables().get(0).getDate());
			
			graph.setFirstValues(totalesValues);
			graph.setSecondValues(cobrosValues);
			graph.setThirdValues(pagosValues);
			graph.setDatesValues(datesValues);
		}
		return printAsJSON(graph);
	}


	public static String getDevGraphDataFromTotalBacklog() {
		List<HPQCReport> reports = getAllReports();
		return getDevGraphDataFromReports(reports);
	}

	/**
	 * @param reports
	 * @return
	 */
	public static String getDevGraphDataFromReports(List<HPQCReport> reports) {
		List<Integer> dev = new ArrayList<Integer>();
		List<Integer> icbc = new ArrayList<Integer>();
		List<Integer> testing = new ArrayList<Integer>();
		List<Date> datesValues = new ArrayList<Date>();
		HPQCGraphData graph = new HPQCGraphData();
		
		for (HPQCReport rep : reports) {
			dev.add(Integer.valueOf(rep.getTables().get(0).getTeamColumns().get(0).getState().get("Total").toString()));
			icbc.add(Integer.valueOf(rep.getTables().get(0).getTeamColumns().get(1).getState().get("Total").toString()));
			testing.add(Integer.valueOf(rep.getTables().get(0).getTeamColumns().get(2).getState().get("Total").toString()));

			datesValues.add(rep.getTables().get(0).getDate());
			
			graph.setDatesValues(datesValues);
			graph.setFirstValues(dev);
			graph.setSecondValues(testing);
			graph.setThirdValues(icbc);
		}
		return printAsJSON(graph);
	}
	public static String getDevGraphDataFromR15() {
		List<HPQCReport> reports = getAllReports();
		return getDevGraphFromR15FromReports(reports);
	}

	/**
	 * Data para el grafico de release 1.5 INTGRA
	 * @param reports
	 * @return
	 */
	public static String getDevGraphFromR15FromReports(List<HPQCReport> reports) {
		List<Integer> dev = new ArrayList<Integer>();
		List<Integer> functional = new ArrayList<Integer>();
		List<Integer> testing = new ArrayList<Integer>();
		List<Date> datesValues = new ArrayList<Date>();
		HPQCGraphData graph = new HPQCGraphData();
		
		for (HPQCReport rep : reports) {
			dev.add(Integer.valueOf(rep.getTables().get(1).getTeamColumns().get(0).getState().get("Total").toString()));
			functional.add(Integer.valueOf(rep.getTables().get(1).getTeamColumns().get(1).getState().get("Total").toString()));
			testing.add(Integer.valueOf(rep.getTables().get(1).getTeamColumns().get(2).getState().get("Total").toString()));

			datesValues.add(rep.getTables().get(0).getDate());
			
			graph.setDatesValues(datesValues);
			graph.setFirstValues(dev);
			graph.setSecondValues(testing);
			graph.setThirdValues(functional);
		}
		return printAsJSON(graph);
	}
	
	/**
	 * Data para el grafico de release 1.5 Regresion
	 * @param reports
	 * @return
	 */
	public static String getDevGraphFromR15RegresionFromReports(List<HPQCReport> reports) {
		List<Integer> dev = new ArrayList<Integer>();
		List<Integer> icbc = new ArrayList<Integer>();
		List<Integer> testing = new ArrayList<Integer>();
		List<Date> datesValues = new ArrayList<Date>();
		HPQCGraphData graph = new HPQCGraphData();
		
		for (HPQCReport rep : reports) {
			dev.add(Integer.valueOf(rep.getTables().get(2).getTeamColumns().get(0).getState().get("Total").toString()));
			icbc.add(Integer.valueOf(rep.getTables().get(2).getTeamColumns().get(1).getState().get("Total").toString()));
			testing.add(Integer.valueOf(rep.getTables().get(2).getTeamColumns().get(2).getState().get("Total").toString()));

			datesValues.add(rep.getTables().get(0).getDate());
			
			graph.setDatesValues(datesValues);
			graph.setSecondValues(dev);
			graph.setThirdValues(icbc);
			graph.setFirstValues(testing);
		}
		return printAsJSON(graph);
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

	public static String getCreatedTotals() {
		List<HPQCReport> reports = getAllReports();
		return getCreatedTotalsFromReports(reports);
	}

	/**
	 * @param reports
	 * @return
	 */
	public static String getCreatedTotalsFromReports(List<HPQCReport> reports) {
		List<Integer> newS = new ArrayList<Integer>();
		List<Integer> open = new ArrayList<Integer>();
		List<Integer> reopen = new ArrayList<Integer>();

		List<Date> datesValues = new ArrayList<Date>();
		HPQCGraphData graph = new HPQCGraphData();
		
		for (HPQCReport rep : reports) {
			newS.add(getTotalOfColumn(rep, "New") );

			open.add(getTotalOfColumn(rep,"Open"));

			reopen.add(getTotalOfColumn(rep,"Reopen"));
			
			datesValues.add(rep.getTables().get(0).getDate());
			
			graph.setDatesValues(datesValues);
			graph.setSecondValues(newS);
			graph.setThirdValues(open);
			graph.setFirstValues(reopen);
		}
		return printAsJSON(graph);
	}

	private static Integer getTotalOfColumn(HPQCReport rep, String col) {
		Integer sum = Integer.valueOf(rep.getTables().get(0).getTeamColumns().get(0).getState().get(col).toString())
				+ Integer.valueOf(rep.getTables().get(0).getTeamColumns().get(1).getState().get(col).toString())
				+ Integer.valueOf(rep.getTables().get(0).getTeamColumns().get(2).getState().get(col).toString());
		return sum;
	}
	
}
