package com.jpark.memowithnodejs;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by JH on 2017-03-20.
 */

public class Remote {

    public static String postData(String siteUrl, String data) {
        String result = "";
        if(!siteUrl.startsWith("http")){
           siteUrl = "http://"+siteUrl;
        }
        try {
            URL url = new URL(siteUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST"); // GET : 데이터 요청시 사용하는 방식

            // post 처리일 경우만 ------------
            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();

            os.write(data.getBytes());
            os.flush();
            os.close();

            int responseCode = connection.getResponseCode();
            if(responseCode ==HttpURLConnection.HTTP_OK){
                // 연결로 부터 스트림을 얻고, 버퍼래퍼로 감싼다.
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder temp = new StringBuilder();
                String lineOfdata = "";
                // 번복문을 돌며넛 버퍼의 데이터를 읽어온다.
                while((lineOfdata = br.readLine()) != null) {
                    temp.append(lineOfdata);
                }
                return result.toString();
            } else {
                Log.e("HTTPConnection","Error Code "+ responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
