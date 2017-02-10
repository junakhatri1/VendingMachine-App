package com.juna.vendingmachine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.juna.vendingmachine.dao.VendingMachineDAO;
import com.juna.vendingmachine.dto.Change;
import com.juna.vendingmachine.dto.Items;
import com.juna.vendingmachine.ui.Prompt;

public class VendingMachineController {
	VendingMachineDAO dao = new VendingMachineDAO();

	Prompt consoleIo = new Prompt();
	Change change = new Change();
	String selectPosition = " ";
	double userInputAmount = 0;
	double amountsToBeNeeded = 0d;
	Double remainingBalance = 0d;

	public void loadVendingMachine(){
		try{
			consoleIo.givenString("Welcome to digital Vending Machine" );
			dao.loadInventory();
			listItemsAndPrices();

			userInputAmount = consoleIo.userInput("Please input cash before making any purchases" );
			boolean condition = true;
			while(condition ){
				if(userInputAmount > 0){
					consoleIo.givenString(" 1. to go to menu.... 2 to select position... 3 to exit");
					Integer makeChoices = consoleIo.userInput("Please select from the given choices");

					switch (makeChoices) {
					case 1:
						consoleIo.givenString("You have entered to the  main menu" );
						listItemsAndPrices();
						break;

					case 2 :
						listItemsAndPrices();
						selectPosition= consoleIo.readString("Please select Position from the above choices to purchase any item");

						Items item = dao.getItems(selectPosition);
						if (item != null ) {					
							changeDollars();
							updateInventory();
						}
						else if(userInputAmount<=0){
							updateInventory();
							consoleIo.givenString("You have insufficient funds. If you want to buy this item you need to insert " + amountsToBeNeeded + "dollars into the vending machine");
							condition =false;
						}
						else {
							consoleIo.givenString("Invalid item Position... Please select from the given item position " );
						}
						break;

					case 3:
						consoleIo.givenString("Exit");
						//dao.inventoryWriter();
						consoleIo.givenString("Your change is "+ change.getDollars() +" Dollars " 
								+ change.getQuarters()+" Quarters " + change.getDimes() + " Dimes " 
								+ change.getNickles() + " Nickles " + change.getCents() + " Cents " );
						condition =false;
						break;

					default:
						consoleIo.givenString("Thanks for stopping by....");
						break;
					}
					dao.inventoryWriter();
				}
				else{
					consoleIo.givenString("You donot have enough balance" ); 
					//consoleIo.givenString("You have insufficient funds. If you want to buy this item you need to insert " + amountsToBeNeeded + "dollars into the vending machine");
					condition =false;
				}
			} 

		}catch(FileNotFoundException e ){
			consoleIo.givenString("Roster file wasnot found");
		}catch(IOException e ){
			consoleIo.givenString("Unable to write file");

		}
	}	


	private void listItemsAndPrices(){
		String[] allItemsPosition = dao.getAllItemPosition();
		for(int i =0; i < allItemsPosition.length; i ++){
			
			Items item = dao.getItems(allItemsPosition[i] );
			if(item.getInventory()!=0){
				consoleIo.givenString(item.getItemPosition() + ": " +  item.getItemName() + "....." + item.getPriceOfItem() + "$ " + item.getInventory() + "in stock");
			}
			
	
			
		}	
	}
	

	private void  changeDollars(){
		Items items = dao.getItems(selectPosition);

		double getChange =0;
		if(userInputAmount >= items.getPriceOfItem()){
			getChange = userInputAmount-items.getPriceOfItem();
			userInputAmount =  getChange;

			Integer cents = (int) Math.round(getChange *100);
			Integer dollar = cents/100;
			cents = cents% 100;
			Integer quarters = cents/25;
			cents = cents%25;
			Integer dimes = cents/10;
			cents = cents%10;
			Integer nickles = cents/10;
			cents = cents%5;
			change.setCents(cents);
			change.setNickles(nickles);
			change.setDimes(dimes);
			change.setQuarters(quarters);
			change.setDollars(dollar);
		}
		else{
			userInputAmount = userInputAmount- items.getPriceOfItem() ;
			amountsToBeNeeded = java.lang.Math.abs(userInputAmount);

		}
	}

	private void updateInventory(){
		Items item = dao.getItems(selectPosition);
		item.setItemPosition(selectPosition);
		Integer newInventory = item.getInventory()-1;
		item.setInventory(newInventory);
		dao.updateInventory(item);

	}

}







