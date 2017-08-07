package com.phincon.talents.app;

import java.util.Date;

import com.phincon.talents.app.utils.Utils;

public class MainTest {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date lastPayrollDate = Utils.convertStringToDate("2017-07-01");
		Date currentPayrollRequest = Utils.convertStringToDate("2017-04-01");
		System.out.println("Dif Month " + Utils.diffMonth(currentPayrollRequest, lastPayrollDate));
	}

}
