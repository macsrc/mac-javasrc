package strings;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

/* Align a page number on a 70-character line. */
// tag::main[]
public class MyStringAlignTest extends Format {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		// Construct a "formatter" to center strings.
		MyStringAlignTest formatter = new MyStringAlignTest(70, MyStringAlignTest.Justify.CENTER);
		// Try it out, for page "i"
		System.out.println(formatter.format("- i -"));
		// Try it out, for page 4. Since this formatter is
		// optimized for Strings, not specifically for page numbers,
		// we have to convert the number to a String
		System.out.println(formatter.format(Integer.toString(4)));
	}

	public enum Justify {
		/* Constant for left justification. */
		LEFT,
		/* Constant for centering. */
		CENTER,
		/** Constant for right-justified Strings. */
		RIGHT,
	}

	/** Current justification */
	private Justify just;
	/** Current max length */
	private int maxChars;

	public MyStringAlignTest(int maxChars, Justify just) {
		switch (just) {
		case LEFT:
		case CENTER:
		case RIGHT:
			this.just = just;
			break;
		default:
			throw new IllegalArgumentException("invalid justification arg.");
		}
		if (maxChars < 0) {
			throw new IllegalArgumentException("maxChars must be positive.");
		}
		this.maxChars = maxChars;
	}

	@Override
	public StringBuffer format(Object input, StringBuffer where, FieldPosition ignore) {

		String s = input.toString();
		String wanted = s.substring(0, Math.min(s.length(), maxChars));
		System.out.println("where-maxchars-wanted : " + where.length() + " " + maxChars + " " + wanted.length() );

		// Get the spaces in the right place.
		switch (just) {
		case RIGHT:
			pad(where, maxChars - wanted.length());
			where.append(wanted);
			System.out.println(" opR : " + where.toString());
			break;
		case CENTER:
			int toAdd = maxChars - wanted.length();
			pad(where, toAdd / 2);
			where.append(wanted);
			System.out.println(" opC1 : " + where.length());
			pad(where, toAdd - toAdd / 2);
			System.out.println(" opC2 : " + where.length());
			break;
		case LEFT:
			where.append(wanted);
			pad(where, maxChars - wanted.length());
			System.out.println(" opL : " + where.toString());
			break;
		}
		return where;
	}

	protected final void pad(StringBuffer to, int howMany) {
		for (int i = 0; i < howMany; i++)
			to.append(' ');
	}

	/** Convenience Routine */
	String format(String s) {
		return format(s, new StringBuffer(), null).toString();
	}

	/** ParseObject is required, but not useful here. */
	public Object parseObject(String source, ParsePosition pos) {
		return source;
	}
}
// end::main[]
