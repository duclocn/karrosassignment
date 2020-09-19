package util;

public class webUtils {

	public static boolean performTextValidattion(String source, String target) {
		if(source.compareToIgnoreCase(target) !=0) {
			System.out.println(source + " does not match with " + target);
			return false;
		}else {
			return true;
		}		
	}

}
