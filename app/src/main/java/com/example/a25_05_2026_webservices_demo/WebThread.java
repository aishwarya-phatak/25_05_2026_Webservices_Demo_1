package com.example.a25_05_2026_webservices_demo;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

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

            JSONObject apiResponse = new JSONObject(responseBuffer.toString());
            Log.e("tag",apiResponse + "");

            JSONArray commentsJSONArray = new JSONArray();
            commentsJSONArray = apiResponse.getJSONArray("comments");

            int total = apiResponse.getInt("total");
            int skip = apiResponse.getInt("skip");
            int limit = apiResponse.getInt("limit");

            int length = commentsJSONArray.length();

            ArrayList<Comment> comments = new ArrayList<Comment>();

            for(int i  = 0;i<length - 1;i++) {
                JSONObject commentJSONObject = commentsJSONArray.getJSONObject(i);

                int commentId = commentJSONObject.getInt("id");
                String commentBody = commentJSONObject.getString("body");
                int postId = commentJSONObject.getInt("postId");
                int likes = commentJSONObject.getInt("likes");

                JSONObject userObject = commentJSONObject.getJSONObject("user");

                int userId = userObject.getInt("id");
                String username = userObject.getString("username");
                String fullName = userObject.getString("fullName");

                User newUser = new User(userId,
                        username,
                        fullName);

                Comment newComment = new Comment(commentId,
                        commentBody,
                        postId,
                        likes,
                        newUser);
                comments.add(newComment);

                for (Comment c : comments) {
                    Log.e("tag",c.toString());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
