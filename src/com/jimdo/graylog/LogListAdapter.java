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
package com.jimdo.graylog;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jimdo.graylog.model.LogMessage;

/**
 * Adapter for the List in LogsActivity
 * 
 * @author Sebastian Kaspari <s.kaspari@googlemail.com>
 */
public class LogListAdapter extends BaseAdapter {
	public final static String TAG = "Graylog/LogListAdapter"; 
	
	private ArrayList<LogMessage> messages = new ArrayList<LogMessage>();
	
	/**
	 * Get number of items
	 */
	public int getCount()
	{
		return messages.size() + 1;
	}
	
	/**
	 * Get item at specified location
	 */
	public Object getItem(int location)
	{
		return location;
	}
	
	/**
	 * Get id of item at specified location
	 */
	public long getItemId(int location)
	{
		return location;
	}
	
	/**
	 * Add ArrayList of messages to the existing messages
	 * 
	 * @param newMessages
	 */
	public void mergeWith(ArrayList<LogMessage> newMessages)
	{
		for (LogMessage message : newMessages) {
			messages.add(message);
		}
	}
	
	/**
	 * Get View of a list element
	 */
	public View getView(int location, View v, ViewGroup p)
	{
		LayoutInflater inflater = (LayoutInflater) p.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		if (location == messages.size()) {
			return inflater.inflate(R.layout.loading, null);
		}
		if (location == messages.size()) {
			// XXX: Load new items
		}
		
		LogMessage message = messages.get(location);
		
		v = inflater.inflate(R.layout.message, null);

		TextView tv;
		
		tv = (TextView) v.findViewById(R.id.text);
		tv.setText(message.getText().length() > 140 ? message.getText().substring(0, 139) : message.getText());
		
		tv = (TextView) v.findViewById(R.id.footer);
		tv.setText(message.getRelativeTime() + " from " + message.getHost());

		return v;
	}

}
