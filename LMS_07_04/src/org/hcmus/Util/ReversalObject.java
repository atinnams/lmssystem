package org.hcmus.Util;

import java.util.ArrayList;

public class ReversalObject {
	
	public static ArrayList<TransactionObject> arrTransactionKey = new ArrayList<TransactionObject>();
	
	public static synchronized void put(TransactionObject obj){
		arrTransactionKey.add(obj);
	}
	
	public static synchronized void remove(TransactionObject obj){
		arrTransactionKey.remove(obj);
	}
	
	public static synchronized void put(String cardNumber,String invoiceNumber){
		arrTransactionKey.add(new TransactionObject(cardNumber,invoiceNumber));
	}
	
	public static synchronized void remove(String cardNumber,String invoiceNumber){
		arrTransactionKey.remove(new TransactionObject(cardNumber,invoiceNumber));
	}
	
	public static synchronized boolean contains(TransactionObject obj){
		return arrTransactionKey.contains(obj);
	}
	
	public static synchronized boolean contains(String cardNumber,String invoiceNumber){
		return arrTransactionKey.contains(new TransactionObject(cardNumber,invoiceNumber));
	}
}
