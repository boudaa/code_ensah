package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static Date addDaysToDate(Date pDateToIncrement, int days) {

		Calendar c = Calendar.getInstance();
		c.setTime(pDateToIncrement);
		c.add(Calendar.DATE, days);
		Date newDate = c.getTime();

		return newDate;

	}

	public static Date addMinutesToDate(Date pDateToIncrement, int minutes) {

		Calendar c = Calendar.getInstance();
		c.setTime(pDateToIncrement);
		c.add(Calendar.MINUTE, minutes);
		Date newDate = c.getTime();

		return newDate;

	}

	/**
	 * This method attempts to convert an Oracle-formatted date in the form
	 * dd-MMM-yyyy to mm/dd/yyyy.
	 * 
	 * @param aDate
	 *            date from database as a string
	 * @return formatted string for the ui
	 */

	/**
	 * Convertit yne date en une chaine de caractere
	 * 
	 * @param aDate
	 * @param pattern
	 * @return
	 */

	public static String getDate(Date aDate, String pattern) {
		SimpleDateFormat df;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(pattern);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date/time in the
	 * format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param strDate
	 *            a string representation of a date
	 * @return a converted Date object
	 * @see java.text.SimpleDateFormat
	 * @throws ParseException
	 *             when String doesn't match the expected format
	 */

	/**
	 * Convertit la date en chaine de caracteres
	 * 
	 * @param aMask
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static Date convertStringToDate(String aMask, String strDate)
			throws ParseException {
		SimpleDateFormat df;
		Date date;
		df = new SimpleDateFormat(aMask);

		date = df.parse(strDate);

		return (date);
	}

	/**
	 * This method generates a string representation of a date's date/time in
	 * the format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param aDate
	 *            a date object
	 * @return a formatted string representation of the date
	 * 
	 * @see java.text.SimpleDateFormat
	 */
	public static String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		df = new SimpleDateFormat(aMask);
		returnValue = df.format(aDate);

		return (returnValue);
	}

	

}
