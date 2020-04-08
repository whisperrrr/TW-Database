package util;

import exception.InputNotLegalException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatUtil {
    public static void inputFormat(String input, String pattern) {
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(input);
        if (!m.matches()) {
            throw new InputNotLegalException();
        }
    }
}
