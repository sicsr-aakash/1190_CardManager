package com.cardmanager;


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

public class Update1 extends Activity {
	
	private Button d_card;
	private Button c_card;
	private Button s_card;
	private Button n_card;
	private Button o_card;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_card2);
		
		d_card=(Button)findViewById(R.id.button1);
		c_card=(Button)findViewById(R.id.button2);
		s_card=(Button)findViewById(R.id.button5);
		n_card=(Button)findViewById(R.id.button6);
		o_card=(Button)findViewById(R.id.button7);
		
		
		d_card.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View v) 
			{
				Intent intentobj=new Intent(getApplicationContext(),Update2.class);
				Bundle b = new Bundle();
				b.putString("cardtype", "Debit Card");
				intentobj.putExtras(b);
				startActivity(intentobj);
			}
		});
		
		c_card.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View v) 
			{
				Intent intentobj=new Intent(getApplicationContext(),Update2.class);
				Bundle b = new Bundle();
				b.putString("cardtype", "Credit Card");
				intentobj.putExtras(b);
				startActivity(intentobj);
			}
		});
		
		s_card.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View v) 
			{
				Intent intentobj=new Intent(getApplicationContext(),Update2.class);
				Bundle b = new Bundle();
				//b.putString("username",getIntent().getExtras().getString("email"));
				b.putString("cardtype", "Shopping Card");
				intentobj.putExtras(b);
				startActivity(intentobj);
			}
		});
		
		n_card.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View v) 
			{
				Intent intentobj=new Intent(getApplicationContext(),Update2.class);
				Bundle b = new Bundle();
				b.putString("cardtype", "Net Banking");
				intentobj.putExtras(b);
				startActivity(intentobj);
			}
		});
		
		o_card.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View v) 
			{
				Intent intentobj=new Intent(getApplicationContext(),Update2.class);
				Bundle b = new Bundle();
				b.putString("cardtype", "Others");
				intentobj.putExtras(b);
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
