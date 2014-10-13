package com.cardmanager;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity 
{
	private Button login_btn;
	private Button registration_btn;
	SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        login_btn=(Button)findViewById(R.id.button1);
        registration_btn=(Button)findViewById(R.id.button2);
        
        pref = getApplicationContext().getSharedPreferences("users.dat", MODE_PRIVATE);
		if(pref.contains("username"))
		{
			if(!pref.getString("username", "null").equals("null"))
			{
				finish();
				Intent intent = new Intent(getApplicationContext(),Menuall.class);
				Bundle b =new Bundle();
				b.putString("email", pref.getString("username", "null"));
				intent.putExtras(b);
				startActivity(intent);
				finish();
			}
		}
        
        login_btn.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View v) 
			{
				Intent intentobj=new Intent(getApplicationContext(),Login.class);
				startActivity(intentobj);
			}
		});
        
        registration_btn.setOnClickListener(new OnClickListener() 
        {
			
			@Override
			public void onClick(View v) 
			{
				Intent intentobj1=new Intent(getApplicationContext(),RegistrationActivity.class);
				startActivity(intentobj1);
			}
		});
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
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
