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

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

/**
 * Deserializer for Responses (JSON)
 * 
 * @author Sebastian Kaspari <s.kaspari@googlemail.com>
 */
public class ResponseDeserializer {
	public static final String TAG = "Graylog/ResponseDeserializer";
	
	/**
	 * Deserializes log message from JSON
	 * 
	 * @param json Serialized log messages (JSON)
	 * 
	 * @return List of deserialized log messages
	 */
	public static ArrayList<LogMessage> deserializeLogMessages(String json) {
		try {
			ArrayList<LogMessage> messages = new ArrayList<LogMessage>();
			JSONArray array = new JSONArray(json);

			for (int i = 0; i < array.length(); i++) {
				JSONObject object  = array.getJSONObject(i);
				messages.add(deserializeMessage(object));
			}
			
			return messages;
		} catch(Exception e) {
			Log.d(TAG, "Exception: " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * Deserialize dashboard from JSON
	 * 
	 * @param json Serialized dashboard (JSON)
	 * 
	 * @return deserialized Dashboard
	 */
	public static Dashboard deserializeDashboard(String json) {
		try {
			JSONObject object = new JSONObject(json);
			
			Dashboard dashboard = new Dashboard();
			dashboard.setTimeSpan(object.getInt("timespan"));
			dashboard.setLastMessage(deserializeMessage(object.getJSONObject("last_message")));
			dashboard.setMessages(object.getInt("messages"));
			dashboard.setStatus(object.getString("status"));

			return dashboard;
		} catch(Exception e) {
			Log.d(TAG, "Exception: " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * Deserialize categories from JSON
	 * 
	 * @param json Serialized categories (JSON)
	 * 
	 * @return List of deserialized categories
	 */
	public static ArrayList<Category> deserializeCategories(String json)
	{
		try {
			ArrayList<Category> categories = new ArrayList<Category>();
			JSONArray array = new JSONArray(json);

			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i).getJSONObject("category");
				Category category = new Category();
				
				category.setId(object.getInt("id"));
				category.setHost(object.getString("filter_host"));
				category.setTitle(object.getString("title"));
				category.setMessage(object.getString("filter_message"));
				
				categories.add(category);
			}
			
			return categories;
		} catch(Exception e) {
			Log.d(TAG, "Exception: " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * Deserialize a single log message object
	 * 
	 * @param object json node to deserialize
	 * @return
	 */
	private static LogMessage deserializeMessage(JSONObject object)
	{
		try {
			LogMessage message = new LogMessage();
			
			object = object.getJSONObject("logentry");
			
			message.setId(object.getInt("ID"));
			message.setHost(object.getString("FromHost"));
			message.setPriority(object.getInt("Priority"));
			message.setRecievedAt(object.getString("ReceivedAt"));
			message.setText(object.getString("Message"));
			
			return message;
		}
		catch(Exception e) {
			Log.d(TAG, "Exception: " + e.getMessage());
			return null;
		}
	}
}
