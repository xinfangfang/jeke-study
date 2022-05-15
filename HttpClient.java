package com.mzdata.tradeservice.pos.controller.v2;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpClient {
    public static void main(String[] args) throws Exception {
        try {
            String url = "http://localhost:8801";
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
            CloseableHttpClient httpClient = HttpClients.createDefault();
            
	    HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), StandardCharsets.UTF_8));
            
	    String line = null;
	    StringBuffer responseSB = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                responseSB.append(line);
            }
            reader.close();

            httpClient.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
