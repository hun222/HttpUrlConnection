package com.hoonyeee.android.httpurlconnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Remote {

    public static String getScreen(String urlString){
        String result = "";

        try {
            //  1. url에 프로토콜 더해주기
            if (!urlString.startsWith("http")) {
                urlString = "http://"+urlString;
            }
            //  2. URL 객체 만들기
            URL url = new URL(urlString);
            //  3. Http connection 만들기
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //  4. 연결 옵션 설정
            connection.setRequestMethod("GET");
            //  5. 처리 결과 꺼내기
            int resCode = connection.getResponseCode();
            //  6. 처리 결과에 따른 분기처리
            if (resCode == HttpURLConnection.HTTP_OK) {
                //  6.1 연결된 스트림을 꺼내서 버퍼에 담아준다. -> 성능향상을 위해
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                //  6.2 반복문을 돌면서
                while((line = br.readLine()) !=null){
                    result = result + line +"\n";
                }
            } else {
                result = "Error Code:" + resCode;
            }
        }catch (Exception e){
            result = e.getMessage();
        }
        return result;
    }
}
