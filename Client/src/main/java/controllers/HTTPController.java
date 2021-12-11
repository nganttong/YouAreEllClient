package controllers;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HTTPController {
    //going to be generic to ids, messages, and all that
    //methods for gets, posts, puts
    private final static String rootURL = "http://zipcode.rocks:8085";

    public static String getUrl(String uri) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        try {
            HttpGet request = new HttpGet(rootURL + uri);

            CloseableHttpResponse response = httpClient.execute(request);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // return it as a String
                result = EntityUtils.toString(entity);
            }
            response.close();
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}