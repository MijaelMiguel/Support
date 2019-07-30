package com.proyecto.support.Controller;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class UsuarioController {

    //"http://10.0.2.2:83/Slim2.6.2/Usuario";
    String strURL ="http://192.168.137.1:8080/ServicesSupport/Usuario";
    URL url = null;
    HttpURLConnection httpClient = null;
    OutputStreamWriter out = null;
    JSONObject res = null;
    //Constructor

    public UsuarioController() {
    }

    public JSONObject consumo(String u,
                              String m,
                              JSONObject j){
        try{
            url = new URL(u);
            httpClient =(HttpURLConnection)
                    url.openConnection();

            httpClient.setRequestProperty("Accept",
                    "application/json");
            httpClient.setUseCaches(false);
            httpClient.setConnectTimeout(10000);//10s
            httpClient.setReadTimeout(15000);//15s

            switch (m){
                case "POST":
                    httpClient.setRequestMethod("POST");
                    httpClient.setDoOutput(true);//post

                    out =
                            new OutputStreamWriter(
                                    httpClient.getOutputStream(),"UTF-8");
                    out.write(j.toString());
                    out.flush();
                    out.close();
                    break;
                case "GET":
                    httpClient.setRequestMethod("GET");
                    break;
                case "PUT":
                    httpClient.setRequestMethod("PUT");

                    out =
                            new OutputStreamWriter(
                                    httpClient.getOutputStream(),"UTF-8");
                    out.write(j.toString());
                    out.flush();
                    out.close();

                    break;
                case "DELETE":
                    httpClient.setRequestMethod("DELETE");
                    break;
            }
            InputStreamReader inp =
                    new InputStreamReader(httpClient.getInputStream(),
                            "UTF-8");
            BufferedReader buf = new BufferedReader(inp);

            String linea;
            StringBuffer stb = new StringBuffer();
            while((linea = buf.readLine()) !=null){
                stb.append(linea);
            }
            Log.i("IDAT",stb.toString());
            if(httpClient.getResponseCode() == HttpURLConnection.HTTP_OK){
                res = new JSONObject(stb.toString());
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return res;
    }
}
