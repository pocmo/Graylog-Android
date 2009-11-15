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

/**
 * Helper class for creating API Urls
 * 
 * @author Sebastian Kaspari <s.kaspari@googlemail.com>
 */
public class UrlBuilder {
	private String baseUrl;
	
	/**
	 * Create a new UrlBuilder with given Base Url
	 * 
	 * @param baseUrl The base Url to Graylog
	 */
	public UrlBuilder(String baseUrl) {
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
		return baseUrl + "ping";
	}
	
	/**
	 * Get Url for Dashboard
	 * 
	 * @return The Url
	 */
	public String getDashboardUrl() {
		return baseUrl + "dashboard/api";
	}
}
