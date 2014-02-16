import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 
 */

/**
 * @author Doug Blase
 * 
 */
public class Grader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintWriter performanceReport = null;
		try {
			performanceReport = new PrintWriter(new File(args[0]
					+ "Test reports.txt"));
			performanceReport.println("Test Results for " + args[0]);
			verifyClassAndMethodExistence(performanceReport);
			performTests(performanceReport);
			performanceReport.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			performanceReport
					.print("Missing a method...or something else.");
			performanceReport.println(e.getMessage());
		}
	}

	/**
	 * 
	 * Recommended Strategy: check for method presence and return
	 * type, then perform tests on actual method.
	 * 
	 * @param reporter
	 */
	public static void verifyClassAndMethodExistence(
			PrintWriter reporter) {
		try {
			Class<?> s = Class.forName("Student");

			// Checking for method presence and return type...........
			try {
				Method m = s.getMethod("addExamGrade", double.class);

				m = s.getMethod("getExamCount", null);
				if (!m.getReturnType().equals(int.class))
					reporter.println("The return type for "
							+ m.getName() + " is:"
							+ m.getReturnType() + "instead of int.");

				m = s.getMethod("getAverage", null);
				if (!m.getReturnType().equals(double.class))
					reporter.println("The return type for "
							+ m.getName() + " is:"
							+ m.getReturnType()
							+ "instead of double.");

				m = s.getMethod("getLetterGrade", null);
				if (!m.getReturnType().equals(char.class))
					reporter.println("The return type for "
							+ m.getName() + " is:"
							+ m.getReturnType() + "instead of char.");

				m = s.getMethod("getName", null);
				if (!m.getReturnType().equals(String.class))
					reporter.println("The return type for "
							+ m.getName() + " is:"
							+ m.getReturnType()
							+ "instead of String.");

				m = s.getMethod("toString", null);
				if (!m.getReturnType().equals(String.class))
					reporter.println("The return type for "
							+ m.getName() + " is:"
							+ m.getReturnType()
							+ "instead of String.");

			}
			catch (NoSuchMethodException e) {
				reporter.println(e.toString());
			}

		}
		catch (SecurityException e) {
			e.printStackTrace();
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Calls specific methods that are supposed to be in the student's
	 * homework and runs tests to verify output and other expected
	 * behavior.
	 * 
	 * @param reporter
	 *            Writes the results of the tests to a text file.
	 */
	public static void performTests(PrintWriter reporter) {
		StudentMaster sm = new StudentMaster("Doug");
		Student s = new Student("Doug");

		for (int i = 0; i < 101; i++) {
			s.addExamGrade(79.9);
			sm.addExamGrade(79.9);
		}

		reporter.print(sm.equals(s));

		sm = new StudentMaster("Doug");
		s = new Student("Doug");

		for (int i = 0; i < 101; i++) {
			s.addExamGrade(80.1);
			sm.addExamGrade(80.1);
		}

		sm = new StudentMaster("Doug");
		s = new Student("Doug");

		for (int i = 0; i < 101; i++) {
			s.addExamGrade(80.9);
			sm.addExamGrade(80.9);
		}

		reporter.print(sm.equals(s));

		sm = new StudentMaster("Doug");
		s = new Student("Doug");

		for (int i = 0; i < 101; i++) {
			s.addExamGrade(90.1);
			sm.addExamGrade(90.1);
		}

		reporter.print(sm.equals(s));

		sm = new StudentMaster("Doug");
		s = new Student("Doug");

		for (int i = 0; i < 101; i++) {
			s.addExamGrade(70.1);
			sm.addExamGrade(70.1);
		}

		reporter.print(sm.equals(s));

		sm = new StudentMaster("Doug");
		s = new Student("Doug");

		for (int i = 0; i < 101; i++) {
			s.addExamGrade(69.9);
			sm.addExamGrade(69.9);
		}

		sm = new StudentMaster("Doug");
		s = new Student("Doug");

		for (int i = 0; i < 101; i++) {
			s.addExamGrade(60.1);
			sm.addExamGrade(60.1);
		}

		reporter.print(sm.equals(s));

		sm = new StudentMaster("Doug");
		s = new Student("Doug");

		for (int i = 0; i < 101; i++) {
			s.addExamGrade(59.9);
			sm.addExamGrade(59.9);
		}

		reporter.print(sm.equals(s));

		s.addExamGrade(-20.);
		sm.addExamGrade(-20.);
		reporter.print(sm.equals(s));

		sm = new StudentMaster("Doug");
		s = new Student("Doug");

		for (int i = 0; i < 12345; i++) {
			double num = Math.random() * 100;
			s.addExamGrade(num);
			sm.addExamGrade(num);
		}

		reporter.print(sm.equals(s));

		s = new Student("Daniel", 15, 333.);
		sm = new StudentMaster("Daniel", 15, 333.);

		reporter.print(sm.equals(s));

		s.addExamGrade(-20);
		sm.addExamGrade(-20);
		reporter.print(sm.equals(s));

		reporter.println();
		reporter.println();
	}
}
