package com.nightclub.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LineApiUtils {
	public static final String URL_LINE_API = "https://notify-api.line.me/api/notify";
	public static final String URL_OAUTH_TOKEN_API = "https://notify-bot.line.me/oauth/token";
	public static final String URL_OAUTH_AUTHORIZE_API = "https://notify-bot.line.me/oauth/authorize?";

	private static HttpURLConnection preparedProperty(final String TOKEN)
			throws MalformedURLException, IOException {
		URL url = new URL(URL_LINE_API);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		StringBuilder Authorization = new StringBuilder("Bearer ");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setInstanceFollowRedirects(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		conn.setRequestProperty("Authorization", Authorization.append(TOKEN)
				.toString());
		conn.setUseCaches(false);
		return conn;
	}

	// set body parameter for http request
	private static String preparedParameter(String message) {
		StringBuilder urlParameters = new StringBuilder();
		urlParameters.append("message=" + message);
		return urlParameters.toString();
	}

	// send data via http request
	public static int sendData(String message, String token) throws IOException {
        String urlParameters = preparedParameter(message);
        byte[] postData = urlParameters.getBytes("UTF-8");
        int postDataLength = postData.length;
        int statusCode = 500;
        HttpURLConnection conn = preparedProperty(token);
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setUseCaches(false);
        
        OutputStream os = conn.getOutputStream();
		os.write(postData);
		os.flush();

        if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
             statusCode = conn.getResponseCode();
            if (statusCode != 200) {
//                throw new RuntimeException("Failed : HTTP error code : "
//                        + conn.getResponseCode());
            	System.out.println("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
        }
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}
        
        conn.disconnect();
        return statusCode;
    }
	
	private static HttpURLConnection preparedPropertyForRequestToken()
			throws MalformedURLException, IOException {
		URL url = new URL(URL_OAUTH_TOKEN_API);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setInstanceFollowRedirects(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		conn.setUseCaches(false);
		return conn;
	}

	// set body parameter for http request
	private static String preparedParameterForRequestToken(String code, String clientId, String clientSecret, String redirectUrl) {
		StringBuilder urlParameters = new StringBuilder();
		urlParameters.append("grant_type=authorization_code");
		urlParameters.append("&code=" + code);
		urlParameters.append("&redirect_uri=" + redirectUrl);
		urlParameters.append("&client_id=" + clientId);
		urlParameters.append("&client_secret=" + clientSecret);
		return urlParameters.toString();
	}

	// request token via http request
	public static String requestToken(String code, String clientId, String clientSecret, String redirectUrl) throws IOException {
        String urlParameters = preparedParameterForRequestToken(code, clientId, clientSecret, redirectUrl);
        byte[] postData = urlParameters.getBytes("UTF-8");
        int postDataLength = postData.length;
        int statusCode = 500;
        HttpURLConnection conn = preparedPropertyForRequestToken();
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setUseCaches(false);
        
        OutputStream os = conn.getOutputStream();
		os.write(postData);
		os.flush();

        if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
             statusCode = conn.getResponseCode();
            if (statusCode != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
        }
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

		String output;
		String data = "";
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
			data += output;
		}
		JsonObject json = new JsonParser().parse(data).getAsJsonObject();
        
        conn.disconnect();
        return json.get("access_token").getAsString();
    }
	
	// set body parameter for http request
	public static String getUrlForRequestCode(String clientId, String redirectUrl) {
		StringBuilder urlParameters = new StringBuilder();
		urlParameters.append("response_type=code");
		urlParameters.append("&client_id=" + clientId);
		urlParameters.append("&redirect_uri=" + redirectUrl);
		urlParameters.append("&scope=notify");
		urlParameters.append("&state=mylinenotify");
		return URL_OAUTH_AUTHORIZE_API + urlParameters.toString();
	}
}
