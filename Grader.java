import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * 
 */

/**
 * @author Doug Blase
 * 
 */
public class Grader {

	public static void main(String[] args) {

		try {
			PrintWriter performanceReport = new PrintWriter(new File(
					args[0] + "Test reports.txt"));
			performanceReport.println("Test Results for " + args[0]);
			performTests(performanceReport);
			performanceReport.close();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Calls specific methods that are supposed to me in the student's
	 * homework and runs tests to verify output and other expected
	 * behavior.
	 * 
	 * Recommended Strategy: check for method presence and return
	 * type, then perform tests on actual method.
	 * 
	 * @param reporter
	 *            Writes the results of the tests to a text file.
	 */
	public static void performTests(PrintWriter reporter) {

		int errorCount = 0;
		String test = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int result;
		result = WordPlay.vowelCount(test);
		if (result != 5) {
			errorCount++;
			reporter.println("Error in vowelCount for " + test + ": "
					+ result);
		}

		test = "HMM";
		result = WordPlay.vowelCount(test);
		if (result != 0) {
			errorCount++;
			reporter.println("Error in vowelCount for " + test + ": "
					+ result);
		}
		test = "AA";
		result = WordPlay.indexOfFirstVowel(test);
		if (result != 0) {
			errorCount++;
			reporter.println("Error in indexOfFirstVowel for " + test
					+ ": " + result);
		}
		test = "ZZZ";
		result = WordPlay.indexOfFirstVowel(test);
		if (result != -1) {
			errorCount++;
			reporter.println("Error in indexOfFirstVowel for " + test
					+ ": " + result);
		}
		test = "SHWA";
		result = WordPlay.indexOfFirstVowel(test);
		if (result != 3) {
			errorCount++;
			reporter.println("Error in indexOfFirstVowel for " + test
					+ ": " + result);
		}
		test = "MOUSE";
		String PLResult;
		PLResult = WordPlay.pigLatinize(test);
		if (!PLResult.equals("OUSEMAY")) {
			errorCount++;
			reporter.println("Error in pigLatinization for " + test
					+ ": " + PLResult);
		}
		test = "SCRAPPY";
		PLResult = WordPlay.pigLatinize(test);
		if (!PLResult.equals("APPYSCRAY")) {
			errorCount++;
			reporter.println("Error in pigLatinization for " + test
					+ ": " + PLResult);
		}
		test = "HMM";
		PLResult = WordPlay.pigLatinize(test);
		if (!PLResult.equals("HMMAY") && !PLResult.equals("MMHAY")) {
			errorCount++;
			reporter.println("Error in pigLatinization for " + test
					+ ": " + PLResult);
		}
		test = "ABCDE";
		PLResult = WordPlay.swapEnds(test);
		if (!PLResult.equals("EBCDA")) {
			errorCount++;
			reporter.println("Error in swapEnds for " + test + ": "
					+ PLResult);
		}
		test = "BY";
		PLResult = WordPlay.swapEnds(test);
		if (!PLResult.equals("YB")) {
			errorCount++;
			reporter.println("Error in swapEnds for " + test + ": "
					+ PLResult);
		}
		test = "ABCDE";
		PLResult = WordPlay.reverse(test);
		if (!PLResult.equals("EDCBA")) {
			errorCount++;
			reporter.println("Error in reverse for " + test + ": "
					+ PLResult);
		}
		test = "BY";
		PLResult = WordPlay.reverse(test);
		if (!PLResult.equals("YB")) {
			errorCount++;
			reporter.println("Error in reverse for " + test + ": "
					+ PLResult);
		}

		reporter.println("Error count: " + errorCount);
		reporter.println();
		reporter.println();
		reporter.close();
	}

}
