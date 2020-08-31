package com.cleardebts.util;

import java.util.Date;

public class DateUtil {

	public static Date getDateFromUnixTimestamp(Long date) {

		return new Date((long) date * 1000);

	}

}
