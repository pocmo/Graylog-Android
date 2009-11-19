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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jimdo.graylog.R;
import com.jimdo.graylog.R.id;
import com.jimdo.graylog.R.layout;
import com.jimdo.graylog.model.Category;
import com.jimdo.graylog.net.FetchCategoriesTask;
import com.jimdo.graylog.net.UrlBuilder;

/**
 * Adapter for the List in CategoriesActivity
 * 
 * @author Sebastian Kaspari <s.kaspari@googlemail.com>
 */
public class CategoryListAdapter extends BaseAdapter {
	public final static String TAG = "Graylog/CategoryListAdapter";
	
	private ArrayList<Category> categories = new ArrayList<Category>();
	private boolean showLoadingItem = true;
	
	/**
	 * Get number of items
	 */
	public int getCount()
	{
		if (showLoadingItem) {
			return categories.size() + 1;
		} else {
			return categories.size();
		}
	}
	
	/**
	 * Load categories from server
	 */
	public void load()
	{
		FetchCategoriesTask task = new FetchCategoriesTask(this);
		task.execute(UrlBuilder.getInstance().getCategoriesUrl());
		showLoadingItem = true;
		notifyDataSetChanged();
	}
	
	/**
	 * Set Categories
	 * 
	 * Will be called by the AsyncTask
	 * 
	 * @param categories
	 */
	public void setCategories(ArrayList<Category> categories)
	{
		this.categories = categories;
		this.showLoadingItem = false;
		notifyDataSetChanged();
	}
	
	/**
	 * Get item at specified location
	 */
	public Category getItem(int location)
	{
		return categories.get(location);
	}
	
	/**
	 * Get id of item at specified location
	 */
	public long getItemId(int location)
	{
		return getItem(location).getId();
	}
	
	/**
	 * Get view for list item
	 */
	public View getView(int location, View v, ViewGroup p)
	{
		LayoutInflater inflater = (LayoutInflater) p.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		if (location == categories.size()) {
			return inflater.inflate(R.layout.loading, null);
		}
		
		Category category = categories.get(location);
		
		v = inflater.inflate(R.layout.category, null);
		
		TextView tv = (TextView) v.findViewById(R.id.title);
		tv.setText(category.getTitle());
		
		return v;
	}
}
