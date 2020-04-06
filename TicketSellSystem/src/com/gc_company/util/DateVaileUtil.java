package com.gc_company.util;

public class DateVaileUtil {
	public static boolean isValidDate(String str) throws NumberFormatException,Exception{
		try {
			if(str.length()!=10){
				return false;
			}
			// 判断闰年标志
			boolean isLeapYear = false;
			String stryear = str.substring(0, 4);
			String strmonth = str.substring(5, 7);
			String strday = str.substring(8);
			int year = Integer.parseInt(stryear);
			// 判断是否为闰年
			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
				isLeapYear = true;
			}
			// 判断月份
			// 1.判断月份
			if (strmonth.startsWith("0")) {
				int secondPlaceOfMonth = Integer.parseInt(strmonth.substring(1,
						2));
				if (secondPlaceOfMonth == 0) {
					return false;
				}
				if (secondPlaceOfMonth == 2) {
					// 获取2月的天数
					int vDays4February = Integer.parseInt(strday);
					if (isLeapYear) {
						if (vDays4February > 29)
							return false;
					} else {
						if (vDays4February > 28)
							return false;
					}
				}
			} else {
				// 2.判断非0打头的月份是否合法
				int month = Integer.parseInt(strmonth);
				if (month != 10 && month != 11 && month != 12) {
					return false;
				}
			}
			// 判断日期
			// 1.判断日期
			if (strday.startsWith("0")) {
				int day = Integer.parseInt(strday.substring(1, 2));
				if (day == 0) {
					return false;
				}
			} else {
				// 2.判断非0打头的日期是否合法
				int day = Integer.parseInt(strday);
				if (day < 10 || day > 31) {
					return false;
				}
			}
			return true;
		}catch(NumberFormatException e){
			throw new NumberFormatException("时间格式不匹配，请重新输入");
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
