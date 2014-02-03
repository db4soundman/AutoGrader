//import grader.Grader;
import java.io.File;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * 
 */

/**
 * @author Doug Blase
 * 
 */
public class Command {

	private String className;
	private String fork;
	private Classpath classpath;

	// private String classpathref;

	/**
	 * @return the destfile
	 */

	/**
	 * @return the className
	 */
	@XmlAttribute(name = "jar")
	public String getClassName() {
		return className;
	}

	/**
	 * @param className
	 *            the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	@XmlAttribute(name = "fork")
	public String getFork() {
		return fork;
	}

	public void setFork(String f) {
		fork = f;
	}

	/**
	 * @return the classpath
	 */
	@XmlElement(name = "arg")
	public Classpath getClasspath() {
		return classpath;
	}

	/**
	 * @param classpath
	 *            the classpath to set
	 */
	public void setClasspath(Classpath classpath) {
		this.classpath = classpath;
	}

	public void initialize(File hwLoc, String id) {
		this.fork = "true";
		this.className = id + "Grader.jar";
		this.classpath = new Classpath();
		this.classpath.initialize(id);
		// try {
		// this.classpath.initialize(hwLoc, id);
		// }
		// catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

}
