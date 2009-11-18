/*
Yaaic - Yet Another Android IRC Client

Copyright 2009 Sebastian Kaspari

This file is part of Yaaic.

Yaaic is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Yaaic is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Yaaic.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.jimdo.graylog.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

/**
 * Do an API Request via HTTP
 *
 * @author Sebastian Kaspari <s.kaspari@googlemail.com>
 */
public class Request
{
	private static final String TAG = "Graylog/Request";
	private static Request instance;
	
	private DefaultHttpClient httpClient;
	
	
	/**
	 * Private constructor
	 */
	private Request()
	{
		this.httpClient = new DefaultHttpClient();
	}
	
	/**
	 * Create a new Request Instance
	 */
	public static Request createInstance()
	{
		return instance = new Request();
	}
	
	/**
	 * Get Request Instance
	 * 
	 * @return
	 */
	public static Request getInstance()
	{
		if (instance == null) {
			instance = createInstance();
		}
		
		return instance;
	}
	
	/**
	 * Set Credentials for HTTPAuth
	 */
	public void setHttpAuth(String username, String password) {
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
		
		BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, credentials);
        
        httpClient.setCredentialsProvider(credentialsProvider); 
	}
	
	/**
	 * Execute a request
	 * 
	 * @param url
	 * @return Content of the response
	 */
	public String execute(String url)
	{
		Log.d(TAG, "Request to " + url);
		
		try {
			HttpGet getRequest = new HttpGet(url);
			HttpResponse response = (HttpResponse) httpClient.execute(getRequest);

			HttpEntity entity = response.getEntity();
			
			if (entity != null) {
				InputStream stream = entity.getContent();
				Header contentEncoding = response.getFirstHeader("Content-Encoding");
				if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
					stream = new GZIPInputStream(stream);
				}
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
				
				StringBuffer sb = new StringBuffer();
				
				String line = null;
				
				while (null != (line = reader.readLine())) {
					sb.append(line + "\n");
				}
				
				//Log.d(TAG, "Response: " + sb);
				
				return sb.toString().trim();
			}
		} catch(Exception e) {
			Log.d(TAG, "Exception: " + e.getMessage());
		}
		
		return null;
	}
}
