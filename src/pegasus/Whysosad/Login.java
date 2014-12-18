package pegasus.Whysosad;

import android.app.AlertDialog;
import android.content.Intent;

public class Login implements HttpCallback {

	String username;
	String password;
	LoginActivity activity;

	Login(LoginActivity loginActivity, String username, String password) {
		this.activity = loginActivity;
		this.username = username;
		this.password = password;
	}

	public void moveOn() {
		activity.finish();
		User.username = username;
		User.password = password;
		Intent changeView = new Intent(activity.getApplicationContext(),
				ResultsActivity.class);
		activity.startActivity(changeView);
	}

	@Override
	public void callback(String response) {
		if(response.equals("")){
			AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(activity);

            dlgAlert.setMessage("Could not connect to server");
            dlgAlert.setTitle("Could not connect");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.create().show();
		}
		if (response.equals("Success")) {
			moveOn();
		} else {
			ClientToServer.Register(username, password, new HttpCallback() {
				@Override
				public void callback(String response) {
					activity.showProgress(false);
					if (response.equals("Success")) {
						moveOn();
					} else {
						activity.mPasswordView
								.setError("User allready exist, wrong password");
						activity.mPasswordView.requestFocus();
					}
				}
			});
		}
	}
}