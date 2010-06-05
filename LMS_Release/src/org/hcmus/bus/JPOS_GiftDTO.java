package org.hcmus.bus;

public class JPOS_GiftDTO {
	/** variable **/
	private int giftId;
	private String giftName;
	private int pointForGift;
	
	public JPOS_GiftDTO() { }
	
	public int getGiftId() {
		return giftId;
	}
	
	public void setGiftId(int giftId) {
		this.giftId = giftId;
	}
	
	public String getGiftName() {
		return giftName;
	}
	
	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}
	
	public int getPointForGift() {
		return pointForGift;
	}
	
	public void setPointForGift(int pointForGift) {
		this.pointForGift = pointForGift;
	}
}
