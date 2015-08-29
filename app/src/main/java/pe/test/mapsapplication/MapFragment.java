package pe.test.mapsapplication;

import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by alumno on 8/29/15.
 */
public class MapFragment extends com.google.android.gms.maps.MapFragment implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationChangeListener {
    private GoogleMap googleMap;
    private MapFragmentInterface mapFragmentInterface;

    //la clase se instancia
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //para habilitar el boton de obtener ubicacion
        googleMap.setMyLocationEnabled(true);

        //capturar primero la  ubicacion al apretar el boton
        googleMap.setOnMyLocationButtonClickListener(this);

        //Capturar la ubicacion al cambio de locacion
        googleMap.setOnMyLocationChangeListener(this);

    }

    /*si este metodo devuelve false enconteces automaticamente cuando se obtenga la direccion
    la camara se desplazara a ese punto. si devuelve true  entonces no se desplazara
     */

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(getActivity(), R.string.getting_location, Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onMyLocationChange(Location location) {
        if (mapFragmentInterface != null) {
            mapFragmentInterface.onLocationChange(location);
        }

    }
    /*En este laboratorio no vamos a utilizar este metodo, sin embargo
    sirve para mover el mapa a unas coordenadas deseadas.
     */

    public void moveTo(final double latitude, final double longitude, final boolean animate) {
        final LatLng latLng = new LatLng(latitude, longitude);
        final CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)
                .build();
        final CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        if (animate) {
            googleMap.animateCamera(null);
        } else {
            googleMap.moveCamera(null);
        }
    }

    public void setMapFragmentInterface(MapFragmentInterface mapFragmentInterface) {
        this.mapFragmentInterface = mapFragmentInterface;
    }

    //tiene que estar statica ya que la clase serìa como si fuera una clase aparte.
    public static interface MapFragmentInterface {
        public void onLocationChange(Location location);


    }


}
