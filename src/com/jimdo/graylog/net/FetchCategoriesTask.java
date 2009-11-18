package com.jimdo.graylog.net;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.util.Log;

import com.jimdo.graylog.CategoryListAdapter;
import com.jimdo.graylog.model.Category;
import com.jimdo.graylog.model.ResponseDeserializer;

public class FetchCategoriesTask extends AsyncTask<String, Void, ArrayList<Category>>{
	public static final String TAG = "Graylog/FetchCategoriesTask";
	
	private CategoryListAdapter adapter;
	
	public FetchCategoriesTask(CategoryListAdapter adapter)
	{
		this.adapter = adapter;
	}

	@Override
	protected ArrayList<Category> doInBackground(String... urls)
	{
		try {
			String response = new Request().execute(urls[0]);
			return ResponseDeserializer.deserializeCategories(response);
		}
		catch(Exception e) {
			Log.d(TAG, "Exception: " + e.getMessage());
			return null;
		}
	}
	
	protected void onPostExecute(ArrayList<Category> categories)
	{
		if (categories != null) {
			adapter.setCategories(categories);
		}
	}
}
