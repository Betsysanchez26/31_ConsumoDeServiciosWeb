package com.example.betsysanchez.a31_consumodeserviciosweb;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by BetsySanchez on 20/03/2018.
 */

public class GetJSON extends AsyncTask<Void,Void,Void> {
    String data ="";
    String dataParsed = "";
    String singleParsed ="";

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Tepic,mx&APPID=0906362826d2cfea265ed029381a7e31");
            HttpURLConnection httpURLConnection = (HttpURLConnection)
                    url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new
                    InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
                JSONObject principal=new JSONObject(data);
                JSONObject main=new JSONObject(principal.getString("main"));
                JSONArray weather=new JSONArray(principal.getString("weather"));
                JSONObject sys=new JSONObject (principal.getString("sys"));
                for(int i=0;i<weather.length();i++){
                    JSONObject J=(JSONObject) weather.get(i);
                    singleParsed=J.getString("description");
                }
                dataParsed=
                        "Ciudad :"+principal.getString("name")+", "+sys.getString("country")+"\n"+
                        "Humedad :"+main.getString("humidity")+"%\n"+
                        "Temp. Minima :"+convertir(main.getString("temp_min"))+"°\n"+
                        "Temp. Maxima :"+convertir(main.getString("temp_max"))+"°\n"+
                        "Temp. Actual :"+convertir(main.getString("temp"))+"°\n"+
                        "Descripcion :"+singleParsed;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.txt.setText(this.dataParsed);
    }

    public String convertir(String f){
        Float c= (5*(Float.parseFloat(f)-32))/9;
        return c+"";
    }

}
