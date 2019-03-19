package org.springapp.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;


/**
 * Miscellaneous String processing methods.
 *
 * @author Yasufuku Hiromi
 */
public class StringUtil {

    /** Logging category */

    //////////////////////////////////////////////////////////////
    // Constructors and initialisation.

    /**
     * (Private constructor) Never instantiated.
     */
    private StringUtil() {
    }


    //////////////////////////////////////////////////////////////
    // Public class methods.

    /**
     * Find and replace the specified substring within the original string with
     * the given replacement, and return the result.
     */
    public static String findAndReplace(String orig, String sub, String rep) {

        StringBuffer out = new StringBuffer();
        int index = 0;
        int oldIndex = index;
        while (index != -1) {
            index = orig.indexOf(sub, index);
            if (index != -1) {
                out.append(orig.substring(oldIndex, index));
                index += sub.length();
                oldIndex = index;
                out.append(rep);
            } else {
                out.append(orig.substring(oldIndex));
            }
        }

        return out.toString();
    }

    /**
     * For a given string orig, find and replace every instance of key strings
     * with their respective values.
     *
     * @param orig The original string.
     * @param sub  The keys to find and their respective values to replace with.
     */
    public static String findAndReplaceAll(String orig, Map sub) {
        Iterator i = sub.keySet().iterator();
        while (i.hasNext()) {
            String key = (String) i.next();
            String value = (String) sub.get(key);
            if (value == null) {

                continue;
            }
            orig = findAndReplace(orig, key, value);
        }
        return orig;
    }

    /**
     * Convert an int to a String and pad it with zeros on the left making
     * it <code>digits</code> digits wide. If the int is already this wide
     * or wider, it will be unchanged. Any minus sign at the front will be
     * preserved, and not included in the length to which the string is
     * padded.
     */
    public static String zeroPadInt(int number, int digits) {
        String s = String.valueOf(number);
        if (s.length() >= digits)
            return s;

        // Digits is never negative here.
        int finalLength = digits + 1;
        if (finalLength < 0)              // Arithmetic overflow.
            finalLength = digits;

        StringBuffer out = new StringBuffer(finalLength);
        if (s.charAt(0) == '-') {
            out.append("-");
            int numZeros = digits - s.length() + 1;
            for (int i = 0; i < numZeros; i++) {
                out.append("0");
            }
            out.append(s.substring(1));
        } else {
            int numZeros = digits - s.length();
            for (int i = 0; i < numZeros; i++) {
                out.append("0");
            }
            out.append(s);
        }
        s = out.toString();
        return s;
    }

    /**
     * Attempt to extract a single line from a stacktrace indicating whence
     * a method was invoked. This is slow, and depends on the stacktrace
     * being multiple lines in the format "at package.class.method ...".
     *
     * @param t  A {@link Throwable} containing a stacktrace. Normally
     *           this is created with: <blockquote><code>
     *           Throwable t = new Throwable(); <br>
     *           t.fillInStackTrace();
     *           </code></blockquote> If null, this method will return
     *           an empty String.
     * @param me The package name or fully qualified class name of
     *           the method being invoked. All lines in the stacktrace
     *           that contain this string will be skipped over, and
     *           the next line in the stacktrace will be returned.
     * @return A string containing a line describing the class,
     *         method, and possibly line number of the deepest
     *         invoker not containing the string <code>me</code>.
     *         This may be the string <code>"unknown"</code> if
     *         the invoker cannot be determined.
     */
    public static String extractCaller(Throwable t, String me) {
        try {
            if (t == null || me == null)
                return "";

            // Get the stack trace out of the Throwable as a String.
            // It sucks that we have to do it this way.
            StringWriter swriter = new StringWriter(512);
            PrintWriter pwriter = new PrintWriter(swriter);
            t.printStackTrace(pwriter);
            pwriter.close();
            String st = swriter.toString();

            // Find the last line with me in it.
            int index = st.indexOf(me);
            int nextIndex = st.indexOf(me, index + 1);
            while (nextIndex >= 0) {
                index = nextIndex;
                nextIndex = st.indexOf(me, index + 1);
            }

            // Find the beginning and end of the next line.
            index = st.indexOf("at ", index) + 3;
            nextIndex = st.indexOf('\n', index);
            while (st.charAt(nextIndex) == '\r' || st.charAt(nextIndex) == '\r')
                nextIndex--;

            return st.substring(index, nextIndex + 1).trim();
        } catch (Exception e) {
//           Category log = Category.getInstance("net.keyring.util.StringUtil");
//           log.warn("Caught exception in debugging code", e);
            // Given that this is debugging code, we never want it to fail.
            return "(Method failed; exception logged.)";
        }
    }

