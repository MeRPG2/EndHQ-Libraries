package net.endhq.util;

public class JavaUtil {
	public static int randBtw(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}
	public static boolean chance(int weight) {
		int num = randBtw(1, 100);
		if (num >= weight) return false;
		
		return true;
	}
}
