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

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * A Log Message of GrayLog
 * 
 * @author Sebastian Kaspari <s.kaspari@googlemail.com>
 */
public class LogMessage
{
	private int id;
	private String host;
	private int priority;
	private String text;
	private String recievedAt;

	/**
	 * Get the Id of the log message 
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * Get text of the log message
	 * 
	 * @return the log message text
	 */
	public String getText()
	{
		return text;
	}
	
	/**
	 * Get the host of the log message
	 *  
	 * @return hostname
	 */
	public String getHost()
	{
		return host;
	}
	
	/**
	 * Get priority of the log message
	 * 
	 * @return priority, see constants in Priority.*
	 */
	public int getPriority()
	{
		return priority;
	}
	
	/**
	 * Get time the message was recieved
	 * 
	 * @return time, like 2009-09-08T12:39:17+02:00
	 */
	public String getRecievedAt()
	{
		return recievedAt;
	}
	
	/**
	 * Set unique id of log message
	 * 
	 * @param id
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * Set host of the log message
	 * 
	 * @param host hostname
	 */
	public void setHost(String host)
	{
		this.host = host;
	}

	/**
	 * Set priority
	 * 
	 * @param priority see constants in Priority.*
	 */
	public void setPriority(int priority)
	{
		this.priority = priority;
	}

	/**
	 * Set text of log message
	 * 
	 * @param text
	 */
	public void setText(String text)
	{
		this.text = text;
	}

	/**
	 * Set time the message was recieved
	 * 
	 * @param recievedAt time, like 2009-09-08T12:39:17+02:00 
	 */
	public void setRecievedAt(String recievedAt)
	{
		this.recievedAt = recievedAt;
	}

	/**
	 * Get relative time of log message
	 * 
	 * @return String relative time like 5 days ago
	 */
	public String getRelativeTime()
	{
		try {
			long delta = new Date().getTime() / 1000 - new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(recievedAt).getTime() / 1000;
			
			if (delta < 60) {
				return "less than a minute ago";
			} else if (delta < 120) {
				return "about a minute ago";
			} else if (delta < 3600) {
				return ((int) (delta / 60)) + " minutes ago";
			} else if (delta < 7200) {
				return "about an hour ago";
			} else if (delta < 86400) {
				return ((int) (delta / 3600)) + " hours ago";
			} else if (delta < 172800) {
				return "about one day ago";
			} else {
				return ((int) (delta / 86400)) +  " days ago";
			}
		}
		catch (Exception e) {
			return recievedAt;
		}
	}
}
