package utils.reportes;

import utils.reportes.hpqc.HPQCReport;
import utils.reportes.hpqc.HpqcAllTimesReporter;
import utils.reportes.hpqc.HpqcDailyReporter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
    	
    	System.out.println("Reporte diario:");
    	generateDailyReport();

    	System.out.println("Gráfico de totales:");
    	HpqcAllTimesReporter.getTotalsGraphData();
    	
    	System.out.println("Gráfico de totales por equipo:");
    	HpqcAllTimesReporter.getDevGraphDataFromTotalBacklog();
    	
    	System.out.println("Gráfico de R1.5 por equipo:");
    	HpqcAllTimesReporter.getDevGraphDataFromR15();

    }
    
    public static void generateDailyReport() {
		HPQCReport hpqcReport = HpqcDailyReporter.readFromHpqc();
    	ObjectMapper mapper = new ObjectMapper();
    	try {
			String jsonInString = mapper.writeValueAsString(hpqcReport);
			System.out.println(jsonInString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    }
    
}
