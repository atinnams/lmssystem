package DTO;

public class DTO_JPOS_Gift {
	/** variable **/
	private int giftId;
	private String giftName;
	private int pointForGift;
	
	public DTO_JPOS_Gift() { }
	
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
