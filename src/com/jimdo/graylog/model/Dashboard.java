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
 * A Dashboard as Graylog gives it to us
 * 
 * @author Sebastian Kaspari <s.kaspari@googlemail.com>
 */
public class Dashboard
{
	private int timeSpan;
	private LogMessage lastMessage;
	private int messages;
	private String status;
	
	/**
	 * Get time span of messages counter
	 * 
	 * @return time span in minutes
	 */
	public int getTimeSpan()
	{
		return timeSpan;
	}
	
	/**
	 * Set time span
	 * 
	 * @param timeSpan in minutes
	 */
	public void setTimeSpan(int timeSpan)
	{
		this.timeSpan = timeSpan;
	}
	
	/**
	 * Get last log message
	 * 
	 * @return the last message
	 */
	public LogMessage getLastMessage()
	{
		return lastMessage;
	}
	
	/**
	 * Set the last message
	 * 
	 * @param lastMessage
	 */
	public void setLastMessage(LogMessage lastMessage)
	{
		this.lastMessage = lastMessage;
	}
	
	/**
	 * Get number of messages in timespan
	 * 
	 * @return number of messages
	 */
	public int getMessages()
	{
		return messages;
	}
	
	/**
	 * Set number of messages in timespan
	 * 
	 * @param messages
	 */
	public void setMessages(int messages)
	{
		this.messages = messages;
	}
	
	/**
	 * Get status
	 * 
	 * default: "success"
	 * 
	 * @return the status
	 */
	public String getStatus()
	{
		return status;
	}
	
	/**
	 * Set status
	 * 
	 * @param status the status
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}
}