    /**
     * Test whether a path is "safe" - i.e. doesnt contain ..'s and
     * is not absolute.
     *
     * @param p path to check
     */
    public static boolean safePath(String p) {
        return !p.startsWith("/") && !p.startsWith("../")
                && !p.endsWith("/..") && p.indexOf("/../") == -1;
    }

    /**
     * Expand c style \\ escapes escapes like C does.
     */
    public static String unescapeBackslashedCharacters(String s) {
        StringBuffer sb = new StringBuffer(s.length() * 2);
        char[] buf = new char[s.length()];
        char c;

        s.getChars(0, s.length(), buf, 0);
        for (int i = 0; i < buf.length; i++) {
            if (buf[i] != '\\' || i == buf.length - 1) {
                sb.append(buf[i]);
                continue;
            }
            switch (buf[++i]) {
                case 'a':
                    c = '\007';       /* Bell */
                    break;
                case 'b':
                    c = '\010';       /* Backspace */
                    break;
                case 't':
                    c = '\011';       /* Horizontal Tab */
                    break;
                case 'n':
                    c = '\012';       /* New Line */
                    break;
                case 'v':
                    c = '\013';       /* Vertical Tab */
                    break;
                case 'f':
                    c = '\014';       /* Form Feed */
                    break;
                case 'r':
                    c = '\015';       /* Carriage Return */
                    break;
                case 'e':
                    c = '\033';       /* Escape */
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7': {
                    int b = 0;
                    for (int cnt = 0; cnt < 3; cnt++) {
                        if (i == buf.length)
                            break;
                        char ch = buf[i++];
                        if (ch < '0' || ch > '7') {
                            i--;
                            break;
                        }
                        b = (b << 3) | (ch - '0');
                    }
                    i--;
                    c = (char) b;
                    break;
                }
                default:
                    c = buf[i];
                    break;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * Check string is ascii characters.
     *
     * @param s is a string to check
     */
    public static boolean isAscii(String s) {
        if (s == null)
            return false;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] < 0x20 || chars[i] >= 0x7F)
                return false;
        }
        return true;
    }

    /**
     * Given a string, convert it to a hexadecimal string of the
     * form "0xn1n2n3n4n5..." where n1, n2, etc. are two-digit
     * hexadecimal numbers. This is a format that SQL server will
     * accept for an insert into a varchar. (The 0xn1n2... string
     * should not be quoted). We do this to handle characters in
     * japanese and other non-ascii strings.
     * <p/>
     * <p> If any chars > 255 are encountered in the String, they
     * will be converted to `3F' (a question mark).
     * <p/>
     * <p> In order to ensure that the result of this is valid when
     * placed into an INSERT or UPDATE statement, this returns an string
     * consisting of just two single quotes if the input string is empty.
     */
    public static String toSQLHexString(String s) {
        int length = s.length();
        if (length == 0)
            return "''";

        StringBuffer retval = new StringBuffer(length * 2 + 3);
        retval.append("0x");
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c > 0xff)
                c = 0x3f;
            if (c < 0x10)
                retval.append('0');
            retval.append(Integer.toHexString(c));
        }
        return retval.toString();
    }

    /**
     * Return <tt>true</tt> if <tt>container</tt> contains within
     * it the String <tt>contained</tt>. Either argument may be null,
     * in which case <tt>false</tt> will be returned.
     */
    public static boolean contains(String container, String contained) {
        if (container == null || contained == null)
            return false;
        if (contained.equals(""))
            return true;

        char firstChar = contained.charAt(0);
        int containedLength = contained.length();

        // contained must be within the first maxlen chars of the
        // container because after that it's longer than what remains.
        int maxlen = container.length() - contained.length();

        // We look for a possible start, which is the next place in
        // container where we see the first char of contained.
        // Then we try a full match at that point.
        int start = -1;
        while (start < maxlen) {
            start = container.indexOf(firstChar, start + 1);
            if (start < 0)
                break;
            if (container.regionMatches(start, contained, 0, containedLength))
                return true;
        }
        return false;
    }

    /**
     * Prepend a backslash (<tt>\</tt>) before every occurance of
     * any character in <tt>toEscape</tt>.
     * <p/>
     * <p> Note that if you want backslashes escaped, you must ensure
     * that backslash is a part of the <tt>toEscape</tt> String.
     *
     * @param string   The string that should have its instances of
     *                 <tt>toEscape</tt> preceeded by a backslash.
     * @param toEscape The characters to escape with a backslash.
     * @return The String which added the escape sequence character.
     */
    public static String escapeWithBackslash(String string, String toEscape) {
        int strlen = string.length();
        int escapeCount = searchString(string, toEscape);
        if (escapeCount == 0)
            return string;

        StringBuffer buff = new StringBuffer(strlen + escapeCount);
        for (int i = 0; i < strlen; i++) {
            char c = string.charAt(i);
            if (toEscape.indexOf(c) >= 0)
                buff.append('\\');
            buff.append(c);
        }
        return buff.toString();
    }

    /**
     * Delete the character in <tt>delChars</tt> from <tt>string</tt>.
     *
     * @param string   String which eliminates the character
     *                 in <tt>delChars</tt>.
     * @param delChars The characters to delete.
     * @return The String which deleted the character.
     */
    public static String deleteCharacters(String string, String delChars) {
        int strlen = string.length();
        int delCount = searchString(string, delChars);
        if (delCount == 0)
            return string;

        StringBuffer buff = new StringBuffer(strlen + delCount);
        for (int i = 0; i < strlen; i++) {
            char c = string.charAt(i);
            if (delChars.indexOf(c) == -1)
                buff.append(c);
        }
        return buff.toString();
    }

    /**
     * Replace the character in <tt>fromChars</tt> to <tt>toChar</tt>.
     *
     * @param string    String which replace the character
     *                  in <tt>fromChars</tt>.
     * @param fromChars The characters to replace.
     * @param toChar    The character after conversion.
     * @return The String which replaced the character.
     */
    public static String replaceCharacters(String string, String fromChars,
                                           String toChar) {
        int strlen = string.length();
        int replaceCount = searchString(string, fromChars);
        if (replaceCount == 0)
            return string;

        StringBuffer buff = new StringBuffer(strlen + replaceCount);
        for (int i = 0; i < strlen; i++) {
            char c = string.charAt(i);
            if (fromChars.indexOf(c) >= 0)
                buff.append(toChar);
            else
                buff.append(c);
        }
        return buff.toString();
    }

    /**
     * Count the search character in string.
     *
     * @param string The string which should count the character
     *               in <tt>search</tt>.
     * @param search The character to search.
     * @return The number of the counted character.
     */
    private static int searchString(String string, String search) {
        int strlen = string.length();
        int searchCount = 0;
        for (int i = 0; i < strlen; i++) {
            if (search.indexOf(string.charAt(i)) >= 0)
                searchCount++;
        }
        return searchCount;
    }

    /**
     * Restriction of the number of characters.
     * <p/>
     * <p> Larger string than restrict is effective to restrict-3.
     * The omitted character is transposed to "...".
     *
     * @param string   String which restriction the character.
     * @param restrict The number of restrictions
     * @return The string which restriction the character.
     */
    public static String restrictLength(String string, int restrict) {
        if (string.length() > restrict) {
            return string.substring(0, restrict - 3) + "...";
        } else {
            return string;
        }
    }


}