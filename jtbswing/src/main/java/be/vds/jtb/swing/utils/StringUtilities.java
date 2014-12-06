package be.vds.jtb.swing.utils;

public class StringUtilities {
	public static String padRight(String s, int n) {
		return String.format("%1$-" + n + "s", s);
	}

	public static String padLeft(long yournumber, int numberOfzeros) {
		return String.format("%0"+numberOfzeros+"d", yournumber);
	}

}
