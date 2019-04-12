package com.jxq.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.mysql.jdbc.StringUtils;

public class PostRequest {

	// post请求
	public static String post(String url, Map<String, String> param, Map<String, String> head, String json) {

		String resp = "";
		HttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);

		for (String key : head.keySet()) {

			post.setHeader(key, head.get(key));

		}

		List<NameValuePair> params = new ArrayList<NameValuePair>();

		for (String key : param.keySet()) {

			params.add(new BasicNameValuePair(key, param.get(key)));

			// try {
			// post.setEntity(new UrlEncodedFormEntity(Arrays.asList(new
			// NameValuePair[]{new BasicNameValuePair(key, param.get(key))})));
			// } catch (UnsupportedEncodingException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }

		}
		if (!StringUtils.isEmptyOrWhitespaceOnly(json)) {

			StringEntity strEntit = new StringEntity(json, "utf-8");
			post.setEntity(strEntit);
		} else {

			try {
				post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		try {
			HttpResponse response = client.execute(post);
			resp = EntityUtils.toString(response.getEntity());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		post.abort();
		return resp;
	}

	// get请求
	public static String Get(String url, Map<String, String> head) throws ClientProtocolException, IOException {
		String resp = "";
		HttpClient client = HttpClients.createDefault();
		HttpGet requestGet = new HttpGet(url);

		for (String key : head.keySet()) {

			requestGet.setHeader(key, head.get(key));

		}

		HttpResponse response = client.execute(requestGet);
		resp = EntityUtils.toString(response.getEntity());
		requestGet.abort();
		return resp;
	}

	public static String Get(String url, Map<String, String> head, String ip, int port)
			throws ClientProtocolException, IOException {
		if (url.contains("https://")) {
//			enableSSL(client);
//			sslClient();
			ssl();
		}
		String resp = "";
//		HttpHost proxy = new HttpHost(ip, port, "http");
		HttpHost proxy = new HttpHost(ip, port, "https");
		RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(10000).setSocketTimeout(10000).setConnectionRequestTimeout(10000)
				.build();
		HttpClient client = HttpClients.createDefault();
		HttpGet requestGet = new HttpGet(url);
		requestGet.setConfig(config);
		for (String key : head.keySet()) {
			requestGet.setHeader(key, head.get(key));
		}

		HttpResponse response = client.execute(requestGet);
		resp = EntityUtils.toString(response.getEntity());
		requestGet.abort();
		return resp;
	}

	public static HttpResponse GetReturnHttpResponse(String url, Map<String, String> head)
			throws ClientProtocolException, IOException {
		HttpClient client = HttpClients.createDefault();
		HttpGet requestGet = new HttpGet(url);

		for (String key : head.keySet()) {

			requestGet.setHeader(key, head.get(key));

		}

		HttpResponse response = client.execute(requestGet);

		return response;
	}

	// 返回responseEntity
	public static HttpResponse postReturnEntity(String url, Map<String, String> param, Map<String, String> head) {

		HttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);

		for (String key : head.keySet()) {

			post.setHeader(key, head.get(key));

		}

		List<NameValuePair> params = new ArrayList<NameValuePair>();

		for (String key : param.keySet()) {

			params.add(new BasicNameValuePair(key, param.get(key)));

			// try {
			// post.setEntity(new UrlEncodedFormEntity(Arrays.asList(new
			// NameValuePair[]{new BasicNameValuePair(key, param.get(key))})));
			// } catch (UnsupportedEncodingException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }

		}
		HttpResponse response = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			response = client.execute(post);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	/**
	 * 访问https的网站
	 * 
	 * @param httpclient
	 */
	private static void enableSSL(HttpClient httpclient) {
		// 调用ssl
		try {
			SSLContext sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null, new TrustManager[] { truseAllManager }, null);
			SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslcontext,NoopHostnameVerifier.INSTANCE);
			// 创建Registry
            RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
                    .setExpectContinueEnabled(Boolean.TRUE).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM,AuthSchemes.DIGEST))
                    .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https",socketFactory).build();
         // 创建ConnectionManager，添加Connection配置信息
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            CloseableHttpClient closeableHttpClient = HttpClients.custom().setConnectionManager(connectionManager)
                    .setDefaultRequestConfig(requestConfig).build();
//            return closeableHttpClient;
		} catch (KeyManagementException ex) {
            throw new RuntimeException(ex);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
	}

	/**
	 * 重写验证方法，取消检测ssl
	 */
	private static TrustManager truseAllManager = new X509TrustManager() {

		public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
				throws CertificateException {

		}

		public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
				throws CertificateException {

		}

		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

	};
	
	/**
     * 在调用SSL之前需要重写验证方法，取消检测SSL
     * 创建ConnectionManager，添加Connection配置信息
     * @return HttpClient 支持https
     */
    private static HttpClient sslClient() {
        try {
            // 在调用SSL之前需要重写验证方法，取消检测SSL
            X509TrustManager trustManager = new X509TrustManager() {
            	public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
        				throws CertificateException {

        		}

        		public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
        				throws CertificateException {

        		}

        		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
        			return null;
        		}
            };
            SSLContext ctx = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);
            ctx.init(null, new TrustManager[] { trustManager }, null);
            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(ctx, NoopHostnameVerifier.INSTANCE);
            // 创建Registry
            RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
                    .setExpectContinueEnabled(Boolean.TRUE).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM,AuthSchemes.DIGEST))
                    .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https",socketFactory).build();
            // 创建ConnectionManager，添加Connection配置信息
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            CloseableHttpClient closeableHttpClient = HttpClients.custom().setConnectionManager(connectionManager)
                    .setDefaultRequestConfig(requestConfig).build();
            return closeableHttpClient;
        } catch (KeyManagementException ex) {
            throw new RuntimeException(ex);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private static void ssl() {
    	
    	try {
    		HttpsURLConnection.setDefaultHostnameVerifier(new NullHostNameVerifier());
        	SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
    	
    }
    
    static TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // TODO Auto-generated method stub
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // TODO Auto-generated method stub
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            // TODO Auto-generated method stub
            return null;
        }
    } };
}
