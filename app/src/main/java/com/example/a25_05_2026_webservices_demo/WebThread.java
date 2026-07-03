package com.example.a25_05_2026_webservices_demo;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WebThread extends Thread{

    @Override
    public void run() {
        super.run();

        try {
            URL url = new URL("https://dummyjson.com/comments");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            Log.e("tag",httpURLConnection.getContentType());
            Log.e("tag",httpURLConnection.getContent().toString());
            Log.e("tag",httpURLConnection.getContentLength() + "");
            Log.e("tag",String.valueOf(httpURLConnection.getResponseCode()));
            Log.e("tag",httpURLConnection.getResponseMessage());
            Log.e("tag",httpURLConnection.getRequestMethod());

            InputStream inputStream = httpURLConnection.getInputStream();
            StringBuffer responseBuffer = new StringBuffer();

            byte[] data = new byte[1024];
            int count = inputStream.read(data);

            while(count != -1){
                responseBuffer.append(new String(data,0,count));
                count = inputStream.read(data);
            }

           Log.e("tag",responseBuffer.toString());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
