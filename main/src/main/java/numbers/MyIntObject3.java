package numbers;

public class MyIntObject3 {

	public static void main(String[] args) {
		
		//Primitive to Object 
		int i1 = Integer.valueOf(11);
		System.out.println("Primitive to Object: " + i1);
		
		
		//Object to primitive
		Integer intObj = 12;
		int i2  = intObj.intValue(); 
		System.out.println("Integer to primitive: " + i2);
	}

}
