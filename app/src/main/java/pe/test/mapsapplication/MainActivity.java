package pe.test.mapsapplication;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity implements MapFragment.MapFragmentInterface{

    private Location lastLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //lama al fragment manager y checa cuantos fragment tiene
        final MapFragment mapFragment =new MapFragment();
        mapFragment.setMapFragmentInterface(this);

        //esto sirve para jalar cualquier fragment nuevo.
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main_place_holder, mapFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()){
           case R.id.action_reverse_geocoding:
               if (lastLocation!= null){

               }else{
                   Toast.makeText(this, R.string.no_last_location, Toast.LENGTH_SHORT).show();
               }
               return true;
           default:
               return super.onOptionsItemSelected(item);
       }
    }

    @Override
    public void onLocationChange(Location location) {
        this.lastLocation=location;

    }

}
