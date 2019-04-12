package com.jxq.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Request {

	public static byte[] get(String url, Map<String, String> head) throws IOException {
		if(StringUtils.isBlank(url))return new byte[0];
		CloseableHttpClient httpclient = HttpClients.createDefault();
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		try {
			HttpGet httpget = new HttpGet(url);

			if (head != null) {
				for (String key : head.keySet()) {

					httpget.setHeader(key, head.get(key));

				}
			}

			System.out.println("Executing request " + httpget.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());

				// Get hold of the response entity
				HttpEntity entity = response.getEntity();

				// If the response does not enclose an entity, there is no need
				// to bother about connection release
				if (entity != null) {
					InputStream instream = entity.getContent();
					int i = -1;
					byte[] buffer = new byte[1024];
					try {
						while ((i = instream.read(buffer)) != -1) {
							outSteam.write(buffer, 0, i);
						}
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						instream.close();
					}

				}
			} finally {
				response.close();
				outSteam.close();
			}
			httpget.abort();
		} finally {
			httpclient.close();
		}
		return outSteam.toByteArray();
	}

	

}
