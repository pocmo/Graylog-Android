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

import java.util.ArrayList;

import android.os.AsyncTask;
import android.util.Log;

import com.jimdo.graylog.adapter.MessageListAdapter;
import com.jimdo.graylog.model.LogMessage;
import com.jimdo.graylog.model.ResponseDeserializer;

/**
 * Asynchronous Task to fetch log messages from the server
 * 
 * @author Sebastian Kaspari <s.kaspari@googlemail.com>
 */
public class FetchMessagesTask extends AsyncTask<String, Void, ArrayList<LogMessage>>
{
	public static final String TAG = "Graylog/FetchLogTask";
	
	private MessageListAdapter adapter;
	
	public FetchMessagesTask(MessageListAdapter adapter)
	{
		this.adapter = adapter;
	}
	
	@Override
	protected ArrayList<LogMessage> doInBackground(String... urls)
	{
		try {
			// Do Request....
			String response = Request.getInstance().execute(urls[0]);
			
			// Deserialize Messages
			return ResponseDeserializer.deserializeLogMessages(response);
		}
		catch (Exception e) {
			Log.d(TAG, "Exception: " + e.getMessage());
			
			return null;
		}
	}

	protected void onPostExecute(ArrayList<LogMessage> messages)
	{
		if (messages != null) {
			adapter.mergeWith(messages);
		}
	}
}
