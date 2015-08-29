package pe.test.mapsapplication;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by alumno on 8/29/15.
 */
public class Parser {

    public static Address parse(final String json)throws JSONException{
        final JSONObject jsonObject=new JSONObject(json);
        final double latitude= Double.valueOf(jsonObject.getString("lat"));
        final double longitude= Double.valueOf(jsonObject.getString("lon"));
        final String name= jsonObject.getString("display_name");
        final String country= jsonObject.getJSONObject("address").getString("country");
        return new Address(latitude, longitude, name, country);

    }
}
