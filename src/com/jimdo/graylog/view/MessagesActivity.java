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
package com.jimdo.graylog.view;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.jimdo.graylog.R;
import com.jimdo.graylog.adapter.MessageListAdapter;
import com.jimdo.graylog.model.Filter;
import com.jimdo.graylog.model.LogMessage;

/**
 * Shows a list of log messages
 * 
 * @author Sebastian Kaspari <s.kaspari@googlemail.com>
 */
public class MessagesActivity extends ListActivity {
	public static final String TAG = "Graylog/MessagesActivity";
	public static final int SET_FILTER = 0;
	
	private MessageListAdapter adapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        adapter = new MessageListAdapter();

        // Use Category if given
        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey("category_id")) {
	        int categoryId = getIntent().getExtras().getInt("category_id", 0);
	        if (categoryId != 0) {
	        	adapter.setCategory(categoryId);
	        }
        }
        
        setListAdapter(adapter);
        setContentView(R.layout.logs);
    }
    
	/**
	 * Options Menu
	 * 
	 * @param menu 
	 */
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.messages, menu);

	    return true;
	}
    
    /**
     * On options item selected
     * 
     * @param item
     */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.refresh:
				adapter.refresh();
				break;
			case R.id.filter:
				Intent intent = new Intent(MessagesActivity.this, FilterActivity.class);
				intent.putExtra("filter", adapter.getFilter());
				startActivityForResult(intent, SET_FILTER);
				break;
		}
		return true;
	}
	
	/**
	 * On filter set
	 */
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == SET_FILTER) {
			if (resultCode == RESULT_OK) {
				adapter.setFilter((Filter) data.getExtras().get("filter"));
				adapter.refresh();
			}
		}
	}
	
	public void onListItemClick(ListView l, View v,  int position, long id)
	{
		LogMessage message = adapter.getItem(position);
		
		Intent intent = new Intent(MessagesActivity.this, InspectActivity.class);
		intent.putExtra("message", message);
		
		startActivity(intent);
	}
}
