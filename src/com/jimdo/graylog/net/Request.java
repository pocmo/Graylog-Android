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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Do an API Request via HTTP
 *
 * @author Sebastian Kaspari <s.kaspari@googlemail.com>
 */
public class Request
{
	private DefaultHttpClient httpClient = new DefaultHttpClient();
	
	/**
	 * Execute a request
	 * 
	 * @param uri
	 * @return Content of the response
	 */
	public String execute(String uri)
	{
		try {
			HttpGet getRequest = new HttpGet(uri);
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
				
				return sb.toString();
			}
		} catch(Exception e) {
			// will return null
		}
		
		return null;
	}
}
