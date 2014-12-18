package pegasus.Whysosad;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 * @author Petroula
 * 
 */

public class ResultsActivity extends Activity implements OnClickListener {

	Button refresh;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	HashMap<String, String> countries = new HashMap();
	String selectedCountry;
	String selectedKey;
	HappinessTable happytable;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results);
		createHashMap();

		happytable = (HappinessTable) findViewById(R.id.happinessTable);
		((Button)findViewById(R.id.refresh)).setOnClickListener(this);
		((Button)findViewById(R.id.bets_button)).setOnClickListener(this);;		
		

		String result = ClientToServer.currentHappiness();
		OnRefresh(result);
	}

	protected void OnRefresh(String response) {

		try {
			JSONObject jsonObj = new JSONObject(response.trim());
			Iterator<?> countrycodes = jsonObj.keys();

			/**
			 * Dynamically create table rows for displaying the results received
			 * from the server
			 */
			while (countrycodes.hasNext()) {
				String countrycode = (String) countrycodes.next();
				if (countries.containsKey(countrycode)) {
					String printCountry = countries.get(countrycode);
					String printValue = "" + jsonObj.get(countrycode);
					happytable.addRow(printCountry, printValue,this);
				}
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void createHashMap() {
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
	}

	/** Refreshes the results from the server */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.refresh:
			happytable.removeAllViews();
			String Result = ClientToServer.currentHappiness();
			OnRefresh(Result);
			break;
		case R.id.bets_button:
			Intent changeView = new Intent(getApplicationContext(),
					BetResultsActivity.class);
			startActivity(changeView);
			break;
			
		default:
			break;
		} 
	}
}