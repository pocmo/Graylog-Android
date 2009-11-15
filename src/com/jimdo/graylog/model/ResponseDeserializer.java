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

import flexjson.JSONDeserializer;

/**
 * Deserializer for Responses (JSON)
 * 
 * @author Sebastian Kaspari <s.kaspari@googlemail.com>
 */
public class ResponseDeserializer {
	/**
	 * Deserializes log message from JSON
	 * 
	 * @param json Serialized log messages (JSON)
	 * 
	 * @return List of deserialized log messages
	 */
	public static ArrayList<LogMessage> deserializeLogMessages(String json) {
		JSONDeserializer<ArrayList<LogMessage>> deserializer = new JSONDeserializer<ArrayList<LogMessage>>();
		return deserializer.deserialize(json);		
	}
	
	/**
	 * Deserialize dashboard from JSON
	 * 
	 * @param json Serialized dashboard (JSON)
	 * 
	 * @return deserialized Dashboard
	 */
	public static Dashboard deserializeDashboard(String json) {
		JSONDeserializer<Dashboard> deserializer = new JSONDeserializer<Dashboard>();
		return deserializer.deserialize(json);
	}
}
