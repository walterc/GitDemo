package ASUI.geomobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log; 

public class DBAdapter
{    
	private static final String TAG = "DBAdapter";     
	private static final String DATABASE_NAME = "geomobiletours"; 
	private static final int DATABASE_VERSION = 1;   
	
	//TABLE administrators
	public static final String KEY_IDADMIN = "id_admin";    
	public static final String KEY_ADMINPASSWORD = "admin_password";   
	public static final String KEY_NAME = "admin_name";
	private static final String DB_TABLE_ADMINS = "administrators";  
  
	private static final String TABLE_CREATE_ADMINS =
			"create table administrators (id_admin integer primary key autoincrement, "
	+ "admin_password text not null, admin_name text not null);"; 
	
	//TABLE tour
	public static final String KEY_IDTOUR = "id_tour";
	public static final String KEY_IDWATCHMAN = "id_watchman";
	public static final String KEY_DATETIMETOUR = "datetimetour"; 
	private static final String DB_TABLE_TOUR = "tour";  
  
	private static final String TABLE_CREATE_TOUR =
			"create table status (id_tour integer primary key autoincrement, "
	+ "id_watchman numeric not null, datetimetour text not null);"; 
	
	//TABLE tour_details
	public static final String KEY_IDTOURDETAIL = "id_tourdetail";
	public static final String KEY_FKIDTOUR = "id_tour";
	public static final String KEY_FKIDSPOT = "id_spot";  
	public static final String KEY_FKIDSTATUS = "id_status";
	public static final String KEY_DATETIMESPOT = "datetimespot";
	public static final String KEY_SPOTJUSTIFY="spot_justify";
	private static final String DB_TABLE_TOUR_DETAILS = "tour_details"; 
  
	private static final String TABLE_CREATE_TOURSDETAILS =
			"create table tour_details (id_tourdetail integer primary key autoincrement, "
	+ "id_tour integer not null, id_spot integer not null, id_status integer not null, datetimespot text not null);"; 
	
	//TABLE spots
	public static final String KEY_IDSPOT = "id_spot"; 
	public static final String KEY_LATITUDE = "id_latitude"; 
	public static final String KEY_LONGITUDE = "id_longitude"; 
	public static final String KEY_SPOTDESC = "spot_description"; 
	private static final String DB_TABLE_SPOTS = "spots";  
  
	private static final String TABLE_CREATE_SPOTS =
			"create table status (id_spot integer primary key autoincrement, "
	+ "latitude integer not null, longitude integer not null, spot_description integer not null);"; 
	
	//TABLE status
	public static final String KEY_IDSTATUS = "id_status";    
	public static final String KEY_STATUSDESC = "status_description";
	private static final String DB_TABLE_STATUS = "status";  
  
	private static final String TABLE_CREATE_STATUS =
			"create table status (id_status integer primary key autoincrement, "
	+ "status_description text not null);";
	
	
	private final Context context;      
	private DatabaseHelper DBHelper;    
	private SQLiteDatabase db;
	
	
    /* Constructor - context to allow the database to be opened/created */
	public DBAdapter(Context ctx)     
	{        
		this.context = ctx;        
		DBHelper = new DatabaseHelper(context);    
	}     
	
	private static class DatabaseHelper extends SQLiteOpenHelper     
	{        
		DatabaseHelper(Context context)         
		{            
			super(context, DATABASE_NAME, null, DATABASE_VERSION);        
		}         
		
		@Override        
		public void onCreate(SQLiteDatabase db)         
		{
			db.execSQL(TABLE_CREATE_ADMINS);
			db.execSQL(TABLE_CREATE_SPOTS);
			db.execSQL(TABLE_CREATE_STATUS);
			db.execSQL(TABLE_CREATE_TOUR);
			db.execSQL(TABLE_CREATE_TOURSDETAILS);
		}
		@Override        
		public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion)         
		{            
			Log.w(TAG, "Upgrading database from version " 
					+ oldVersion                     
					+ " to "                    
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS administrators");   //falta outras         
			onCreate(db);        
		}
		
	}
	
	 // opens the database    
	public DBAdapter open() throws SQLException     
	{        
		db = DBHelper.getWritableDatabase();        
		return this;    
	}     
	
	//---closes the database---        
	public void close()     
	{        DBHelper.close();    
	}
	
	//////////////////////////////
	// ADMINISTRATORS TABLE
	//////////////////////////////
	
	//insert an admin    
	public long insertadmin(String admin_password, String admin_name)     
	{        
		ContentValues initialValues = new ContentValues();        
		initialValues.put(KEY_ADMINPASSWORD, admin_password);        
		initialValues.put(KEY_NAME, admin_name);             
		return db.insert(DB_TABLE_ADMINS, null, initialValues);
	} 
	 	
	//////////////////////////////
	// TOUR TABLE
	//////////////////////////////
	
	//insert a tour    
	public long inserttour(String id_watchman, String datetimetour)     
	{        
		ContentValues initialValues = new ContentValues();       
		initialValues.put(KEY_IDWATCHMAN, id_watchman);
		initialValues.put(KEY_DATETIMETOUR, datetimetour); 
		return db.insert(DB_TABLE_TOUR, null, initialValues);
	} 
	
	//////////////////////////////
	// TOUR DETAILS TABLE
	//////////////////////////////
	
	//insert a tourdetails
	public long inserttourdetails(Integer id_tour, Integer id_spot, Integer id_status, String datetimespot, String spot_justify)     
	{        
		ContentValues initialValues = new ContentValues();       
		initialValues.put(KEY_FKIDTOUR, id_tour);
		initialValues.put(KEY_FKIDSPOT, id_spot);
		initialValues.put(KEY_FKIDSTATUS, id_status); 
		initialValues.put(KEY_DATETIMESPOT, datetimespot); 
		initialValues.put(KEY_SPOTJUSTIFY, spot_justify); 
		return db.insert(DB_TABLE_TOUR_DETAILS, null, initialValues);
	}

	//////////////////////////////
	// SPOTS TABLE
	//////////////////////////////
	
	//insert a spot 
	public long insertspot(String id_latitude, String id_longitude, String spot_description)     
	{        
		ContentValues initialValues = new ContentValues();       
		initialValues.put(KEY_LATITUDE, id_latitude);
		initialValues.put(KEY_LONGITUDE, id_longitude);
		initialValues.put(KEY_SPOTDESC, spot_description);
		return db.insert(DB_TABLE_SPOTS, null, initialValues);
	}
	
	//////////////////////////////
	// STATUS TABLE
	//////////////////////////////
	
	//insert a status    
	public long insertstatus(String status_description)     
	{        
		ContentValues initialValues = new ContentValues();       
		initialValues.put(KEY_STATUSDESC, status_description);             
		return db.insert(DB_TABLE_STATUS, null, initialValues);
	} 
	
}