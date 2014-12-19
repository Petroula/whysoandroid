package pegasus.Whysosad;
import pegasus.Whysosad.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.about_page);
    	
    	String totalUsers = ClientToServer.totalUsers();
    	TextView aboutApp = (TextView)findViewById(R.id.aboutApp);
    	aboutApp.setText("This application displays the current level of happiness regarding a list of countries."
    			+ "Included in the main functionality of the application is the possibility to bet a certain number of "
    			+ "credits on a country's score.The user has to register/login as soon as the application starts and is "
    			+ "allowed to go back and forth between most of the pages.Currently we have a total of " + totalUsers +
    			" registered users.");
    }	
}