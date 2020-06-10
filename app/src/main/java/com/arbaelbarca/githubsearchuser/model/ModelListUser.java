package com.arbaelbarca.githubsearchuser.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ModelListUser{

	@SerializedName("total_count")
	private int totalCount;

	@SerializedName("incomplete_results")
	private boolean incompleteResults;

	@SerializedName("items")
	private ArrayList<ItemsItem> items;

	public void setTotalCount(int totalCount){
		this.totalCount = totalCount;
	}

	public int getTotalCount(){
		return totalCount;
	}

	public void setIncompleteResults(boolean incompleteResults){
		this.incompleteResults = incompleteResults;
	}

	public boolean isIncompleteResults(){
		return incompleteResults;
	}

	public void setItems(ArrayList<ItemsItem> items){
		this.items = items;
	}

	public ArrayList<ItemsItem> getItems(){
		return items;
	}

	@Override
 	public String toString(){
		return 
			"ModelListUser{" + 
			"total_count = '" + totalCount + '\'' + 
			",incomplete_results = '" + incompleteResults + '\'' + 
			",items = '" + items + '\'' + 
			"}";
		}
}