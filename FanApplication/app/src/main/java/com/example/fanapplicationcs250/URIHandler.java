package com.example.fanapplicationcs250;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class URIHandler {
    public static final String hostName = "10.0.2.2:8085"; // Use a temporary loopback to localhost

    public static String doGet(String uri,String failure) {
        InputStream is = null;

        try {
            Log.d("Survey Application","GET uri: " + uri);
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            if (response != 200)
                return failure;

            ByteArrayOutputStream sink = new ByteArrayOutputStream();
            copy(conn.getInputStream(), sink, 3000);
            byte[] buffer = sink.toByteArray();
            String result = new String(buffer, "UTF8");
            Log.d("Survey Application","Received: " + result);
            return result;

        } catch(Exception ex) {
            Log.d("Survey Application","Exception in doGet: "+ex.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                }
            }
        }
        return failure;
    }

    public static String doPost(String uri, String data) {
        InputStream is = null;

        try {
            Log.d("Survey Application","Post uri: " + uri);
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            Log.d("Survey Application","Posted: " + data);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            OutputStream os = conn.getOutputStream();
            os.write(data.getBytes());
            os.flush();

            // Starts the query
            conn.connect();

            is = conn.getInputStream();
            // Read the response as an array of char
            Reader reader = null;
            reader = new InputStreamReader(is, "UTF-8");
            char[] buffer = new char[conn.getContentLength()];
            reader.read(buffer);
            // Convert the array of chars to a String and return that
            String result = new String(buffer);
            Log.d("AndroidMail","Received: " + result);
            return result;
        } catch(Exception ex) {
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                }
            }
        }
        return "";
    }


    /* Helper method to assist with reading chunked data. */
    private static void copy(InputStream in, OutputStream out , int bufferSize)
            throws IOException
    {
        // Read bytes and write to destination until eof
        byte[] buf = new byte[bufferSize];
        int len = 0;
        while ((len = in.read(buf)) >= 0)
        {
            out.write(buf, 0, len);
        }
    }
}


