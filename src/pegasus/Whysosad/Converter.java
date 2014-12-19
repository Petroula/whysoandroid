package pegasus.Whysosad;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Converter {


	public static HashMap<String, String>  countries =  createHashMap();
	
	
	public static String convert(String countrycode){
		return countries.get(countrycode);
	}
	
	public static String getcountrycode(String countryname){

		Iterator<Entry<String, String>> iterate = countries.entrySet().iterator();
		
	    while (iterate.hasNext()) {
	        Map.Entry pairs = (Map.Entry)iterate.next();
	        if(pairs.getValue().equals(countryname)) {
	        	return (String) pairs.getKey();
	        }
	        iterate.remove();
	    }
	    
		return null;
	}
	
	private static HashMap<String, String> createHashMap() {
		HashMap<String, String> countries = new HashMap<String, String>();
		countries.put("TR", "Turkey");
		countries.put("BR", "Brazil");
		countries.put("US", "USA");
		countries.put("ID", "Indonesia");
		countries.put("AR", "Argentina");
		countries.put("PH", "Philippines");
		countries.put("SE", "Sweden");
		countries.put("NO", "Norway");
		countries.put("MX", "Mexico");
		countries.put("VE", "Venezuela");
		countries.put("GT", "Guatemala");
		countries.put("RU", "Russia");
		countries.put("CO", "Colombia");
		countries.put("IN", "India");
		countries.put("GB", "Great Britain");
		return countries;
	}
}
