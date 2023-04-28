package Util;

public class StringUtil {

    public static boolean isNulOrEmpty(String value) {
        if (value == null || value.isEmpty())
            return true;
        return false;
    }
}
