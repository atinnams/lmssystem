package test.dao;

import java.sql.Connection;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.hcmus.bus.JPOS_GiftDTO;
import org.hcmus.dao.lms.JPOS_GiftDAO;

public class JPOS_GiftDAO_TEST extends TestCase {

	Connection con = null;

	public JPOS_GiftDAO_TEST(String name) {
		super(name);
	}

	public void setUp() {
		con = DataProviderTest.getConnection();
	}

	public void tearDown() {
		con = null;
	}

	public void testGetGifts() {
		JPOS_GiftDAO giftDAO = new JPOS_GiftDAO();
		ArrayList<JPOS_GiftDTO> gifts = giftDAO.getGifts("1111111111111111", con);
		if (gifts.size() == 0) {
			System.out.println("Empty");
		} else {
			int giftsSize = gifts.size();
			for (int i = 0; i < giftsSize; i++) {
				JPOS_GiftDTO gift = gifts.get(i);
				System.out.println(gift.getGiftId());
				System.out.println(gift.getGiftName());
				System.out.println(gift.getPointForGift());
			}
		}
	}

}
