package com.example.whysosad;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * 
 * @author Petroula
 * 
 */

public class ResultsActivity extends Activity implements OnClickListener {

	Button refresh;
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
					happytable.addRow(printCountry, printValue);
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