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

public class LineApiUtils extends Thread {
	public static final String URL_LINE_API = "https://notify-api.line.me/api/notify";
	public static final String URL_OAUTH_TOKEN_API = "https://notify-bot.line.me/oauth/token";
	public static final String URL_OAUTH_AUTHORIZE_API = "https://notify-bot.line.me/oauth/authorize?";
	public static final String URL_LINE_MESSAGE_API = "https://api.line.me/v2/bot/message";
	public static final String REQUEST_TOKEN = "com.nightclub.main.RequestToken";
	public static final String SEND_MESSAGE_API = "com.nightclub.main.SendMessageApi";
	public static final String SEND_DATA = "com.nightclub.main.SendData";

	public static final String BATCH_ROOT_PATH = "C:\\Users\\thainightnav\\Desktop\\";
	// String path = "C:\\Developer\\";

	private String arg1;
	private String arg2;
	private String arg3;
	private String arg4;
	private String arg5;
//
//	private static HttpURLConnection preparedProperty(final String TOKEN)
//			throws MalformedURLException, IOException {
//		URL url = new URL(URL_LINE_API);
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		StringBuilder Authorization = new StringBuilder("Bearer ");
//		conn.setDoOutput(true);
//		conn.setDoInput(true);
//		conn.setInstanceFollowRedirects(false);
//		conn.setRequestMethod("POST");
//		conn.setRequestProperty("Content-Type",
//				"application/x-www-form-urlencoded");
//		conn.setRequestProperty("Authorization", Authorization.append(TOKEN)
//				.toString());
//		conn.setUseCaches(false);
//		return conn;
//	}
//
//	// set body parameter for http request
//	private static String preparedParameter(String message) {
//		StringBuilder urlParameters = new StringBuilder();
//		urlParameters.append("message=" + message);
//		return urlParameters.toString();
//	}

	// send data via http request
	public static int sendData(String message, String token) throws IOException {
//		String urlParameters = preparedParameter(message);
//		byte[] postData = urlParameters.getBytes("UTF-8");
//		int postDataLength = postData.length;
//		int statusCode = 500;
//		HttpURLConnection conn = preparedProperty(token);
//		conn.setRequestProperty("charset", "utf-8");
//		conn.setRequestProperty("Content-Length",
//				Integer.toString(postDataLength));
//		conn.setUseCaches(false);
//
//		OutputStream os = conn.getOutputStream();
//		os.write(postData);
//		os.flush();
//
//		if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
//			statusCode = conn.getResponseCode();
//			if (statusCode != 200) {
//				// throw new RuntimeException("Failed : HTTP error code : "
//				// + conn.getResponseCode());
//				System.out.println("Failed : HTTP error code : "
//						+ conn.getResponseCode());
//			}
//		}
//		BufferedReader br = new BufferedReader(new InputStreamReader(
//				(conn.getInputStream())));
//
//		String output;
//		System.out.println("Output from Server .... \n");
//		while ((output = br.readLine()) != null) {
//			System.out.println(output);
//		}
//
//		conn.disconnect();
		
		LineApiUtils thread = new LineApiUtils();
		thread.setArg1(SEND_DATA);
		thread.setArg2(message);
		thread.setArg3(token);
		thread.start();
		
		return 0;
	}

//	private static HttpURLConnection preparedPropertyForRequestToken()
//			throws MalformedURLException, IOException {
//		URL url = new URL(URL_OAUTH_TOKEN_API);
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setDoOutput(true);
//		conn.setDoInput(true);
//		conn.setInstanceFollowRedirects(false);
//		conn.setRequestMethod("POST");
//		conn.setRequestProperty("Content-Type",
//				"application/x-www-form-urlencoded");
//		conn.setUseCaches(false);
//		return conn;
//	}
//
//	// set body parameter for http request
//	private static String preparedParameterForRequestToken(String code,
//			String clientId, String clientSecret, String redirectUrl) {
//		StringBuilder urlParameters = new StringBuilder();
//		urlParameters.append("grant_type=authorization_code");
//		urlParameters.append("&code=" + code);
//		urlParameters.append("&redirect_uri=" + redirectUrl);
//		urlParameters.append("&client_id=" + clientId);
//		urlParameters.append("&client_secret=" + clientSecret);
//		return urlParameters.toString();
//	}
//
//	// request token via http request
//	public static String requestToken(String code, String clientId,
//			String clientSecret, String redirectUrl) throws IOException {
//		String urlParameters = preparedParameterForRequestToken(code, clientId,
//				clientSecret, redirectUrl);
//		byte[] postData = urlParameters.getBytes("UTF-8");
//		int postDataLength = postData.length;
//		int statusCode = 500;
//		HttpURLConnection conn = preparedPropertyForRequestToken();
//		conn.setRequestProperty("charset", "utf-8");
//		conn.setRequestProperty("Content-Length",
//				Integer.toString(postDataLength));
//		conn.setUseCaches(false);
//
//		OutputStream os = conn.getOutputStream();
//		os.write(postData);
//		os.flush();
//
//		if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
//			statusCode = conn.getResponseCode();
//			if (statusCode != 200) {
//				throw new RuntimeException("Failed : HTTP error code : "
//						+ conn.getResponseCode());
//			}
//		}
//		BufferedReader br = new BufferedReader(new InputStreamReader(
//				(conn.getInputStream())));
//
//		String output;
//		String data = "";
//		System.out.println("Output from Server .... \n");
//		while ((output = br.readLine()) != null) {
//			System.out.println(output);
//			data += output;
//		}
//		JsonObject json = new JsonParser().parse(data).getAsJsonObject();
//
//		conn.disconnect();
//		return json.get("access_token").getAsString();
//	}

