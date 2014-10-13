package com.cardmanager;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;









import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

public class Allcardno extends Activity {
	
	private ArrayList<Card> cards;
	ListView listView;
	SharedPreferences pref;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_allcardno);
		pref = getSharedPreferences("users.dat", MODE_PRIVATE);
		String email = pref.getString("username", "null");//getIntent().getExtras().getString("email");
		String type = getIntent().getExtras().getString("cardtype");
		DBHelper handler = new DBHelper(getBaseContext(),DBHelper.DATABASE_NAME);
		cards = handler.getUserCards(email, type);
		ArrayList<String> names = new ArrayList<String>();
		for(Card c: cards)
		{
			names.add(c.getCard_name());
		}
		//Log.e("Cards", names.get(0));
		CustomAdapter ca = new CustomAdapter(getApplicationContext(), names);
		listView = (ListView) findViewById(R.id.all_cards);
		listView.setAdapter(ca);
		final Context context = this;
		listView.setOnItemClickListener(new OnItemClickListener() {
		
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Card c = cards.get(position);
				final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				alertDialogBuilder.setTitle("View Details");
				alertDialogBuilder.setMessage("Input text to search");
				LinearLayout card_entry = (LinearLayout) findViewById(R.layout.activity_card_entry);
				View view = card_entry.inflate(getApplicationContext(), R.layout.activity_card_entry, null);
				EditText card_number,card_name,card_holder_name,validity_date,cvv,pin,password,bank_name;
				RadioGroup rg;
				card_number = (EditText) view.findViewById(R.id.Card_number);
				card_number.setFocusable(false);
				card_name = (EditText) view.findViewById(R.id.Card_name);
				card_name.setFocusable(false);
				card_holder_name = (EditText) view.findViewById(R.id.Card_holder_name);
				card_holder_name.setFocusable(false);
				validity_date = (EditText) view.findViewById(R.id.Validity_date);
				validity_date.setFocusable(false);
				cvv = (EditText) view.findViewById(R.id.Card_cvv);
				cvv.setFocusable(false);
				pin = (EditText) view.findViewById(R.id.card_pin);
				pin.setInputType(InputType.TYPE_CLASS_TEXT);
				pin.setFocusable(false);
				password = (EditText) view.findViewById(R.id.Card_pass);
				password.setInputType(InputType.TYPE_CLASS_TEXT);
				password.setFocusable(false);
				bank_name = (EditText) view.findViewById(R.id.Card_bank_name);
				bank_name.setFocusable(false);
				rg =(RadioGroup) view.findViewById(R.id.radioGroup1);
				card_number.setText(c.getCard_no());
				card_name.setText(c.getCard_name());
				card_holder_name.setText(c.getCard_holder_name());
				cvv.setText(c.getCard_cvv());
				pin.setText(c.getCard_pin());
				password.setText(c.getCard_password());
				bank_name.setText(c.getCard_bank());
				validity_date.setText(c.getCard_validity());
				String radioValue = c.getCard_type();
				switch(radioValue)
				{
					case "Debit Card": rg.check(R.id.radio0); 
										break;
					case "Credit Card": rg.check(R.id.radio1);
										break;
					case "Net Banking": rg.check(R.id.radio3);
										break;
					case "Shopping Card": rg.check(R.id.radio4);
										break;
					case "Others": rg.check(R.id.radio5);
										break;
				}
				alertDialogBuilder.setView(view);
				final Context context = getApplicationContext();
				
				alertDialogBuilder.setPositiveButton("Back", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int whichButton) {
						
					}
				});	
				
				
				AlertDialog al = alertDialogBuilder.create();
				al.show();
				
				
			}

		
			
		});
		
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
