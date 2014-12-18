package pegasus.Whysosad;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;


/**
 * 
 * @author Petroula
 * 
 */


public class MainActivity extends Activity {
	
    AnimationDrawable animationD;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ImageView animationV = (ImageView) findViewById(R.id.animationView);
    	animationV.setBackgroundResource(R.drawable.animation);
    	animationD = (AnimationDrawable) animationV.getBackground();
    	animationD.start();
	
		
		/** Starts the next activity after 3 seconds */
		
		new CountDownTimer(2000, 100) {
		
		public void onTick(long millisUntilFinished) {
		
		}
		public void onFinish() {
		
			Intent changeView = new Intent(getApplicationContext(), LoginActivity.class);
			startActivity(changeView);
		}
		}.start();
		
	}

		 		
}