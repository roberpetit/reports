package utils.reportes.hpqc;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utils.reportes.hpqc.model.GraphData;
import utils.reportes.hpqc.model.State;
import utils.reportes.hpqc.model.User;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poiji.bind.Poiji;

public class HpqcAllTimesReporter {
	
	private static final String PATH_USERS = "src/resources/Users.xlsx";
	private static final String PATH_STATES = "src/resources/States.xlsx";
	private static final String PATH_FILES = "src/resources/bajadas";
	
	private static List<String> readFilesNames() {
		final File folder = new File(PATH_FILES);
		List<String> fileNames = new ArrayList<String>();
	    for (final File fileEntry : folder.listFiles()) {
            fileNames.add(PATH_FILES + "/" + fileEntry.getName());
	    }
	    return fileNames;
	}

	public static List<HPQCReport> getAllReports() {
			File usersFile = new File(PATH_USERS);
			File statesFile = new File(PATH_STATES);
			List<User> users = Poiji.fromExcel(usersFile, User.class);
			List<State> states = Poiji.fromExcel(statesFile, State.class);
			
			List<String> fileNames = readFilesNames();
			List<HPQCReport> reports = new ArrayList<HPQCReport>();
			
			for (String fileName : fileNames) {
				HPQCReport rep = HpqcDailyReporter.getReport(users, states, fileName);
				reports.add(rep);
			}
			return reports;
	}

	public static GraphData getTotalsGraphData() {
		List<HPQCReport> reports = getAllReports();
		List<Integer> totalesValues = new ArrayList<Integer>();
		List<Integer> fbdValues = new ArrayList<Integer>();
		List<Integer> qaValues = new ArrayList<Integer>();
		List<Date> datesValues = new ArrayList<Date>();
		GraphData graph = new GraphData();
		
		for (HPQCReport rep : reports) {
			totalesValues.add(rep.getTables().get(0).getTotal());
			fbdValues.add(rep.getTables().get(1).getTotal());
			qaValues.add(rep.getTables().get(2).getTotal());

			datesValues.add(rep.getTables().get(0).getDate());
			
			graph.setDatesValues(datesValues);
			graph.setFbdValues(fbdValues);
			graph.setQaValues(qaValues);
			graph.setTotalesValues(totalesValues);
		}
		printAsJSON(graph);
		return graph;
	}


	public static GraphData getDevGraphDataFromTotalBacklog() {
		List<HPQCReport> reports = getAllReports();
		List<Integer> dev = new ArrayList<Integer>();
		List<Integer> functional = new ArrayList<Integer>();
		List<Integer> testing = new ArrayList<Integer>();
		List<Date> datesValues = new ArrayList<Date>();
		GraphData graph = new GraphData();
		
		for (HPQCReport rep : reports) {
			printAsJSON(rep);
			dev.add(Integer.valueOf(rep.getTables().get(0).getTeamColumns().get(0).getState().get("Total").toString()));
			functional.add(Integer.valueOf(rep.getTables().get(0).getTeamColumns().get(1).getState().get("Total").toString()));
			testing.add(Integer.valueOf(rep.getTables().get(0).getTeamColumns().get(2).getState().get("Total").toString()));

			datesValues.add(rep.getTables().get(0).getDate());
			
			graph.setDatesValues(datesValues);
			graph.setFbdValues(dev);
			graph.setQaValues(functional);
			graph.setTotalesValues(testing);
		}
		printAsJSON(graph);
		return graph;
	}
	public static GraphData getDevGraphDataFromR15() {
		List<HPQCReport> reports = getAllReports();
		List<Integer> dev = new ArrayList<Integer>();
		List<Integer> functional = new ArrayList<Integer>();
		List<Integer> testing = new ArrayList<Integer>();
		List<Date> datesValues = new ArrayList<Date>();
		GraphData graph = new GraphData();
		
		for (HPQCReport rep : reports) {
			printAsJSON(rep);
			dev.add(Integer.valueOf(rep.getTables().get(1).getTeamColumns().get(0).getState().get("Total").toString()));
			functional.add(Integer.valueOf(rep.getTables().get(1).getTeamColumns().get(1).getState().get("Total").toString()));
			testing.add(Integer.valueOf(rep.getTables().get(1).getTeamColumns().get(2).getState().get("Total").toString()));

			datesValues.add(rep.getTables().get(0).getDate());
			
			graph.setDatesValues(datesValues);
			graph.setFbdValues(dev);
			graph.setQaValues(functional);
			graph.setTotalesValues(testing);
		}
		printAsJSON(graph);
		return graph;
	}
	/**
	 * @param rep
	 */
	public static void printAsJSON(Object rep) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonInString = mapper.writeValueAsString(rep);
			System.out.println(jsonInString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
