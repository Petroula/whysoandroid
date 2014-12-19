package pegasus.Whysosad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * 
 * @author Petroula
 * 
 */

public class BetActivity extends Activity implements OnClickListener {
	
	String betCountry;
	String betKey;
	String predictLevel;
	String hour;
	String minutes;
	Button done;
	Button back;
	TextView textView1;
	CheckBox day;
	CheckBox night;
	RadioButton increase;
	RadioButton decrease;
	TimePicker timePicker1;
	TextView message;
	
	
	protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.bet_layout);
    	
    	message = (TextView)findViewById(R.id.message);
    	
    	done = (Button)findViewById(R.id.done);
    	done.setOnClickListener(this);
    	back = (Button)findViewById(R.id.back);
    	back.setOnClickListener(this);
    	textView1 = (TextView)findViewById(R.id.textView1);
    	increase = (RadioButton)findViewById(R.id.increase);
    	increase.setOnClickListener(this);
    	decrease = (RadioButton)findViewById(R.id.decrease);
    	decrease.setOnClickListener(this);
    	
    	timePicker1 = (TimePicker)findViewById(R.id.timePicker1);
	
    	Intent changeView = getIntent();
    	betCountry = changeView.getStringExtra("country");
    	betKey = changeView.getStringExtra("key");
    	
    	textView1.setText("You chose to place your bet on " + betCountry + ". " + betCountry + "'s " + "current state equals " +
    	betKey + ". " + "You can bet on whether the current level is going to increase or decrease at one of the time frames provided below:");
	
	}

	
		
	@Override
	public void onClick(View v) {
		
		if(v.getId()==R.id.done) {
			User.credits = ClientToServer.getCredits(User.username, User.password);
//			if(Integer.parseInt(chooseCr.getText().toString()) > Integer.parseInt(User.credits)) {
//				message.setText("Not enough credits in your account for this bet!");
//			 } else {
				 Intent changeView = getIntent();
				 betCountry = changeView.getStringExtra("country");
				 betKey = changeView.getStringExtra("key");
						
				hour = timePicker1.getCurrentHour() + "";
				minutes =  timePicker1.getCurrentMinute() + "";
						
//				String s = new ClientToServer().placeBet(User.username, User.password, betCountry, hour, minutes, predictLevel, chooseCr.getText().toString());
						
				User.credits = ClientToServer.getCredits(User.username, User.password);
//				message.setText(s);
//			 }		
		} else if(v.getId()==R.id.back) {			
			Intent changeView = new Intent(getApplicationContext(), ResultsActivity.class);
			startActivity(changeView);
		} else if(v.getId()==R.id.increase) {
			decrease.setChecked(false);
			predictLevel = "happier";
		} else if(v.getId()==R.id.decrease) {
			increase.setChecked(false);
			predictLevel = "sadder";
		} 
		
	}
}