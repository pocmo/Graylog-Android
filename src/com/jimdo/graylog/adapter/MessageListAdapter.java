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
package com.jimdo.graylog.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jimdo.graylog.R;
import com.jimdo.graylog.model.Filter;
import com.jimdo.graylog.model.LogMessage;
import com.jimdo.graylog.model.Priority;
import com.jimdo.graylog.net.FetchMessagesTask;
import com.jimdo.graylog.net.UrlBuilder;

/**
 * Adapter for the List in LogsActivity
 * 
 * @author Sebastian Kaspari <s.kaspari@googlemail.com>
 */
public class MessageListAdapter extends BaseAdapter {
	public final static String TAG = "Graylog/LogListAdapter"; 
	
	private ArrayList<LogMessage> messages = new ArrayList<LogMessage>();
	private boolean updateFromServer = true;
	private boolean showLoadingItem = true;
	private Filter filter;
	private int categoryId = 0;
	
	/**
	 * Set Category
	 */
	public void setCategory(int categoryId)
	{
		this.categoryId = categoryId;
	}
	
	/**
	 * Set Filter
	 */
	public void setFilter(Filter filter)
	{
		this.filter = filter;
	}
	
	/**
	 * Get Filter
	 * 
	 * @return currently assigned filter
	 */
	public Filter getFilter()
	{
		return filter;
	}
	
	/**
	 * Get number of items
	 */
	public int getCount()
	{
		if (showLoadingItem) {
			return messages.size() + 1;
		} else {
			return messages.size();
		}
	}
	
	/**
	 * Get item at specified location
	 */
	public LogMessage getItem(int location)
	{
		if (location < messages.size()) {
			return messages.get(location);
		}
		return null;
	}
	
	/**
	 * Get id of item at specified location
	 */
	public long getItemId(int location)
	{
		if (location < messages.size()) {
			messages.get(location).getId();
		}
		return location;
	}
	
	/**
	 * Add ArrayList of messages to the existing messages
	 * 
	 * @param newMessages
	 */
	public void mergeWith(ArrayList<LogMessage> newMessages)
	{
		if (newMessages != null && newMessages.size() > 0) {
			for (LogMessage message : newMessages) {
				messages.add(message);
			}
			updateFromServer = true;
		} else {
			Log.d(TAG, "No new messages. Stopping updates..");
			showLoadingItem = false;
		}
		
		notifyDataSetChanged();
	}
	
	/**
	 * Refresh list from server
	 */
	public void refresh()
	{
		messages.clear();
		showLoadingItem = true;
		updateFromServer = true;
		notifyDataSetChanged();
	}
	
	/**
	 * Get View of a list element
	 */
	public View getView(int location, View v, ViewGroup p)
	{
		LayoutInflater inflater = (LayoutInflater) p.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		if (location == messages.size()) {
			if (updateFromServer) {
				FetchMessagesTask task = new FetchMessagesTask(this);

				if (categoryId == 0) {
					task.execute(UrlBuilder.getInstance().getMessagesUrl(messages.size(), 20, filter));					
				} else {
					task.execute(UrlBuilder.getInstance().getMessagesUrl(messages.size(), 20, filter, categoryId));
				}
				
				updateFromServer = false;
			}
			
			return inflater.inflate(R.layout.loading, null);
		}
		
		LogMessage message = messages.get(location);
		
		v = inflater.inflate(R.layout.message, null);

		TextView tv;
		
		tv = (TextView) v.findViewById(R.id.message);
		tv.setText(message.getText().length() > 140 ? message.getText().substring(0, 140) + " [..]" : message.getText());
		
		tv = (TextView) v.findViewById(R.id.footer);
		tv.setText(message.getRelativeTime() + " from " + message.getHost());
		
		tv = (TextView) v.findViewById(R.id.priority);
		tv.setText(Priority.getReadable(message.getPriority()));

		return v;
	}
}
