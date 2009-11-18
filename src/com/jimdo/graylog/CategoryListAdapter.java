package com.jimdo.graylog;

import java.util.ArrayList;

import com.jimdo.graylog.model.Category;
import com.jimdo.graylog.net.FetchCategoriesTask;
import com.jimdo.graylog.net.UrlBuilder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CategoryListAdapter extends BaseAdapter {
	public final static String TAG = "Graylog/CategoryListAdapter";
	
	private ArrayList<Category> categories = new ArrayList<Category>();
	private String baseUrl;
	private boolean showLoadingItem = true;
	
	/**
	 * Create a new CategoryListAdapter
	 * 
	 * @param baseUrl Base Url to Graylog
	 */
	public CategoryListAdapter(String baseUrl)
	{
		this.baseUrl = baseUrl;
	}
	
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
		UrlBuilder builder = new UrlBuilder(baseUrl);
		task.execute(builder.getCategoriesUrl());
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
