package DTO;

/**
 * 
 * @author HUNGPT
 * PoSCC Data transfer object.
 */
public class DTO_JPOS_PoSCC {
	
	/**************** Attribute *******************/
	
	// PoSCC Identify.
	private String JPOS_PoSCC_ID ;
	
	//PoSCC Name
	private String JPOS_PoSCC_Name;
	
	/***************** Properties ******************/
	
	public String getJPOS_PoSCC_ID() {
		return JPOS_PoSCC_ID;
	}
	
	public void setJPOS_PoSCC_ID(String poSCC_ID) {
		JPOS_PoSCC_ID = poSCC_ID;
	}
	
	public String getJPOS_PoSCC_Name() {
		return JPOS_PoSCC_Name;
	}
	
	public void setJPOS_PoSCC_Name(String poSCC_Name) {
		JPOS_PoSCC_Name = poSCC_Name;
	}
}
