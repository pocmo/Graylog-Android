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
import android.view.View;
import android.widget.ListView;

import com.jimdo.graylog.R;
import com.jimdo.graylog.adapter.CategoryListAdapter;

/**
 * Shows the available categories
 * 
 * @author Sebastian Kaspari <s.kaspari@googlemail.com>
 */
public class CategoriesActivity extends ListActivity {
	public static final String TAG = "Graylog/CategoriesActivity";
	
	private CategoryListAdapter adapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);
        
        adapter = new CategoryListAdapter();
        setListAdapter(adapter);
     
        refresh();
    }
    
    /**
     * Refresh categories, reload from server
     */
    public void refresh()
    {
    	adapter.load();
    }
    
	/**
	 * On category selected..
	 */
    @Override
	public void onListItemClick(ListView l, View v,  int position, long id)
	{
		Intent intent = new Intent(CategoriesActivity.this, MessagesActivity.class);
		intent.putExtra("category_id", (int) id);
		startActivity(intent);
	}
}
