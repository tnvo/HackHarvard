package com.example.nvlad.remoteforyun;

import android.os.AsyncTask;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Forward extends AsyncTask<String, Void, Forward>
{
    protected Forward doInBackground(String... urls)
    {
        URL url;
        HttpURLConnection urlConnection = null;
        try
        {
            //url = new URL("http://192.168.240.1/arduino/digital/13/500");
            url = new URL("http://google.ca");

            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);

            int data = isw.read();
            while (data != -1)
            {
                char current = (char) data;
                data = isw.read();
                System.out.print(current);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                urlConnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace(); //If you want further info on failure...
            }
        }
        return null;
    }
}