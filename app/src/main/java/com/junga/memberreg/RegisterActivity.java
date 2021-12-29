package com.junga.memberreg;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

//generic <>
public class RegisterActivity extends AsyncTask<String, Void, String> {
    String sendMsg, receiveMsg;

    // (String.. strings) : String types variable strings, strings[0], strings[1], ....
    @Override
    protected String doInBackground(String... strings) {

        try {
            // if you use here toast.makeText(...) error okay?
            String str;

            URL url = new URL("http://192.168.1.19:9455/dbtest/androidDB.jsp");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");

            //GET type
            sendMsg = "id= " + strings[0] + "&pw=" + strings[1];

            //sending data
            osw.write(sendMsg);
            osw.flush();

            //jsp and Communication connect success
            if(conn.getResponseCode() == conn.HTTP_OK){
                InputStreamReader temp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(temp);
                StringBuffer buffer = new StringBuffer();

                while((str = reader.readLine()) != null){
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
            }else {
                // Communication false
                receiveMsg ="jsp and Communication error";
            }


        } catch (IOException ie){
            ie.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        return receiveMsg;

    }



}