	// set body parameter for http request
	public static String getUrlForRequestCode(String clientId,
			String redirectUrl) {
		StringBuilder urlParameters = new StringBuilder();
		urlParameters.append("response_type=code");
		urlParameters.append("&client_id=" + clientId);
		urlParameters.append("&redirect_uri=" + redirectUrl);
		urlParameters.append("&scope=notify");
		urlParameters.append("&state=mylinenotify");
		return URL_OAUTH_AUTHORIZE_API + urlParameters.toString();
	}

	// send data via http request
	public static int sendMessageApi(String message, String token, String to)
			throws IOException {
		LineApiUtils thread = new LineApiUtils();
		thread.setArg1(SEND_MESSAGE_API);
		thread.setArg2(message);
		thread.setArg3(token);
		thread.setArg4(to);
		thread.start();
		return 0;
	}
	
	// request token
	public static String requestToken(String code, String clientId, String clientSecret, String redirectUrl)
			throws IOException {
		ProcessBuilder processBuilder = null;
		try {
			processBuilder = new ProcessBuilder(
					BATCH_ROOT_PATH + "apache-tomcat-7.0.28\\batch\\LineService\\LineServiceBatch.bat",
					REQUEST_TOKEN, code, clientId, clientSecret, redirectUrl);
			Process process = processBuilder.start();
			StringBuilder output = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
			int exitVal = process.waitFor();
			if (exitVal == 0) {
				System.out.println(output);
				return output.toString().trim();
			} else {
				// abnormal...
				return "";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "";
	}

	public void run() {
		ProcessBuilder processBuilder = null;
		
//		String path = "C:\\Developer\\";
		if (SEND_MESSAGE_API.equals(getArg1())) {
			processBuilder = new ProcessBuilder(
					BATCH_ROOT_PATH + "apache-tomcat-7.0.28\\batch\\LineService\\LineServiceBatch.bat",
				SEND_MESSAGE_API, getArg2(), getArg3(), getArg4());
		} else if (SEND_DATA.equals(getArg1())) {
			processBuilder = new ProcessBuilder(
					BATCH_ROOT_PATH + "apache-tomcat-7.0.28\\batch\\LineService\\LineServiceBatch.bat",
					SEND_DATA, getArg2(), getArg3());
		}
		// Process process = Runtime.getRuntime().exec(
		// "cmd /c hello.bat", null, new File("C:\\Users\\mkyong\\"));

		try {
			Process process = processBuilder.start();
			StringBuilder output = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
			int exitVal = process.waitFor();
			if (exitVal == 0) {
				System.out.println(output);
			} else {
				// abnormal...
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getArg1() {
		return arg1;
	}

	public String getArg2() {
		return arg2;
	}

	public String getArg3() {
		return arg3;
	}
	
	public String getArg4() {
		return arg4;
	}
	
	public String getArg5() {
		return arg5;
	}

	public void setArg1(String arg1) {
		this.arg1 = arg1;
	}

	public void setArg2(String arg2) {
		this.arg2 = arg2;
	}
	
	public void setArg3(String arg3) {
		this.arg3 = arg3;
	}
	
	public void setArg4(String arg4) {
		this.arg4 = arg4;
	}
	
	public void setArg5(String arg5) {
		this.arg5 = arg5;
	}

	// private static HttpURLConnection preparedPropertyForMessageApi(final
	// String TOKEN)
	// throws MalformedURLException, IOException {
	// URL url = new URL(URL_LINE_MESSAGE_API + "/push");
	// HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	// StringBuilder Authorization = new StringBuilder("Bearer ");
	// conn.setDoOutput(true);
	// conn.setDoInput(true);
	// conn.setInstanceFollowRedirects(false);
	// conn.setRequestMethod("POST");
	// conn.setRequestProperty("Content-Type",
	// "application/json");
	// conn.setRequestProperty("Authorization", Authorization.append(TOKEN)
	// .toString());
	// conn.setUseCaches(false);
	// return conn;
	// }
	//
	// // set body parameter for http request
	// private static String preparedParameterForMessageApi(String message,
	// String to) {
	// StringBuilder urlParameters = new StringBuilder();
	// urlParameters.append("{\"messages\":[{\"type\":\"text\", \"text\":\"" +
	// message + "\"}],");
	// urlParameters.append("\"to\":\"" + "U6271bf37cf8784e414c25f5d1b9c91c5" +
	// "\"}");
	// System.out.println(urlParameters.toString());
	// return urlParameters.toString();
	// }
}
