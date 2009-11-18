package com.jimdo.graylog;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jimdo.graylog.model.Category;
import com.jimdo.graylog.net.FetchCategoriesTask;
import com.jimdo.graylog.net.UrlBuilder;

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
