package com.dominic.demos.regex;

import com.dominic.demos.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {


    public static void matcherDemo() {
        String testLine = "hi, this msg come from Dominic at night 11:54";
        String regex = "(Dominic)(\\w+)(\\d+:\\d+)";
        Pattern p = Pattern.compile(regex);
        Matcher mm = p.matcher(testLine);
        while (mm.find()) {
            int gCount = mm.groupCount();
            String wholeMatchContent = mm.group(); //group(0)

            String group1 = mm.group(1);
            String group2 = mm.group(2);
            String group3 = mm.group(3);
            Log.log(group1);
            Log.log(group2);
            Log.log(group3);
        }
    }


}
