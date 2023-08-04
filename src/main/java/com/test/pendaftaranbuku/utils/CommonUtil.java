package com.test.pendaftaranbuku.utils;

import org.springframework.util.ObjectUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CommonUtil {

    private static final String PATTERN2 = "dd MMM yyyy";

    public static Date getDateByStringFormat1(String input) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
        return formatter.parse(input);
    }

    public static Date getDateByStringFormat2(String input) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(PATTERN2, Locale.ENGLISH);
        return formatter.parse(input);
    }

    public static String getDateFormat(Date date) {
        if (ObjectUtils.isEmpty(date))
            return "-";

        DateFormat formatter = new SimpleDateFormat(PATTERN2);
        return formatter.format(date);
    }
}
