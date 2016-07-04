package com.qcloud.common;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qcloud.utils.MD5;

/**
 * @brief 请求调用类
 * @author robinslsun
 */
public class Request {
	protected static String requestUrl = "";
	protected static String rawResponse = "";
	protected static String version = "SDK_JAVA_1.3";
	protected static int timeOut = 100;// 设置连接主机的超时时间，单位：毫秒，可以根据实际需求合理更改
										// timeOut的值。

	private static Logger logger = LoggerFactory.getLogger(Request.class);

	private static final Random random = new Random(System.currentTimeMillis());

	public static String getRequestUrl() {
		return requestUrl;
	}

	public static String getRawResponse() {
		return rawResponse;
	}

	public static String generateUrl(TreeMap<String, Object> params, String secretId, String secretKey, String requestMethod, String requestHost,
			String requestPath) throws UnsupportedEncodingException {
		initParam(params, secretId, secretKey, requestMethod, requestHost, requestPath);
		if (params.get("Action").toString().equals("MultipartUploadVodFile")) {
			String url = combineUrl(false, requestHost, requestPath);
			url = combineParmas2Url(url, params);
			return url;
		} else {
			String url = combineUrl(true, requestHost, requestPath);
			if (requestMethod.equals("GET")) {
				url = combineParmas2Url(url, params);
			}
			return url;
		}
	}

	public static String send(TreeMap<String, Object> params, String secretId, String secretKey, String requestMethod, String requestHost,
			String requestPath, File file) {
		initParam(params, secretId, secretKey, requestMethod, requestHost, requestPath);

		try {
			if (params.get("Action").toString().equals("MultipartUploadVodFile")) {
				String url = combineUrl(false, requestHost, requestPath);
				url = combineParmas2Url(url, params);
				logger.info(url);
				return sendMultipartUploadVodFileRequest(url, params, requestMethod, file);
			} else {
				String url = combineUrl(true, requestHost, requestPath);
				if (requestMethod.equals("GET")) {
					url = combineParmas2Url(url, params);
				}
				logger.info(url);
				return sendRequest(url, params, requestMethod, file);
			}
		} catch (UnsupportedEncodingException e) {
			String result = "{\"code\":-2300,\"location\":\"com.qcloud.Common.Request:129\",\"message\":\"api sdk throw exception! " + e.toString()
					+ "\"}";
			return result;
		}
	}

	private static void initParam(TreeMap<String, Object> params, String secretId, String secretKey, String requestMethod, String requestHost,
			String requestPath) {
		if (!params.containsKey("SecretId"))
			params.put("SecretId", secretId);

		if (!params.containsKey("Nonce"))
			params.put("Nonce", random.nextInt(java.lang.Integer.MAX_VALUE));

		if (!params.containsKey("Timestamp"))
			params.put("Timestamp", System.currentTimeMillis() / 1000);

		params.put("RequestClient", version);

		String plainText = Sign.makeSignPlainText(requestMethod, requestHost+requestPath, params);
		System.out.println(plainText);
		try {
			params.put("Signature", Sign.sign(plainText, secretKey));
		} catch (Exception e) {
			logger.error("Sign error", e);
		}
	}

	private static String combineUrl(boolean isHttps, String requestHost, String requestPath) {
		String http = isHttps ? "https://" : "http://";
		String url = http + requestHost + requestPath;
		return url;
	}

	private static String combineParmas2Url(String url, Map<String, Object> requestParams) throws UnsupportedEncodingException {
		try {
			StringBuilder sb = new StringBuilder(url);
			if (url.indexOf('?') > 0) {
				sb.append('&');
			} else {
				sb.append('?');
			}
			sb.append(getParamStr(requestParams));

			url = sb.toString();
			return url;
		} catch (UnsupportedEncodingException e) {
			logger.error("UnsupportedEncodingException", e);
			throw e;
		}
	}

