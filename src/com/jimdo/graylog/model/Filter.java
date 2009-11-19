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
package com.jimdo.graylog.model;

import java.io.Serializable;

/**
 * A filter for log messages
 * 
 * @author Sebastian Kaspari <s.kaspari@googlemail.com>
 */
public class Filter implements Serializable {
	private static final long serialVersionUID = 1831657210357692826L;
	
	private String host;
	private String message;
	
	/**
	 * Get Hostname
	 * 
	 * @return hostname
	 */
	public String getHost()
	{
		return host;
	}

	/**
	 * Set Hostname
	 * 
	 * @param host hostname
	 */
	public void setHost(String host)
	{
		this.host = host;
	}
	
	/**
	 * Get message query
	 * 
	 * @return
	 */
	public String getMessage()
	{
		return message;
	}
	
	/**
	 * Set message query
	 * 
	 * @param message
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}
}
