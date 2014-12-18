package pegasus.Whysosad;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;

/**
 * 
 * @author Petroula
 * 
 */

public class ClientToServer {

	static String response = "";
	
	public String placeBet(String username, String mPassword, String betCountry, String hour, String minutes, String predictLevel, String credits) {

    	ArrayList<NameValuePair>parameters = new ArrayList<NameValuePair>();
	    parameters.add(new BasicNameValuePair("user", username));
	    parameters.add(new BasicNameValuePair("password", mPassword));
	    parameters.add(new BasicNameValuePair("country", betCountry));
	    parameters.add(new BasicNameValuePair("hour", hour));
	    parameters.add(new BasicNameValuePair("minute", minutes));
	    parameters.add(new BasicNameValuePair("targetstatus", predictLevel));
	    parameters.add(new BasicNameValuePair("credits", credits));
	    
	    return syncRequest("place_bet", parameters);
	}
    
    
    public static String currentHappiness(){
    
    	ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();
	    parameters.add(new BasicNameValuePair("apikey", "23jk4n823nasdf23rgdf"));
	    
	    return syncRequest("current_happiness", parameters);
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

	public static String userbets(String username, String password) {
    	ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();
	    parameters.add(new BasicNameValuePair("user", username));
	    parameters.add(new BasicNameValuePair("password", password));

	    return syncRequest("get_all_bets", parameters);
	}
}