package com.example.whysosad;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.widget.Toast;

class HttpRequest extends AsyncTask<String, Void, String> {

	static final String ip = "10.0.2.2";// "129.16.155.25";
	static final String port = "10111";
	static final String urlbegin = "http://" + ip + ":" + port
			+ "/esi/esi_facade:";
	
	HttpCallback callback = null;

	String url = "";
	List<NameValuePair> parameters;
	public boolean isLocked =false;

	HttpRequest(String function, List<NameValuePair> parameters) {
		this.parameters = parameters;
		url = urlbegin + function;
	}
	
	HttpRequest(String function, List<NameValuePair> parameters,HttpCallback callback) {
		this.parameters = parameters;
		url = urlbegin + function;
		this.callback = callback;
	}

	@Override
	protected String doInBackground(String... urls) {
		String response = "";
		try {

			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);

			httpPost.setEntity(new UrlEncodedFormEntity(parameters));

			HttpResponse httpResponse = httpClient.execute(httpPost);

			InputStreamReader input = new InputStreamReader(httpResponse
					.getEntity().getContent());

			BufferedReader bufferedReader = new BufferedReader(input);
			String line = "";
			String result = "";

			while ((line = bufferedReader.readLine()) != null)
				result += line;

			input.close();

			response = result;

		} catch (Exception e) {
			response = "";
		}

		return response;
	}

	@Override
	protected void onPostExecute(String response) {
		if(callback!=null){
			callback.callback(response);
		}
	}

}