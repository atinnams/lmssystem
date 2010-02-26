package org.hcmus.dao.idao;

import org.hcmus.bus.JPOS_GiftDTO;

public interface IJPOS_Gift {
	public void setGift(JPOS_GiftDTO gift);
	public JPOS_GiftDTO getGift(int GiftId);
}
