package numbers;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.darwinsys.io.FileIO;

/** Print "n" calls to nextDouble() and nextGaussian() in raw form
 * using java.util.Random.next*(); results are plotted using
 * the "R" script randomnesshistograms5.r via javax.script and "renjin"
 */
public class Random5 {
	// tag::main[]
	private static final String R_SCRIPT_FILE = "/randomnesshistograms.r";
	private static final String PLOT_FILE = "randomness.png";
	private static int N = 100_000;

	public static void main(String[] argv) throws Exception {
		// java.util.Random methods are non-static; we need an instance
		if (argv.length == 1) {
			try {
				N = Integer.parseInt(argv[0]);
			} catch (NumberFormatException ex) {
				System.out.printf("Number %s invalid, using %d\n", argv[0], N);
			}
		}
		System.out.println("Generating " + N + " randoms");
		Random r = new Random();
		double[] us = new double[N], ns = new double[N];
		for (int i=0; i<N; i++) {
			us[i] = r.nextDouble();
			ns[i] =r.nextGaussian();
		}
		System.out.println("Generating histograms");
		try (InputStream is = 
			Random5.class.getResourceAsStream(R_SCRIPT_FILE)) {
			if (is == null) {
				throw new IllegalStateException("Can't open R file ");
			}
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("Renjin");
			engine.put("us", us);
			engine.put("ns", ns);
			engine.eval(FileIO.readerToString(new InputStreamReader(is)));
		}
		System.out.println("Done; output in " + PLOT_FILE);
	}
	// end::main[]
}
