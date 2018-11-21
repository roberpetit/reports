package utils.reportes;

import utils.reportes.hpqc.HPQCReport;
import utils.reportes.hpqc.HpqcReporter;
import utils.reportes.hpqc.StatesAndTeamsTable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	HPQCReport hpqcReport = HpqcReporter.readFromHpqc();
    	
    	ObjectMapper mapper = new ObjectMapper();

    	try {
			String jsonInString = mapper.writeValueAsString(hpqcReport);
			System.out.println(jsonInString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    }
}
