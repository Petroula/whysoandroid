package pegasus.Whysosad;

import java.util.HashMap;

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
	RadioButton bet5;
	RadioButton bet10;
	RadioButton bet20;
	String betChosen = "";
	TimePicker timePicker1;
	TextView message;
	TextView choose_credits;
	
	protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.bet_layout);
    	
    	message = (TextView)findViewById(R.id.message);
    	
    	done = (Button)findViewById(R.id.done);
    	done.setOnClickListener(this);
    	back = (Button)findViewById(R.id.back);
    	back.setOnClickListener(this);
    	textView1 = (TextView)findViewById(R.id.textView1);
    	choose_credits = (TextView)findViewById(R.id.choose_credits);
    	increase = (RadioButton)findViewById(R.id.increase);
    	increase.setOnClickListener(this);
    	decrease = (RadioButton)findViewById(R.id.decrease);
    	decrease.setOnClickListener(this);
    	bet5 = (RadioButton)findViewById(R.id.bet5);
    	bet5.setOnClickListener(this);
    	bet10 = (RadioButton)findViewById(R.id.bet10);
    	bet10.setOnClickListener(this);
    	bet20 = (RadioButton)findViewById(R.id.bet20);
    	bet20.setOnClickListener(this);
    	increase.setChecked(true);
    	bet10.setChecked(true);
    	
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
			Intent changeView = getIntent();
		    betCountry = changeView.getStringExtra("country");
			betKey = changeView.getStringExtra("key");
						
		    hour = timePicker1.getCurrentHour() + "";
		    minutes =  timePicker1.getCurrentMinute() + "";
			if(Integer.parseInt(User.credits)>=Integer.parseInt(betChosen)) {
				String s = new ClientToServer().placeBet(User.username, User.password, betCountry, hour, minutes, predictLevel, betChosen);
				User.credits = ClientToServer.getCredits(User.username, User.password);
				message.setText(s);
			} else {
				message.setText("Sorry, you don't have enough credits for this bet!");
			}					
		} else if(v.getId()==R.id.back) {			
			Intent changeView = new Intent(getApplicationContext(), ResultsActivity.class);
			startActivity(changeView);
		} else if(v.getId()==R.id.increase) {
			decrease.setChecked(false);
			predictLevel = "happier";
		} else if(v.getId()==R.id.decrease) {
			increase.setChecked(false);
			predictLevel = "sadder";
		} else if(v.getId()==R.id.bet5) {
			bet10.setChecked(false);
			bet20.setChecked(false);
			betChosen = "5";
		} else if(v.getId()==R.id.bet10) {
			bet5.setChecked(false);
			bet20.setChecked(false);
			betChosen = "10";
		} else if(v.getId()==R.id.bet20) {
			bet5.setChecked(false);
			bet10.setChecked(false);
			betChosen = "20";
		}
		
	}
}