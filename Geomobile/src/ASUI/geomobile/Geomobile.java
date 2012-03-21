package ASUI.geomobile;

//import geomobile.namespace.DBAdapter;
import android.app.Activity;
import android.os.Bundle;

//database support
import android.database.Cursor;
import android.widget.Toast;

public class Geomobile extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //instantiates a DBAdapter class
        DBAdapter db = new DBAdapter(this); 
    }
}