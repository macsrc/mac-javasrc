package structure;

import java.util.Arrays;

//void main() {
//// tag::main[]
//record Cat(String name){
//	public String toString() { return "Meower(" + name + ")";}
//}
//Cat[] pets = new Cat[]{new Cat("Tom"), new Cat("Slinky")};
//System.out.println("Pets are " + Arrays.toString(pets));
//// end::main[]
//}

public class ArraysToString {

	// top-level record
	record Cat(String name) {
		@Override
		public String toString() {
			return "Meower(" + name + ")";
		}
	}

	public static void main(String[] args) {
		Cat[] pets = new Cat[] { new Cat("Tom"), new Cat("Slinky") };
		System.out.println("Pets are " + Arrays.toString(pets));
	}
}
