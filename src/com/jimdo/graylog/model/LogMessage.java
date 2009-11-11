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

import java.util.ArrayList;

/**
 * A Log Message of GrayLog
 * 
 * @author Sebastian Kaspari <s.kaspari@googlemail.com>
 */
public class LogMessage
{
	private LogEntry logentry;

	/**
	 * Get the deserialized LogEntry
	 * 
	 * @return the deserialized LogEntry object
	 */
	private LogEntry getLogEntry()
	{
		return logentry;
	}

	/**
	 * Set the deserialized LogEntry
	 * 
	 * This method is used by the JSONDeserializer 
	 * 
	 * @param logentry
	 */
	public void setLogEntry(LogEntry logentry)
	{
		this.logentry = logentry;
	}
}