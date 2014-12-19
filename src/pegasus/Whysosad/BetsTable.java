package pegasus.Whysosad;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class BetsTable extends TableLayout {

	Context context;
	
	public BetsTable(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    context = getContext();
	}

	public BetsTable(Context context) {
		super(context);
		this.context = getContext();
	}
	


	private void addColumn(String text, int color, TableRow row) {
		TextView textview = new TextView(getContext());
		textview.setText(text);
		textview.setTextColor(color);
		textview.setTextSize(30);
		textview.setGravity(Gravity.CENTER);
		row.addView(textview);
	}

	public void addRow(String countryname, String status, double odds, int credits, int targettime, final BetResultsActivity resultsActivity) {
		final TableRow row = new TableRow(getContext());
		String timeleft = "";
		String creditsPrefix = "";
		addColumn(countryname,Color.WHITE,row);
		
		int statuscolor = 0;
		if (status.equals("lost")) {
			statuscolor = Color.RED;
			credits = -credits;
		} else if (status.equals("inprogress")) {
			creditsPrefix = "+";
			timeleft = Integer.toString(targettime);
			statuscolor = Color.YELLOW;
		} else {
			credits = (int) (credits * odds);
			creditsPrefix = "+";
			statuscolor = Color.GREEN;
		}
		
		addColumn(status,Color.WHITE,row);
		addColumn(creditsPrefix + Integer.toString(credits),statuscolor,row);
		addColumn(timeleft,Color.WHITE,row);
		
		addView(row);
	}

}
