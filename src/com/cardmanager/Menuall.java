
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

public class Menuall extends Activity {
	
	private Button new_card;
	private Button view_card;
	private Button delete_card;
	private Button update_card;
	private Button change_password;
	private Button logout;
	SharedPreferences pref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		pref = getSharedPreferences("users.dat", MODE_PRIVATE);
		new_card=(Button)findViewById(R.id.button1);
		view_card=(Button)findViewById(R.id.button2);
		delete_card=(Button)findViewById(R.id.button3);
		logout=(Button)findViewById(R.id.logout);
		change_password=(Button)findViewById(R.id.changepassword);
		update_card=(Button)findViewById(R.id.update);
		
		new_card.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View v) 
			{
				Intent intentobj=new Intent(getApplicationContext(),CardEntryActivity.class);
				Bundle b = new Bundle();
				b.putString("username",pref.getString("username", null));
				intentobj.putExtras(b);
				startActivity(intentobj);
			}
		});
		
		view_card.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View v) 
			{
				Intent intentobj=new Intent(getApplicationContext(),View_card2.class);
				Bundle b = new Bundle();
				b.putString("username",pref.getString("username", null));
				intentobj.putExtras(b);
				startActivity(intentobj);
			}
		});
		
		
		delete_card.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View v) 
			{
				Intent intentobj=new Intent(getApplicationContext(),Delete1.class);
				Bundle b = new Bundle();
				b.putString("username",pref.getString("username", null));
				intentobj.putExtras(b);
				startActivity(intentobj);
			}
		});
		
		update_card.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View v) 
			{
				Intent intentobject=new Intent(getApplicationContext(),Update1.class);
				Bundle b = new Bundle();
				b.putString("username",pref.getString("username", null));
				intentobject.putExtras(b);
				startActivity(intentobject);
			}
		});
		
		change_password.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View v) 
			{
				Intent intentobject=new Intent(getApplicationContext(),ChangePasswordActivity.class);
				Bundle b = new Bundle();
				b.putString("username",pref.getString("username", null));
				intentobject.putExtras(b);
				startActivity(intentobject);
			}
		});
		
		logout.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View v) 
			{
				
				Editor e = pref.edit().clear();
				e.commit();
				Intent intentobj=new Intent(getApplicationContext(),MainActivity.class);
				startActivity(intentobj);
			}
		});
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
