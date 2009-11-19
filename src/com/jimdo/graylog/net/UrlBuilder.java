/*
Graylog - Android Client

Copyright 2009 Sebastian Kaspari

This file is part of Graylog (Android Client).

Graylog (Android Client) is free software: you can redistribute it
and/or modify it under the terms of the GNU General Public License
as published by the Free Software Foundation, either version 3 of
the License, or (at your option) any later version.

Graylog (Android Client) is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Graylog (Android Client). If not, see <http://www.gnu.org/licenses/>.
*/
package com.jimdo.graylog.net;

import com.jimdo.graylog.model.Filter;

/**
 * Helper class for creating API Urls
 * 
 * @author Sebastian Kaspari <s.kaspari@googlemail.com>
 */
public class UrlBuilder {
	public static final String TAG = "Graylog/UrlBuilder";
	
	private static UrlBuilder instance;
	private String baseUrl;
	
	/**
	 * Private constructor
	 */
	private UrlBuilder()
	{
	}
	
	/**
	 * Get UrlBuilder Instance
	 */
	public static UrlBuilder getInstance()	{
		if (instance == null) {
			instance = new UrlBuilder();
		}
		
		return instance;
	}
	
	/**
	 * Set Base URL to Graylog
	 * 
	 * @param baseUrl The URL to Graylog
	 */
	public void setBaseUrl(String baseUrl) {
		if (baseUrl.length() > 0 && baseUrl.charAt(baseUrl.length() - 1) !=  '/') {
			baseUrl = baseUrl + '/'; 
		}
		
		this.baseUrl = baseUrl;
	}
	
	/**
	 * Get Url for Ping
	 * 
	 * Ping just tests if there's a graylog instance on the server
	 * 
	 * @return The Url
	 */
	public String getPingUrl() {
		return baseUrl + "api/ping";
	}
	
	/**
	 * Get Url for Dashboard
	 * 
	 * @return The Url
	 */
	public String getDashboardUrl() {
		return baseUrl + "api/getdashboard";
	}
	
	/**
	 * Get Url for Log Messages
	 * 
	 * @param offset
	 * @param limit
	 * @return The Url
	 */
	public String getMessagesUrl(int offset, int limit, Filter filter) {
		return baseUrl + applyFilter(filter, "api/getmessages?offset=" + offset + "&limit=" + limit);
	}
	
	/**
	 * Get Url for Log Messages of a specific category
	 * 
	 * @param offset
	 * @param limit
	 * @param categoryId
	 * @return The Url
	 */
	public String getMessagesUrl(int offset, int limit, Filter filter, int categoryId)	{
		return getMessagesUrl(offset, limit, filter) + "&category=" + categoryId;
	}
	
	/**
	 * Get Url for Categories
	 * 
	 * @return The Url
	 */
	public String getCategoriesUrl() {
		return baseUrl + "api/getcategories";
	}
	
	/**
	 * Apply filter to Url
	 */
	private String applyFilter(Filter filter, String url) {
		if (filter != null) {
			if (!"".equals(filter.getHost())) {
				url += "&filter[host]=" + filter.getHost();
			}
			
			if (!"".equals(filter.getMessage())) {
				url += "&filter[message]=" + filter.getMessage();
			}
		}
		
		return url;
	}
}
