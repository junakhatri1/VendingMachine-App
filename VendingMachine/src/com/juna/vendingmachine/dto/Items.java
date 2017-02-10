package com.juna.vendingmachine.dto;

public class Items {
	private String itemName;
	private double priceOfItem;
	private Integer inventory;
	private Integer userInputAmount;
	private String  itemPosition;
	
	public Items(){
		
	}
	public Items( String itemPosition, String itemName, double priceOfItem, Integer inventory ){
		this.itemPosition = itemPosition;
		this.itemName = itemName;
		this.priceOfItem = priceOfItem;
		this.inventory = inventory;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPriceOfItem() {
		return priceOfItem;
	}

	public void setPriceOfItem(double priceOfItem) {
		this.priceOfItem = priceOfItem;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public Integer getUserInputAmount() {
		return userInputAmount;
	}

	public void setUserInputAmount(Integer userInputAmount) {
		this.userInputAmount = userInputAmount;
	}

	public String getItemPosition() {
		return itemPosition;
	}

	public void setItemPosition(String itemPosition) {
		this.itemPosition = itemPosition;
	}
	
	
	
	
	

}
