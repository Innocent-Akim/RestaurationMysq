/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.app;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 *
 * @author Akim
 */
public class DateOfday {

    public static DateOfday _instanceDateOfDay;
    public static Calendar cal = Calendar.getInstance();
    public static GregorianCalendar calendar = new GregorianCalendar();

    public static String getWeekDay() {
        // on crée une instance de DateFormatSymbols selon la locale :
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.FRANCE);
        // On récupère le numéro du jour dans la semaine :
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        // Format court :
//        System.out.println(symbols.getShortWeekdays()[day]);
        // Format long :
        return symbols.getWeekdays()[day];
    }

    public static String getMoth() {
        // on crée une instance de DateFormatSymbols selon la locale :
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.FRANCE);
        // On récupère le numéro du jour dans la semaine :
        int moth = calendar.get(Calendar.MONTH);
        // Format court :
//        System.out.println(symbols.getShortWeekdays()[day]);
        // Format long :
        return symbols.getMonths()[moth];
    }

    public static void getDate() {

        Calendar cal = Calendar.getInstance();  // date du jour
    }

    public static String Pic(String str) {
        String _fst = ("" + str.charAt(0)).toUpperCase();
        String scnd = ("" + str.substring(1, str.length())).toLowerCase();
        str = _fst + scnd;
        return str;
    }
}
