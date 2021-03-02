package com.dominic.demos;

import com.dominic.demos.util.LogUtil;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testRegex() {
        matcherDemo();
    }

    public void matcherDemo() {
        String testLine = "hi, this msg come from Dominicatnight11:54";
        String regex = "(Dominic)(\\w+)(\\d+\\:\\d+)";
        Pattern p = Pattern.compile(regex);
        Matcher mm = p.matcher(testLine);
        while (mm.find()) {
            int gCount = mm.groupCount();
            String wholeMatchContent = mm.group(); //group(0)

            String group1 = mm.group(1);
            String group2 = mm.group(2);
            String group3 = mm.group(3);
            LogUtil.log(group1);
            LogUtil.log(group2);
            LogUtil.log(group3);
        }
    }
}