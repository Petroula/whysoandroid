package com.example.whysosad;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
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
		refresh = (Button) findViewById(R.id.refresh);
		refresh.setOnClickListener(this);

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
		if (v.getId() == R.id.refresh) {
			happytable.removeAllViews();
			String Result = ClientToServer.currentHappiness();
			OnRefresh(Result);
		}
	}
}