import reports.hpqc.HpqcReportManager;

public class app {
	
	public static void main(String[] args) { 
		String frontDataOrdered = HpqcReportManager.getReport();
		System.out.println(frontDataOrdered);
	}
		/**
		 * 
		frontDataOrdered+="public initTableData: any =  ";
		frontDataOrdered+=HpqcDailyReporter.readFromHpqc();
		frontDataOrdered+="\n";
		
		frontDataOrdered+="public initGraphServiceDataTotales: InitGraphData = ";
		frontDataOrdered+=HpqcAllTimesReporter.getTotalsGraphData();
		frontDataOrdered+="\n";
		frontDataOrdered+="public initGraphServiceDataTotalesPorEquipo: InitGraphData = ";
		frontDataOrdered+=HpqcAllTimesReporter.getDevGraphDataFromTotalBacklog();
		frontDataOrdered+="\n";
		frontDataOrdered+="public initGraphServiceDataR15PorEquipo: InitGraphData = ";
		frontDataOrdered+=HpqcAllTimesReporter.getDevGraphDataFromR15();
		frontDataOrdered+="\n";
		frontDataOrdered+="public initGraphServiceDataCreatedTotals: InitGraphData = ";
		frontDataOrdered+=HpqcAllTimesReporter.getCreatedTotals();
		System.out.println(frontDataOrdered);
	}
	 */

}
