package org.hcmus.dao.idao;

import java.sql.Connection;

import org.hcmus.bus.JPOS_GiftDTO;

public interface IJPOS_Gift {
	public void setGift(JPOS_GiftDTO gift,Connection con);
	public JPOS_GiftDTO getGift(int GiftId,Connection con);
}
