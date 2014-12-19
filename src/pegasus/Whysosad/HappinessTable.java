package pegasus.Whysosad;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class HappinessTable extends TableLayout {

	Context context;
	
	public HappinessTable(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    context = getContext();
	}

	public HappinessTable(Context context) {
		super(context);
		this.context = getContext();
	}

	public void addRow(String countryname, String value, final ResultsActivity resultsActivity) {
		final TableRow row = new TableRow(getContext());
		TextView country = new TextView(getContext());
		
		country.setText(countryname);
		country.setTextColor(Color.WHITE);
		country.setTextSize(30);
		country.setGravity(Gravity.CENTER);
		
		TextView valuetext = new TextView(getContext());
		
		valuetext.setText(value);
		valuetext.setTextSize(30);
		valuetext.setGravity(Gravity.CENTER);

		row.setClickable(true);
		row.setOnClickListener(new OnClickListener() {

			/**
			 * Displays a dialog box after clicking on any of the table rows
			 */
			public void onClick(final View v) {
				row.setBackgroundColor(Color.GRAY);
				final AlertDialog alert = new AlertDialog.Builder(getContext())
						.create();
				alert.setTitle("*Bet*");
				alert.setMessage("Do you want to place a bet?");
				alert.setButton(DialogInterface.BUTTON_NEGATIVE,"No", new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog,
							final int which) {
						alert.dismiss();
						row.setBackgroundColor(Color.TRANSPARENT);
					}
				});
				alert.setCanceledOnTouchOutside(false);
				alert.setButton(DialogInterface.BUTTON_POSITIVE,"Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

						/**
						 * Get the info from the selected row and pass it to the
						 * other activity
						 */
						TableRow tr = (TableRow) v;
						TextView selectedC = (TextView) tr.getChildAt(0);
						TextView selectedK = (TextView) tr.getChildAt(1);
						String selectedCountry = selectedC.getText().toString();
						String selectedKey = selectedK.getText().toString();
						Intent changeView = new Intent(resultsActivity,
								BetActivity.class);
						changeView.putExtra("country", selectedCountry);
						changeView.putExtra("key", selectedKey);
						resultsActivity.startActivity(changeView);
					}
				});

				alert.setCanceledOnTouchOutside(false);
				alert.show();
			}
		});

		if (Integer.parseInt(value) < 0) {
			valuetext.setTextColor(Color.RED);
		} else if (Integer.parseInt(value) == 0) {
			valuetext.setTextColor(Color.YELLOW);
		} else {
			valuetext.setTextColor(Color.GREEN);
		}
		row.addView(country);
		row.addView(valuetext);
		addView(row);
	}

}
