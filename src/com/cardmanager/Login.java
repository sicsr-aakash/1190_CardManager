package com.cardmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	
	
	DBHelper handler;
	Button login_btn_succ;
	EditText email,passwd;
	SharedPreferences pref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		pref = getApplicationContext().getSharedPreferences("users.dat", MODE_PRIVATE);
		login_btn_succ=(Button)findViewById(R.id.button1);
		
		email = (EditText) findViewById(R.id.editText1);
		passwd = (EditText) findViewById(R.id.editText2);
		
		login_btn_succ.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				handler = new DBHelper(getBaseContext(),DBHelper.DATABASE_NAME);
				
				
				String email1 = email.getText().toString();
				String passwd2 = passwd.getText().toString();
				
				
				
				if(email1.length() > 0 && passwd2.length() > 0){
				
					boolean login_result = handler.login(email1, passwd2);
					if(login_result){
						Editor editor = pref.edit();
						editor.putString("username", email1);
						editor.commit();
						Intent intentobject = new Intent(getApplicationContext(),Menuall.class);
						Bundle b =new Bundle();
						b.putString("email", email1);
						intentobject.putExtras(b);
						startActivity(intentobject);
					
						finish();
					}
					else{
						Toast.makeText(getBaseContext(), "Invalid Login Details", Toast.LENGTH_SHORT).show();
					}
					
				}
				else{
					Toast.makeText(getBaseContext(), "Username or password is empty", Toast.LENGTH_SHORT).show();
				}
					
				handler.close();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
