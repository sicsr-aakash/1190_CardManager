package com.cardmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends Activity {
	
	EditText full_name,email,passwd,re_passwd,phone;
	Button btn_register,btn_back;
	
	
	DBHelper handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		
		full_name = (EditText) findViewById(R.id.full_name);
		email = (EditText) findViewById(R.id.email);
		phone = (EditText) findViewById(R.id.mobile);
		passwd = (EditText) findViewById(R.id.password);
		re_passwd = (EditText) findViewById(R.id.repassword);
		btn_register = (Button) findViewById(R.id.register);
		btn_back = (Button) findViewById(R.id.back);
		
		
		btn_register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String get_full_name = full_name.getText().toString();
				String get_email = email.getText().toString();
				String get_passwd = passwd.getText().toString();
				String get_re_passwd = re_passwd.getText().toString();
				String get_phone = phone.getText().toString();
				
				
				if(get_full_name.length() > 0 && get_email.length() > 0 && get_phone.length() > 0 && get_passwd.length() > 0 && get_re_passwd.length() > 0 ){
					if(get_passwd.length() >= 8){
					if(get_passwd.equals(get_re_passwd)){
						
						
					if(isValidEmail(get_email)){
				
						handler = new DBHelper(getBaseContext(),DBHelper.DATABASE_NAME);
						
						int eid = handler.loginemailreg (get_email);
						if (eid==1){
							Toast.makeText(getBaseContext(), "Email Already exist.", Toast.LENGTH_SHORT).show();
							finish();
						}
						else{
							long id = handler.registerUser(get_full_name,get_email,get_passwd,get_phone);
							if(id>0){
								Toast.makeText(getBaseContext(), "Registered successfully.", Toast.LENGTH_SHORT).show();
								finish();
							}
							else
							{
								Toast.makeText(getBaseContext(), "not successfully.", Toast.LENGTH_SHORT).show();
								finish();
							}
						}
						Intent intentObject = new Intent(getApplicationContext(),MainActivity.class);
						startActivity(intentObject);
						
						
						handler.close();
					}
					else{
						Toast.makeText(getBaseContext(), "Invalid email.", Toast.LENGTH_SHORT).show();
						
					}}
					else
					{
						Toast.makeText(getBaseContext(), "Password and Retype password is not match.", Toast.LENGTH_SHORT).show();
					}}
					else{
						Toast.makeText(getBaseContext(), "Password is too sort minimun length 8.", Toast.LENGTH_SHORT).show();
					}
			
		}else{
			Toast.makeText(getBaseContext(), "Please fill all details.", Toast.LENGTH_SHORT).show();
		 }
	   }//onClick.
	});//setOnClickListener.
						
						
		//Back button on registration screen.
				btn_back.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intentObject = new Intent(getApplicationContext(),MainActivity.class);
						startActivity(intentObject);
					}
				});
			}

			@Override
			public boolean onCreateOptionsMenu(Menu menu) {
				// Inflate the menu; this adds items to the action bar if it is present.
				getMenuInflater().inflate(R.menu.main, menu);
				return true;
			}
			
			public final static boolean isValidEmail(CharSequence target) {
			    if (target == null) 
			        return false;

			    return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
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
