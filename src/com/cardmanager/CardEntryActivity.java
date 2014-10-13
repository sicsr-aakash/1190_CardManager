package com.cardmanager;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CardEntryActivity extends Activity {
	
	
	EditText card_number,card_name,card_holder_name,validity_date,cvv,pin,password,bank_name;
	Button btn_submit,btn_reminder;
	RadioGroup rg;
	DBHelper handler;
	String radioValue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_entry);
		
		card_number = (EditText) findViewById(R.id.Card_number);
		card_name = (EditText) findViewById(R.id.Card_name);
		card_holder_name = (EditText) findViewById(R.id.Card_holder_name);
		validity_date = (EditText) findViewById(R.id.Validity_date);
		cvv = (EditText) findViewById(R.id.Card_cvv);
		pin = (EditText) findViewById(R.id.card_pin);
		password = (EditText) findViewById(R.id.Card_pass);
		bank_name = (EditText) findViewById(R.id.Card_bank_name);
		btn_submit = (Button) findViewById(R.id.submit);
		btn_reminder = (Button) findViewById(R.id.reminder);
		rg =(RadioGroup) findViewById(R.id.radioGroup1);
		
		
		
		
		btn_submit.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						String get_card_number = card_number.getText().toString();
						String get_card_name = card_name.getText().toString();
						String get_card_holder_name = card_holder_name.getText().toString();
						String get_validity_date = validity_date.getText().toString();
						String get_cvv = cvv.getText().toString();
						String get_pin = pin.getText().toString();
						String get_password = password.getText().toString();
						String get_bank_name = bank_name.getText().toString();
						if(rg.getCheckedRadioButtonId()!=-1){
						    int id= rg.getCheckedRadioButtonId();
						    View radioButton = rg.findViewById(id);
						    int radioId = rg.indexOfChild(radioButton);
						    RadioButton btn = (RadioButton) rg.getChildAt(radioId);
						    radioValue=btn.getText().toString();
						}
						
						if(get_card_number.length() > 0 && get_card_name.length() > 0 && get_validity_date.length() > 0 && get_bank_name.length() > 0 && get_cvv.length() > 0 && get_pin.length() > 0 && get_password.length() > 0 && get_card_holder_name.length() > 0){
							{
						
								handler = new DBHelper(getBaseContext(),DBHelper.DATABASE_NAME);
								
								long id = handler.carduser(get_card_number,get_card_name,get_card_holder_name,get_validity_date,get_cvv,get_pin,get_password,get_bank_name,radioValue, getIntent().getExtras().getString("username"));
								if(id>0){
									Toast.makeText(getBaseContext(), "Card Enterd successfully.", Toast.LENGTH_SHORT).show();
									finish();
								}
								else
								{
									Toast.makeText(getBaseContext(), "not successfully.", Toast.LENGTH_SHORT).show();
								}
								Intent intentObject = new Intent(getApplicationContext(),Menuall.class);
								startActivity(intentObject);
								
								
								handler.close();
							}	
				}
			   }//onClick.
			});//setOnClickListener.
		btn_reminder.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Calendar cal = Calendar.getInstance();              
				Intent intent = new Intent(Intent.ACTION_EDIT);
				intent.setType("vnd.android.cursor.item/event");
				intent.putExtra("beginTime", cal.getTimeInMillis());
				intent.putExtra("allDay", true);
				intent.putExtra("rrule", "FREQ=YEARLY");
				intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
				intent.putExtra("title", "A Test Event from android app");
				startActivity(intent);
				//Intent openNewAlarm = new Intent(AlarmClock.ACTION_SET_ALARM);
//				openNewAlarm.putExtra(AlarmClock.EXTRA_HOUR, 22);
//				openNewAlarm.putExtra(AlarmClock.EXTRA_MINUTES, 13);
				//startActivity(openNewAlarm);
				
			}
		});		
							
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	
	
	@Override
	public boolean  onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.about:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("About Us");
			builder.setMessage("Application made by:Sreejib Das using this application you can manage your all type of cards like Debit Card, Credit Card, Shopping Card, Net Banking and Others.");
			builder.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface d, int arg1) {
					// TODO Auto-generated method stub
					d.cancel();
				}
			});
			AlertDialog alert = builder.create();
			alert.show();
		}
		return true;
	}

}
