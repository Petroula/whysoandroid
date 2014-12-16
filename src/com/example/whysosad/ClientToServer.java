package com.example.whysosad;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.NameValuePair;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

/**
 * 
 * @author Petroula
 * 
 */

public class ClientToServer {

	static String response = "";
	
	BetActivity bet = new BetActivity();
	
	public String placeBet() {

    	ArrayList<NameValuePair>parameters = new ArrayList<NameValuePair>();
	    parameters.add(new BasicNameValuePair("user", "petra"));
	    parameters.add(new BasicNameValuePair("password", "davidishappy"));
	    parameters.add(new BasicNameValuePair("country", bet.betCountry));
	    parameters.add(new BasicNameValuePair("hour", bet.hour));
	    parameters.add(new BasicNameValuePair("minute", bet.minutes));
	    parameters.add(new BasicNameValuePair("targetstatus", bet.predictLevel));
	    parameters.add(new BasicNameValuePair("credits", "10"));

	    return syncRequest("place_bet", parameters);
	}
    
    
    public static String currentHappiness(){
    
    	ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();
	    parameters.add(new BasicNameValuePair("apikey", "23jk4n823nasdf23rgdf"));
	    
	    return syncRequest("current_happiness", parameters);
    }
	
	
    public String registerUser() {

    	ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();
	    parameters.add(new BasicNameValuePair("user", "pikachu"));
	    parameters.add(new BasicNameValuePair("password", "Pegasus!1291615525"));
	    
	    return syncRequest("register_user", parameters);
	}
	
    private static void aSyncRequest(String urlAppend,List<NameValuePair> params,HttpCallback callback){
		HttpRequest Request = new HttpRequest(urlAppend,params,callback);
		AsyncTask<String, Void, String> Response = Request.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
    
	private static String syncRequest(String urlAppend,List<NameValuePair> params){
		HttpRequest Request = new HttpRequest(urlAppend,params);
		AsyncTask<String, Void, String> Response = Request.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		try {
			return Response.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}


	public static void login(String username, String mPassword,HttpCallback callback) {

    	ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();
	    parameters.add(new BasicNameValuePair("user", username));
	    parameters.add(new BasicNameValuePair("password", mPassword));

	    aSyncRequest("login", parameters,callback);
	}


	public static void Register(String username, String mPassword,HttpCallback callback) {
    	ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();
	    parameters.add(new BasicNameValuePair("user", username));
	    parameters.add(new BasicNameValuePair("password", mPassword));

	    aSyncRequest("register_user", parameters,callback);
	}
}