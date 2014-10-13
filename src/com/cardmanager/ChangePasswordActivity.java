
package com.cardmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePasswordActivity extends Activity {

	EditText oldPass,newPass,newConfPass;
	Button change;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_password);
		final String email = getIntent().getExtras().getString("username");
		oldPass = (EditText) findViewById(R.id.oldpassword);
		newPass = (EditText) findViewById(R.id.newpassword);
		newConfPass = (EditText) findViewById(R.id.renewpassword);
		change = (Button) findViewById(R.id.change);
		
		OnClickListener listener = new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				DBHelper dbHelper = new DBHelper(getApplicationContext(), DBHelper.DATABASE_NAME);
				String pass = oldPass.getText().toString();
				String new_pass = newPass.getText().toString();
				String conf_pass = newConfPass.getText().toString();
				
				if(dbHelper.login(email, pass))
				{
					if(new_pass.equals(conf_pass))
					{
						dbHelper.ChangePassword(email, new_pass);
						Toast.makeText(getApplicationContext(), "Password changed successfuly", Toast.LENGTH_SHORT);
						finish();
					}
					else
					{
						Toast.makeText(getApplicationContext(), "Passwords don't match!", Toast.LENGTH_SHORT);
					}
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT);
				}
					
				
			}
		};
		change.setOnClickListener(listener);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
