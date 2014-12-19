package pegasus.Whysosad;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

@SuppressLint({ "SetJavaScriptEnabled", "CutPasteId" })
public class WebActivity extends Activity implements OnClickListener {

	Button back;
	Button reload;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);
		
		back = (Button)findViewById(R.id.back);
		back.setOnClickListener(this);
		
		reload = (Button)findViewById(R.id.reload);
		reload.setOnClickListener(this);
		
		showMap();	
	}

	public void showMap() {
		WebView webView = (WebView) findViewById(R.id.web_map);
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		
		WebView browser = (WebView) findViewById(R.id.web_map);
	    browser.getSettings().setLoadWithOverviewMode(true);
	    browser.getSettings().setUseWideViewPort(true);
		
		webView.loadUrl("https://platis.solutions/jiaxin/");
	}
	
	
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.back) {
			Intent changeView = new Intent(getApplicationContext(), ResultsActivity.class);
			startActivity(changeView);
		} else if(v.getId() == R.id.reload) {
			showMap();
		}
		
	}
	
	
}