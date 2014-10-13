package com.cardmanager;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
	public static final String CARD_NUMBER = "card_number";
	public static final String CARD_NAME = "card_name";
	public static final String CARD_HOLDER_NAME = "card_holder_name";
	public static final String VALIDITY_DATE = "validity_date";
	public static final String CARD_CVV = "card_cvv";
	public static final String CARD_ID = "card_id";
	public static final String CARD_PIN = "card_pin";
	public static final String CARD_PASSWORD = "card_password";
	public static final String CARD_BANK_NAME = "card_bank_name";
	public static final String CARD_TYPE="card_type";
	public static final String USER_ID = "user_id";
	public static final String TABLE_NAME_CARD = "user_card_detail";
	public static final String FULL_NAME = "fullname";
	public static final String EMAIL = "email";
	public static final String MOBILE = "mobile_no";
	public static final String PASSWORD = "password";
	public static final String TABLE_NAME = "user_detail";
	public static final String DATABASE_NAME = "cards.db";
	public static final int  DATABASE_VERSION = 1;
	public static final String USER_TABLE_CREATE = "create table "+TABLE_NAME+" (id integer primary key autoincrement,"+EMAIL+" text not"
			+ " null,"+FULL_NAME+" text not null, "+PASSWORD+" text not null,"+MOBILE+" text not null);";
	public static final String USER_TABLE_CREATE_CARD = "create table "+TABLE_NAME_CARD+" (" + CARD_ID + " integer primary key autoincrement," + CARD_NUMBER + " text not null, "+CARD_NAME+" text not"
			+ " null,"+CARD_HOLDER_NAME+" text , "+CARD_PASSWORD+" text ,"+CARD_BANK_NAME+" text,"+VALIDITY_DATE+" text not null,"+CARD_CVV+" text,"+CARD_PIN+" text, "+CARD_TYPE+" text not null," +USER_ID+" text not null);";
	

	public DBHelper(Context context, String name) {
		super(context, name, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try{
			db.execSQL(USER_TABLE_CREATE);
			db.execSQL(USER_TABLE_CREATE_CARD);
		}catch(SQLException e)
		{
			e.printStackTrace();
		}

	}
	
	

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	
	public int loginemailreg (String email1) throws SQLException
	{
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE EMAIL=? ", new String[]{email1});
		
			if(c.getCount() > 0)
			{
		
				return 1;
			}
			else{
					return 0;
			}
	}

	public long registerUser(String full_name,String email,String passwd,String phone){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues content = new ContentValues();
		content.put(FULL_NAME, full_name);
		content.put(EMAIL, email);
		content.put(MOBILE, phone);
		content.put(PASSWORD, passwd);
        return db.insertOrThrow(TABLE_NAME, null, content);
		
	}
	
	public long carduser(String get_card_number,String get_card_name,String get_card_holder_name,String get_validity_date,String get_cvv,String get_pin,String get_password,String get_bank_name, String card_type, String user_id){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues content = new ContentValues();
		content.put(CARD_NUMBER, get_card_number);
		content.put(CARD_NAME, get_card_name);
		content.put(CARD_HOLDER_NAME, get_card_holder_name);
		content.put(VALIDITY_DATE, get_validity_date);
		content.put(CARD_CVV, get_cvv);
		content.put(CARD_PIN, get_pin);
		content.put(CARD_PASSWORD, get_password);
		content.put(CARD_BANK_NAME, get_bank_name);
		content.put(CARD_TYPE, card_type);
		content.put(USER_ID , user_id);
        return db.insertOrThrow(TABLE_NAME_CARD, null, content);
		
	}
	
	
	
	public boolean login(String email1, String passwd2) throws SQLException
	{
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE EMAIL=? AND PASSWORD=?", new String[]{email1,passwd2});
		
			if(c.getCount() > 0)
			{
		
				return true;
			}
			else{
					return false;
			}
	}
	
	
	public boolean delete(int id) throws SQLException
	{
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery("DELETE FROM " + TABLE_NAME_CARD + " WHERE "+ CARD_ID +"=?", new String[]{Integer.toString(id)});
		
			if(c.getCount() > 0)
			{
		
				return true;
			}
			else{
					return false;
			}
	}
	public boolean update(Card c) throws SQLException
	{
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cur = db.rawQuery("UPDATE " + TABLE_NAME_CARD + " SET "+CARD_NAME +"=?, "+CARD_NUMBER +"=?, "+CARD_HOLDER_NAME+"=?, "+CARD_PASSWORD+"=?, "+ CARD_PIN+"=?, "+CARD_CVV+"=?, "+CARD_TYPE+"=?, "+VALIDITY_DATE+"=?, "+CARD_BANK_NAME+"=? WHERE "+ CARD_ID +"=?", new String[]{c.getCard_name(),c.getCard_no(),c.getCard_holder_name(),c.getCard_password(),c.getCard_pin(),c.getCard_cvv(),c.getCard_type(),c.getCard_validity(),c.getCard_bank(),Integer.toString(c.getCard_id())});
		
			if(cur.getCount() > 0)
			{
		
				return true;
			}
			else{
					return false;
			}
	}
	
public boolean ChangePassword(String email, String passwd) throws SQLException
	{
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery("UPDATE " + TABLE_NAME + " SET PASSWORD=? WHERE EMAIL=?", new String[]{passwd, email});
	
			if(c.getCount() > 0)
			{
				return true;
			
				//Cursor d = db.rawQuery("UPDATE" + TABLE_NAME + " SET "+ PASSWORD=? + WHERE EMAIL=? , new String[]{email1,passwd});
			
			}
			else{
					return false;
			}
	}
	
	
	
	public ArrayList<Card> getUserCards(String email, String type)
	{
		ArrayList<Card> allCards = new ArrayList<Card>();
		String sql = "SELECT * FROM " + TABLE_NAME_CARD + " WHERE USER_ID=? AND CARD_TYPE=?";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(sql, new String[]{email,type});
		c.moveToFirst();
		while (c.isAfterLast() == false) 
		{
			int id = c.getInt(c.getColumnIndex(CARD_ID));
		    String card_name = c.getString(c.getColumnIndex(CARD_NAME));
		    String card_no = c.getString(c.getColumnIndex(CARD_NUMBER));
		    
		    String card_holder_name = c.getString(c.getColumnIndex(CARD_HOLDER_NAME));
		    String card_pin = c.getString(c.getColumnIndex(CARD_PIN));
		    String card_validity = c.getString(c.getColumnIndex(VALIDITY_DATE));
		    String card_user_email = c.getString(c.getColumnIndex(USER_ID));
		    String card_cvv = c.getString(c.getColumnIndex(CARD_CVV));
		    String card_bank = c.getString(c.getColumnIndex(CARD_BANK_NAME));
		    String card_type = c.getString(c.getColumnIndex(CARD_TYPE));
		    String card_password = c.getString(c.getColumnIndex(CARD_PASSWORD));
		    Card card = new Card(card_no, card_name, card_holder_name, card_validity, card_password, card_pin, card_cvv, card_bank, card_type, card_user_email);
		    card.setCard_id(id);
		    allCards.add(card);
		    c.moveToNext();
		}
		return allCards;
	}
	public boolean cardsExists(String email, String type){
		String sql = "SELECT * FROM " + TABLE_NAME_CARD + " WHERE USER_ID=? AND CARD_TYPE=?";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(sql, new String[]{email,type});
		if(c.getCount() > 0){
			return true;
		}
		else
			return false;
	}
}
