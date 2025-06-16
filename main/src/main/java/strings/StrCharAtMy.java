package strings;

/**
 * StrCharAt - show String.charAt()
 * 
 * @author Ian F. Darwin, https://darwinsys.com/
 * @updated by SS.
 */
// tag::main[]
public class StrCharAtMy {
	public static void main(String[] av) {
		String a = "A quick bronze fox";
		for (int i = 0; i < a.length(); i++) { // no forEach, need the index
			String message = "charAt is '%c', codePointAt is %3d, casted it's '%c', my codePointBefore is '%s'".formatted(
					a.charAt(i),
					a.codePointAt(i), 
					(char) a.codePointAt(i),
					a.substring(i));
			System.out.println(message);
		}
	}
}
// end::main[]
