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
import android.widget.TextView;

/**
 * 
 * @author Petroula
 * 
 */

public class ResultsActivity extends Activity implements OnClickListener {

	Button refresh;
	Button map_button;
	String selectedCountry;
	String selectedKey;
	HappinessTable happytable;
	TextView credits_left2;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results);

		map_button = (Button)findViewById(R.id.map_button);
		map_button.setOnClickListener(this);
		
		credits_left2 = (TextView)findViewById(R.id.credits_left2);
		User.credits = ClientToServer.getCredits(User.username, User.password);
		credits_left2.setText("Remaining Credits:" +User.credits);
		
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
				if (Converter.countries.containsKey(countrycode)) {
					String printCountry = Converter.convert(countrycode);
					String printValue = "" + jsonObj.get(countrycode);
					happytable.addRow(printCountry, printValue,this);
				}
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
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
		case R.id.map_button:
			Intent changeV = new Intent(getApplicationContext(),
					WebActivity.class);
			startActivity(changeV);
			
		default:
			break;
		} 
	}
}