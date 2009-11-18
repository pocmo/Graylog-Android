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

/**
 * A Category of Graylog
 * 
 * @author Sebastian Kaspari <s.kaspari@googlemail.com>
 */
public class Category
{
	private int id;
	private String title;
	private String host;
	private String message;
	
	/**
	 * Get id of category
	 * 
	 * @return id
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * Set id of category
	 * 
	 * @param id id
	 */
	public void setId(int id)
	{
		this.id = id;
	}
	
	/**
	 * Get title of category
	 * 
	 * @return title
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * Set title of category
	 * 
	 * @param title title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	/**
	 * Get host of category (filter criteria)
	 * 
	 * @return hostname
	 */
	public String getHost()
	{
		return host;
	}
	
	/**
	 * Set host of category (filter criteria)
	 * 
	 * @param host hostname
	 */
	public void setHost(String host)
	{
		this.host = host;
	}
	
	/**
	 * Get filter message of category
	 * 
	 * @return filter message
	 */
	public String getMessage()
	{
		return message;
	}
	
	/**
	 * Set filter message of category
	 * 
	 * @param message filter message
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}
}
