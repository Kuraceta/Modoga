package utils;

import org.apache.commons.lang.StringUtils;

public class CapsLock {
	
	public static String getFixed(String message) {
		final int[] newMessage = checkCaps(message);
        if (percentageCaps(newMessage) >= 50 || checkCapsInRow(newMessage) >= 50) {
               return message.toLowerCase();
          }
        return message;
      }

public static int[] checkCaps(final String message) {
    final int[] newMessage = new int[message.length()];
    final String[] parts = message.split(" ");
    final String msgLow = StringUtils.join((Object[])parts, " ");
    for (int j = 0; j < msgLow.length(); ++j) {
        if (Character.isUpperCase(msgLow.charAt(j)) && Character.isLetter(msgLow.charAt(j))) {
            newMessage[j] = 1;
        }
        else {
            newMessage[j] = 0;
        }
    }
    return newMessage;
}

public static int percentageCaps(final int[] caps) {
    int sum = 0;
    for (int i = 0; i < caps.length; ++i) {
        sum += caps[i];
    }
    final double ratioCaps = sum / caps.length;
    final int percentCaps = (int)(100.0 * ratioCaps);
    return percentCaps;
}

public static int checkCapsInRow(final int[] caps) {
    int sum = 0;
    int sumTemp = 0;
    for (final int i : caps) {
        if (i == 1) {
            ++sumTemp;
            sum = Math.max(sum, sumTemp);
        }
        else {
            sumTemp = 0;
        }
    }
    return sum;
}

}
