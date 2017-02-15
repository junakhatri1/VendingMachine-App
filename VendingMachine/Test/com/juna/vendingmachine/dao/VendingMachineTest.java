package com.juna.vendingmachine.dao;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.juna.vendingmachine.dto.Items;

public class VendingMachineTest {
	VendingMachineDAO dao;
	Items item1;
	Items item2;

	@Before
	public void SetUp(){
		dao = new VendingMachineDAO();
		item1 = new Items();
		item1.setItemName("M&Ms");
		item1.setItemPosition("A");
		item1.setPriceOfItem(2);
		item1.setInventory(20);

		item2 = new Items();
		item2.setItemPosition("B");
		item2.setItemName("Gummies");
		item2.setPriceOfItem(3);
		item2.setInventory(30);




	}

	@Test
	public void getAllItemPositiontest() {
		//AAA
		//Arrange
		dao.vendingMachineMap.put(item1.getItemPosition(), item1);
		dao.vendingMachineMap.put(item2.getItemPosition(), item2);
		//Act
		String[] positionId = dao.getAllItemPosition();
		int result = positionId.length;
		//Assert
		assertEquals(2, result);
	}


	@Test
	public void getItemsTest(){
		//AAA
		//Arrange
		dao.vendingMachineMap.put(item1.getItemPosition(), item1);
		dao.vendingMachineMap.put(item2.getItemPosition(), item2);
		//Act
		Items result = dao.getItems(item1.getItemPosition());
		//Assert
		assertEquals(item1, result);
	}

	@Test
	public void updateInventoryTest(){
		//AAA
		//Arrange
		dao.vendingMachineMap.put(item1.getItemPosition(), item1);
		dao.vendingMachineMap.put(item2.getItemPosition(), item2);
		item1.setInventory(21);
		//Act
		Items result = dao.updateInventory(item1);
		//assert
		assertEquals(item1, result);
	}

}
