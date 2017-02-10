package com.juna.vendingmachine.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.juna.vendingmachine.dto.Change;
import com.juna.vendingmachine.dto.Items;

public class VendingMachineDAO {
	Map<String, Items> vendingMachineMap = new HashMap<>();
	private static final String MACHINE_FILE = "machine.txt";
	private static final String DELIMITER = "::";

	public VendingMachineDAO() {
	/*Items item1 = new Items("A1", "Twix", 2, 20);
		Items item2 = new Items("A2" , "Cheetos", 3, 30);
		Items item3 = new Items("A3", "Gum", 1.99, 40);
		Items item4 = new Items("A4", "pepsi", 2.5, 60);

		vendingMachineMap.put(item1.getItemPosition(), item1);
		vendingMachineMap.put(item2.getItemPosition(), item2);
		vendingMachineMap.put(item3.getItemPosition(), item3);
		vendingMachineMap.put(item4.getItemPosition(), item4);*/
	}

	public String[] getAllItemPosition(){
		Set<String> keySet = vendingMachineMap.keySet();
		String[] position = new String[keySet.size()];
		position = keySet.toArray(position);
		return position;

	}


	public Items getItems(String position){
		return vendingMachineMap.get(position);

	}

	public Items updateInventory(Items item){
		String	itemPosition = item.getItemPosition();
		vendingMachineMap.put(itemPosition,item);
		return item;

	}


	public void inventoryWriter() throws IOException{
		PrintWriter writer = new PrintWriter(new FileWriter(MACHINE_FILE));
		String[] itemPosition = this.getAllItemPosition();
		for(int i =0; i < itemPosition.length; i++){
			Items item = this.getItems(itemPosition[i]);
			writer.println(item.getItemPosition()+ DELIMITER +item.getItemName() + DELIMITER
					+ item.getPriceOfItem()+ DELIMITER + item.getInventory());
			writer.flush();
		}
		writer.close();
	}


	public void loadInventory() throws FileNotFoundException{
		Scanner sc = new Scanner(new BufferedReader(new FileReader(MACHINE_FILE)));
		String currentLine;
		String[] currentTokens;
		
			while(sc.hasNext()){
			currentLine =sc.nextLine();
			currentTokens =currentLine.split(DELIMITER);
			Items item = new Items();
			item.setItemPosition(currentTokens[0]);
			item.setItemName(currentTokens[1]);
			double itemPrice = Double.valueOf(currentTokens[2]);
			item.setPriceOfItem(itemPrice);
			Integer inventory = Integer.valueOf(currentTokens[3]);
			item.setInventory(inventory);
			vendingMachineMap.put(item.getItemPosition(), item);
		
			}


			sc.close();





		}

	}



























