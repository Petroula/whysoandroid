package pegasus.Whysosad;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BetResultsActivity extends Activity implements OnClickListener {

	Button refresh;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	String selectedCountry;
	String selectedKey;
	BetsTable betstable;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.betresults);

		betstable = (BetsTable) findViewById(R.id.betsTable1);
		refresh = (Button) findViewById(R.id.refresh1);
		refresh.setOnClickListener(this);
		((Button) findViewById(R.id.results_button)).setOnClickListener(this);
		

		reload();
	}
	
	void reload(){
		betstable.removeAllViews();
		String result = ClientToServer.userbets(User.username,User.password);
		OnRefresh(result);
	}

	protected void OnRefresh(String response) {

		try {
			JSONArray bets = new JSONArray(response.trim());
			/**
			 * Dynamically create table rows for displaying the results received
			 * from the server
			 */
			for (int i = 0; i < bets.length(); i++) {
				JSONObject bet = bets.getJSONObject(i);				
				String countrycode = bet.getString("country");
				int credits = bet.getInt("credits");
				double odds = bet.getDouble("odds");
				String status = bet.getString("status");
				String targetstatus = bet.getString("targetstatus");
				int targettime = bet.getInt("targettime");
				
				betstable.addRow(countrycode, status,odds,credits,targettime,this);
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
			reload();
			break;

		case R.id.results_button:
			Intent changeView = new Intent(getApplicationContext(),
					ResultsActivity.class);
			startActivity(changeView);
			break;
		
		default:
			break;
		} 
	}
}
