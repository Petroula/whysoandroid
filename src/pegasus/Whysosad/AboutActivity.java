package pegasus.Whysosad;
import pegasus.Whysosad.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends Activity implements OnClickListener {
	
	Button back;
	
	protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.about_page);
    	
    	back = (Button)findViewById(R.id.back);
    	back.setOnClickListener(this);
    	
    	String totalUsers = ClientToServer.totalUsers();
    	TextView aboutApp = (TextView)findViewById(R.id.aboutApp);
    	aboutApp.setText("This application displays the current level of happiness regarding a list of countries."
    			+ "Included in the main functionality of the application is the possibility to bet a certain number of "
    			+ "credits on a country's score.The user has to register/login as soon as the application starts and is "
    			+ "allowed to go back and forth between most of the pages.Currently we have a total of " + totalUsers +
    			" registered users.");
    }

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.back) {
			Intent change = new Intent(getApplicationContext(), WebActivity.class);
			startActivity(change);
		}
	}	
}