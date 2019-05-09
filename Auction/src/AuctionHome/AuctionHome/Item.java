package AuctionHome;

import javax.swing.JButton;

//allows users to set get and create everything relating to the items
public class Item {

	 private String itemName;
	 private String picture;
	 private Double price;
	 private String stringDateEnd;
	 private static JButton button;
	 
	   public Item(String itemName, String picture, Double price, String stringDateEnd, JButton button) {
	        this.itemName = itemName;
	        this.picture = picture;
	        this.price = price;
	        this.stringDateEnd = stringDateEnd;
	        this.button = button;
	    }
	   
	   public Item(String itemName, String picture, Double price, String stringDateEnd) {
	        this.itemName = itemName;
	        this.picture = picture;
	        this.price = price;
	        this.stringDateEnd = stringDateEnd;
	    }
	   
	   public Item() {
		   
	   }

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTime() {
		return stringDateEnd;
	}

	public void setTime(String stringDateEnd) {
		this.stringDateEnd = stringDateEnd;
	}

	 public static JButton getButtonList() {
		return button;
	}
	public void setButtonList(JButton button) {
		this.button = button;
	}

	
	public String toString() {
			return itemName + "," + picture + "," + price + "," + stringDateEnd;
		}
	   
	   
}
