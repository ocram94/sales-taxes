package app.shopping.cart.utils;

import java.text.DecimalFormat;
import java.util.StringTokenizer;

public class FormatData {
	
	private static StringBuilder sb=new StringBuilder();
	private static DecimalFormat df = new DecimalFormat(".##");
	
	public static int getQta(String inputUser) {
		if(sb.length()!=0)
			sb=new StringBuilder();
		sb.append(inputUser);
		int a=sb.indexOf(" ");
		return Integer.parseInt(sb.substring(0,a));
	}
	
	public static double getPrice(String inputUser) {
		if(sb.length()!=0)
			sb=new StringBuilder();
		sb.append(inputUser);
		int da=sb.lastIndexOf(" ");
		int a=sb.length();
		
		double price=Double.parseDouble(sb.substring(da+1,a));
		return rounded(price);
	}
	
	public static double roundedCents(double number) {
		return Math.round(number*20.0)/20.0;
	}
	
	public static double rounded(double number) {
		return Double.parseDouble(df.format(number).replace(',', '.'));
	}
	
	public static boolean isImported(String inputUser) {
		StringTokenizer st=new StringTokenizer(inputUser," ");
		while(st.hasMoreTokens()) {
			if(st.nextToken().equals("imported")) {
				return true;
			}
		}
		return false;
	}
	
	public static String getDescription(String inputUser) {
		String temp="";
		if(isImported(inputUser))
			temp=inputUser.replace("imported ", "");
		else 
			temp=inputUser;
		
		if(sb.length()!=0)
			sb=new StringBuilder();
		sb.append(temp);
		
		int da=sb.indexOf(" ");
		int a=sb.lastIndexOf(" at ");
		return sb.substring(da+1,a);
	}
	
}
