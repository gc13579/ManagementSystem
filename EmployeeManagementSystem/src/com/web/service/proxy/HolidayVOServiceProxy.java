package com.web.service.proxy;

import com.web.VO.HolidayVO;
import com.web.service.HolidayVOService;
import com.web.trans.Transaction;

public class HolidayVOServiceProxy implements HolidayVOService {
	private Transaction trans;
	private HolidayVOService holidayVoService;

	public HolidayVO getHolidayVOByNo(String holidayNo) throws Exception {
		HolidayVO holidayVO = null;
		try {
			trans.begin();
			holidayVO = holidayVoService.getHolidayVOByNo(holidayNo);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return holidayVO;
	}

	public HolidayVOService getHolidayVoService() {
		return holidayVoService;
	}

	public void setHolidayVoService(HolidayVOService holidayVoService) {
		this.holidayVoService = holidayVoService;
	}

	public Transaction getTrans() {
		return trans;
	}

	public void setTrans(Transaction trans) {
		this.trans = trans;
	}

	
}
