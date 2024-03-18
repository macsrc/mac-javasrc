package numbers;

public class MyStringToDouble2 {

	public static void main(String[] argv) {
		String aNumber = argv[1];
		double d;
		try {
			d = Double.parseDouble(aNumber);
			System.out.println("Double value = " + d);
		} catch (NumberFormatException e) {
			System.out.println("Not a valid number: " + aNumber);
			e.printStackTrace();
			return;
		}

	}

}
