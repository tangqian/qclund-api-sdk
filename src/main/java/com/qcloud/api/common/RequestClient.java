package com.qcloud.api.common;

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

import com.qcloud.common.Sign;
import com.qcloud.utils.MD5;

/**
 * @brief 请求调用客户端类
 * @author robinslsun
 */
public final class RequestClient {

	private static final Logger logger = LoggerFactory.getLogger(RequestClient.class);
	private static final String VERSION = "SDK_JAVA_1.3";
	private static final Random RANDOM = new Random(System.currentTimeMillis());
	private static final int PAGE_SIZE = 4 * 1024;//每次写入页面大小
	private final int timeOut;// 设置连接主机的超时时间，单位：毫秒，可以根据实际需求合理更改timeOut的值。

	private IdentityConfig identity;

	private String requestUrl;

	private String rawResponse;
	
	public RequestClient(IdentityConfig identity){
		this(identity, 1000);
	}

	public RequestClient(IdentityConfig identity, int timeOut) {
		this.identity = identity;
		this.timeOut = timeOut;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public String getRawResponse() {
		return rawResponse;
	}

	public String send(String requestMethod, String requestUrl, TreeMap<String, Object> params, File file) {
		initParam(requestMethod, requestUrl, params);
		try {
			if (params.get("Action").toString().equals("MultipartUploadVodFile")) {
				String url = combineHttpUrl(requestUrl);
				url = combineParmas2Url(url, params);
				return sendMultipartUploadVodFileRequest(requestMethod, url, params, file);
			} else {
				String url = combineHttpsUrl(requestUrl);
				if (requestMethod.equals("GET")) {
					url = combineParmas2Url(url, params);
				}
				return sendRequest(requestMethod, url, params, file);
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("com.qcloud.Common.Request:129", e);
			String result = "{\"code\":-2300,\"location\":\"com.qcloud.Common.Request:129\",\"message\":\"api sdk throw exception! " + e.toString()
					+ "\"}";
			return result;
		}
	}

	private void initParam(String requestMethod, String requestUrl, TreeMap<String, Object> params) {
		if (!params.containsKey("SecretId"))
			params.put("SecretId", identity.getSecretId());

		if (!params.containsKey("Nonce"))
			params.put("Nonce", RANDOM.nextInt(java.lang.Integer.MAX_VALUE));

		if (!params.containsKey("Timestamp"))
			params.put("Timestamp", System.currentTimeMillis() / 1000);

		params.put("RequestClient", VERSION);

		String plainText = Sign.makeSignPlainText(requestMethod, requestUrl, params);
		System.out.println(plainText);
		try {
			params.put("Signature", Sign.sign(plainText, identity.getSecretKey()));
		} catch (Exception e) {
			logger.error("Sign error", e);
		}
	}

	private static String combineHttpUrl(String requestUrl) {
		return "http://" + requestUrl;
	}

	private static String combineHttpsUrl(String requestUrl) {
		return "https://" + requestUrl;
	}

	private static String combineParmas2Url(String url, Map<String, Object> requestParams) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder(url);
		if (url.indexOf('?') > 0) {
			sb.append('&');
		} else {
			sb.append('?');
		}
		sb.append(getParamStr(requestParams));

		url = sb.toString();
		return url;
	}

	private String sendRequest(String requestMethod, String url, Map<String, Object> requestParams, File file) {
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
			logger.error("com.qcloud.Common.Request:225", e);
			result = "{\"code\":-2700,\"location\":\"com.qcloud.Common.Request:225\",\"message\":\"api sdk throw exception! " + e.toString() + "\"}";
		} finally {
			// 使用finally块来关闭输入流
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
			}
		}
		rawResponse = result;
		return result;
	}

	private String sendMultipartUploadVodFileRequest(String requestMethod, String url, Map<String, Object> requestParams, File file) {
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
			if (offset >= file_length) {
				return "{\"code\":-3001,\"location\":\"com.qcloud.Common.Request:303\",\"message\":\"api sdk throw exception! offset larger than the size of file\"}";
			}
			ins.skipBytes(offset);
			System.out.println("----start upload--------");
			System.out.println("offset=" + offset);
			int dataSize = ((Integer) requestParams.get("dataSize")).intValue();
			System.out.println("dataSize=" + dataSize);
			
			writeData(ins, out, dataSize);
			
			ins.close();
			out.flush();
			out.close();

			// 建立实际的连接
			connection.connect();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			result = getResponse(in);
		} catch (Exception e) {
			System.out.println(e);
			logger.error("com.qcloud.Common.Request:345", e);
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

	private void writeData(DataInputStream ins, OutputStream out, int dataSize) throws IOException {
		int page = dataSize / PAGE_SIZE;
		int remainder = dataSize % PAGE_SIZE;
		int bytes = 0;
		byte[] bufferOut = new byte[PAGE_SIZE];
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

	private URLConnection getConnection(String url) throws IOException {
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
}
