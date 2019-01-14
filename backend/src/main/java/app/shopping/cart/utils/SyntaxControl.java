package app.shopping.cart.utils;

public class SyntaxControl {

	//User input formatting control
	public static boolean control(String inputUser) {
		String pattern="[0-9]+\\s(\\w|\\s)+\\s(at)\\s[0-9]+(\\.[0-9]+)?";
		if(inputUser.matches(pattern)) {
			return true;
		}
		return false;
	}
	
	
}
