package strings;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintStream;

public class GetMark {
	/** the default starting mark */
	public final String START_MARK = "//+";
	/** the default ending mark */
	public final String END_MARK = "//-";

	/**
	 * Set this to TRUE for running in "exclude" mode (e.g., for building exercises
	 * from solutions) and to FALSE for running in "extract" mode (e.g., writing a
	 * book and omitting the imports and "public class" stuff).
	 */
	public final static boolean START = true;
	/** True if we are currently inside marks */
	protected boolean printing = START;
	/** True if you want line numbers */
	protected final boolean number = false;

	/**
	 * Get Marked parts of one file, given an open LineNumberReader. This is the
	 * main operation of this class, and can be used inside other programs or from
	 * the main() wrapper.
	 */
	public void process(String fileName, LineNumberReader is, PrintStream out) {
		int nLines = 0;
		try {
			String inputLine;
			while ((inputLine = is.readLine()) != null) {
				if (inputLine.trim().equals(START_MARK)) {
					if (printing)
						// These go to stderr, so you can redirect the output
						System.err.println("ERROR: START INSIDE START, " + fileName + ':' + is.getLineNumber());
					printing = true;
				} else if (inputLine.trim().equals(END_MARK)) {
					if (!printing)
						System.err.println("ERROR: STOP WHILE STOPPED, " + fileName + ':' + is.getLineNumber());
					printing = false;
				} else if (printing) {
					if (number) {
						out.print(nLines);
						out.print(": ");
					}
					out.println(inputLine);
					++nLines;
				}
			}
			is.close();
			out.flush(); // Must not close - caller may still need it.
			if (nLines == 0)
				System.err.println("ERROR: No marks in " + fileName + "; no output generated!");
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}
	}
}