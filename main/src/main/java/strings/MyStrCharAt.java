package strings;

public class MyStrCharAt {
	public static void main(String[] args) {
		System.out.println("check for each chars!");
		String str = "check for each chars!";
					
		for (char charv : str.toCharArray()) {
			System.out.print(charv + " ");
		}
		
		System.out.println("Using Streams:");
		str.chars().forEach(c -> System.out.print((char)c + " "));
	}
}

