import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * An AutoGrader to be used for CSE 271. Will compile student's java
 * files, copy the .class file to the file location of this program, 
 * and perform the specified tasks
 */

/**
 * @author Doug Blase
 * 
 */
public class AutoGrader {

	public static String[] uniqueIds;
	public static final String HW_ID = "HW9";

	/**
	 * @param args
	 * @throws Exception
	 *             Just because I don't want to surround the
	 *             printwriter with try/catch
	 */
	public static void main(String[] args) throws Exception {

		int halfOfClass;

		halfOfClass = Integer
				.parseInt(JOptionPane
						.showInputDialog("Which half of the class are you grading?"));

		File directory = new File(HW_ID);
		File[] d = directory.listFiles();
		uniqueIds = new String[(d.length / 2)];
		int indexOfFiles;
		if (halfOfClass == 1)
			indexOfFiles = 0;
		else indexOfFiles = d.length / 2;
		for (int i = 0; i < uniqueIds.length; i++, indexOfFiles++) {
			String name = d[indexOfFiles].getPath();
			if (name.contains(".DS_Store")) {
				i--;
			}
			else uniqueIds[i] = name.substring(name.indexOf("/") + 1);
		}

		int numOfFilesToCompile = Integer
				.parseInt(JOptionPane
						.showInputDialog("How many student Turnins need to be compiled?"));

		String[] fileNames = new String[numOfFilesToCompile];
		for (int i = 0; i < numOfFilesToCompile; i++)
			fileNames[i] = JOptionPane
					.showInputDialog("Enter name of the Java file WITHOUT the .java ending");

		for (String person : uniqueIds) {

			compileTurnins(person, fileNames);
			copyFiles(person);
		}
		createBuildFile();
		combineAndGatherReports();

		System.out.println("Grading completed.");
	}

	/**
	 * 
	 * @param fileName
	 *            The filename of the student's turnin. Can be
	 *            converted to an array if multiple files need to be
	 *            compiled
	 * @throws FileNotFoundException
	 *             Not worth try/catching a PrintWriter
	 */
	public static void compileTurnins(String person,
			String[] fileNames) throws FileNotFoundException {
		PrintWriter fileChecker = new PrintWriter(new File(
				"Missing Files.txt"));
		for (String fileName : fileNames) {
			try {
				JavaCompiler compiler = ToolProvider
						.getSystemJavaCompiler();
				DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
				StandardJavaFileManager fileManager = compiler
						.getStandardFileManager(diagnostics, null,
								null);
				Iterable<? extends JavaFileObject> compilationUnits = fileManager
						.getJavaFileObjectsFromStrings(Arrays
								.asList(HW_ID + "/" + person + "/"
										+ fileName + ".java"));
				JavaCompiler.CompilationTask task = compiler.getTask(
						null, fileManager, diagnostics, null, null,
						compilationUnits);
				if (!task.call()) {
					fileChecker.println(person
							+ " is missing a turnin file.");
				}
				fileManager.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		fileChecker.close();
	}

	/**
	 * A method to copy the file from the Turnin set from cascade to
	 * the directory of the autograder.
	 * 
	 * @param person
	 *            The uniqueID of the student
	 * @param fileName
	 *            The filename of the java file
	 * @return Whether or not the file was copied, indicating that
	 *         their filename did not match specifications, or did not
	 *         turn in a file
	 */
	public static void copyFiles(String person) {
		File hw = new File(HW_ID + "/" + person + "/Grader.class");
		File loc = new File("src/Grader.class");
		try {
			FileChannel inputChannel = null;
			FileChannel outputChannel = null;
			try {
				inputChannel = new FileInputStream(loc).getChannel();
				outputChannel = new FileOutputStream(hw).getChannel();
				outputChannel.transferFrom(inputChannel, 0,
						inputChannel.size());
			} finally {
				inputChannel.close();
				outputChannel.close();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void createBuildFile() {
		Project buildFile = new Project();
		buildFile.initialize(new File(HW_ID), uniqueIds);
		try {
			JAXBContext context = JAXBContext
					.newInstance(Project.class);
			Marshaller m;

			m = context.createMarshaller();

			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);

			m.marshal(buildFile, new File("build.xml"));
		}
		catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	public static void combineAndGatherReports() {
		System.out
				.println("Go run the build.xml (ant) file that is housed in the same location as this program.\nWhen this has completed, type something and press enter to collect the grade reports and remove traces of the program's work");
		Scanner kb = new Scanner(System.in);
		String random = kb.next();

		kb.close();
		PrintWriter testResults;
		testResults = null;
		try {
			testResults = new PrintWriter(
					new File("Test Results.txt"));
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (String student : uniqueIds) {
			File testRpt, jarFile;
			testRpt = new File(student + "Test reports.txt");
			jarFile = new File(student + "Grader.jar");
			try {
				Scanner errorReader = new Scanner(testRpt);
				while (errorReader.hasNextLine()) {
					testResults.println(errorReader.nextLine());
				}
				errorReader.close();
				testRpt.delete();
				// missingFileReport.delete();
				jarFile.delete();
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		testResults.close();
	}
}