	private static String sendRequest(String url, Map<String, Object> requestParams, String requestMethod, File file) {
		String result = "";
		BufferedReader in = null;
		try {
			requestUrl = url;
			URLConnection connection = getConnection(url);
			if (requestMethod.equals("POST")) {
				sendPostData(connection, requestParams, file);
			}
			connection.connect();

			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			result = getResponse(in);
		} catch (Exception e) {
			result = "{\"code\":-2700,\"location\":\"com.qcloud.Common.Request:225\",\"message\":\"api sdk throw exception! " + e.toString() + "\"}";
		} finally {
			// 使用finally块来关闭输入流
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				result = "{\"code\":-2800,\"location\":\"com.qcloud.Common.Request:234\",\"message\":\"api sdk throw exception! " + e2.toString()
						+ "\"}";
			}
		}
		rawResponse = result;
		return result;
	}

	private static String sendMultipartUploadVodFileRequest(String url, Map<String, Object> requestParams, String requestMethod, File file) {
		String result = "";
		BufferedReader in = null;
		try {
			requestUrl = url;
			System.out.println(url);
			URLConnection connection = getConnection(url);
			connection.setDoOutput(true);
			connection.setDoInput(true);

			long file_length = (Long) requestParams.get("fileSize");
			OutputStream out = new DataOutputStream(connection.getOutputStream());
			DataInputStream ins = new DataInputStream(new FileInputStream(file));
			int offset = ((Integer) requestParams.get("offset")).intValue();
			int dataSize = ((Integer) requestParams.get("dataSize")).intValue();
			if (offset >= file_length) {
				return "{\"code\":-3001,\"location\":\"com.qcloud.Common.Request:303\",\"message\":\"api sdk throw exception! offset larger than the size of file\"}";
			}
			ins.skipBytes(offset);
			int page = dataSize / 1024;
			int remainder = dataSize % 1024;
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			byte[] bufferOut2 = new byte[remainder];
			while (page != 0) {
				if ((bytes = ins.read(bufferOut)) != -1) {
					out.write(bufferOut, 0, bytes);
				}
				page = page - 1;
			}
			if ((bytes = ins.read(bufferOut2)) != -1) {
				out.write(bufferOut2, 0, bytes);
			}
			ins.close();
			out.flush();
			out.close();

			// 建立实际的连接
			connection.connect();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			result = getResponse(in);
		} catch (Exception e) {
			result = "{\"code\":-3000,\"location\":\"com.qcloud.Common.Request:345\",\"message\":\"api sdk throw exception! " + e.toString() + "\"}";
		} finally {
			// 使用finally块来关闭输入流
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				result = "{\"code\":-3003,\"location\":\"com.qcloud.Common.Request:354\",\"message\":\"api sdk throw exception! " + e2.toString()
						+ "\"}";
			}
		}
		rawResponse = result;
		return result;
	}

	private static String getResponse(BufferedReader in) throws IOException {
		String line;
		StringBuilder sb = new StringBuilder();
		while ((line = in.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}

	private static void sendPostData(URLConnection connection, Map<String, Object> requestParams, File file) throws IOException {
		String BOUNDARY = "---------------------------" + MD5.stringToMD5(String.valueOf(System.currentTimeMillis())).substring(0, 15);
		((HttpURLConnection) connection).setRequestMethod("POST");
		// 发送POST请求必须设置如下两行
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
		OutputStream out = new DataOutputStream(connection.getOutputStream());
		StringBuilder strBuf = new StringBuilder();
		for (String key : requestParams.keySet()) {
			strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
			strBuf.append("Content-Disposition: form-data; name=\"" + key + "\"\r\n\r\n");
			strBuf.append(requestParams.get(key));
		}
		out.write(strBuf.toString().getBytes());
		if (file != null) {
			String fileName = file.getName();
			String contentType = URLConnection.getFileNameMap().getContentTypeFor(file.getAbsolutePath());
			if (contentType == null) {
				contentType = "application/octet-stream";
	        }
			
			strBuf = new StringBuilder();
			strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
			strBuf.append("Content-Disposition: form-data; name=\"entityFile\"; filename=\"" + fileName + "\"\r\n");
			strBuf.append("Content-Type:" + contentType + "\r\n\r\n");

			out.write(strBuf.toString().getBytes());

			DataInputStream ins = new DataInputStream(new FileInputStream(file));
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = ins.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			ins.close();
		}
		byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
		out.write(endData);
		out.flush();
		out.close();
	}

	private static URLConnection getConnection(String url) throws IOException {
		URLConnection connection = null;
		URL realUrl = new URL(url);
		if (url.toLowerCase().startsWith("https")) {
			HttpsURLConnection httpsConn = (HttpsURLConnection) realUrl.openConnection();

			httpsConn.setHostnameVerifier(new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
			connection = httpsConn;
		} else {
			connection = realUrl.openConnection();
		}

		// 设置通用的请求属性
		connection.setRequestProperty("accept", "*/*");
		connection.setRequestProperty("connection", "Keep-Alive");
		connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		// 设置链接主机超时时间
		connection.setConnectTimeout(timeOut);
		return connection;
	}

	private static String getParamStr(Map<String, Object> requestParams) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		for (String key : requestParams.keySet()) {
			if (sb.length() != 0) {
				sb.append('&');
			}
			sb.append(key).append('=').append(URLEncoder.encode(requestParams.get(key).toString(), "utf-8"));
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		//File file = new File("d:\\exhibition-daily.log");
		File file = new File("d:\\test.txt");
		String contentType = URLConnection.getFileNameMap().getContentTypeFor(file.getAbsolutePath());
		System.out.println(contentType);
	}
}
