package taxCalculator;

import java.util.*;

/** This class uses a hash map to store the average state income tax rate for each state
 * in the United States. This hash map is then used to determine how much a filer can deduct
 * from their taxable income based upon the amount of money they earn and the state they reside in.
 * @author Michael Kokkines and Harry Chen
 *
 */
public class stateIncomeTax {
	private static Map<String, Double> stateTaxRates = new HashMap<>();
	
	/** Creates the hash map described above**/
	public static void setMap() {
		stateTaxRates.put("Alaska", .0564);
		stateTaxRates.put("Delaware", .0670);
		stateTaxRates.put("Montana", .0689);
		stateTaxRates.put("Wyoming", .0743);
		stateTaxRates.put("Nevada", .0766);
		stateTaxRates.put("Tenenssee", .0797);
		stateTaxRates.put("Idaho", .0848);
		stateTaxRates.put("California", .0879);
		stateTaxRates.put("South Carolina", .0884);
		stateTaxRates.put("Florida", .0894);
		stateTaxRates.put("Oregon", .0922);
		stateTaxRates.put("Utah", .0925);
		stateTaxRates.put("Colorado", .0934);
		stateTaxRates.put("Alabama", .0943);
		stateTaxRates.put("Arizona", .0960);
		stateTaxRates.put("South Dakota", .0977);
		stateTaxRates.put("Washington DC", .10);
		stateTaxRates.put("North Dakota", .1003);
		stateTaxRates.put("New Hampshire", .1009);
		stateTaxRates.put("Louisiana", .1033);
		stateTaxRates.put("Hawaii", .1033);
		stateTaxRates.put("West Virginia", .1038);
		stateTaxRates.put("Georgia", .1057);
		stateTaxRates.put("North Carolina", .1063);
		stateTaxRates.put("New Mexico", .1073);
		stateTaxRates.put("Oklahoma", .1070);
		stateTaxRates.put("Virginia", .1089);
		stateTaxRates.put("Vermont", .1089);
		stateTaxRates.put("Missouri", .1102);
		stateTaxRates.put("Texas", .1112);
		stateTaxRates.put("Massachusetts", .1152);
		stateTaxRates.put("Minnesota", .1159);
		stateTaxRates.put("Maine", .1163);
		stateTaxRates.put("Washington", .1172);
		stateTaxRates.put("Indiana", .1187);
		stateTaxRates.put("Maryland", .1192);
		stateTaxRates.put("Kentucky", .1201);
		stateTaxRates.put("Mississippi", .1214);
		stateTaxRates.put("Kansas", .1228);
		stateTaxRates.put("Arkansas", .1228);
		stateTaxRates.put("Pennsylvania", .1233);
		stateTaxRates.put("New Jersey", .1263);
		stateTaxRates.put("Iowa", .1284);
		stateTaxRates.put("Michigan", .13);
		stateTaxRates.put("Ohio", .1306);
		stateTaxRates.put("Connecticut", .1356);
		stateTaxRates.put("Rhode Island", .1357);
		stateTaxRates.put("New York", .1358);
		stateTaxRates.put("Wisconsin", .1360);
		stateTaxRates.put("Nebraska", .1380);
		stateTaxRates.put("Illinois", .1476);	
	}
	
	/** Determines an the amount of money a filer can deduct from their taxes due to 
	 * the state income taxes they pay.
	 * @return the magnitude of the state deduction for a given filer. 
	 */
	public static double stateTaxDeduction(String state, double income) {
		setMap();
		return income * stateTaxRates.get(state);
	}

}
