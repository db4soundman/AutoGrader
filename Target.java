import java.io.File;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * 
 */

/**
 * Contains the target elements of the ant build file
 * 
 * @author Doug Blase
 * 
 */
public class Target {

	private String name;
	private String failOnError;
	private CommandJar[] jar;
	private CommandCompile[] compile;
	private Command[] java;
	private String depends;

	/**
	 * @return the name
	 */
	@XmlAttribute(name = "name")
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the failOnError
	 */
	@XmlAttribute(name = "failonerror")
	public String getFailOnError() {
		return failOnError;
	}

	/**
	 * @param failOnError
	 *            the failOnError to set
	 */
	public void setFailOnError(String failOnError) {
		this.failOnError = failOnError;
	}

	/**
	 * @return the java
	 */
	@XmlElement(name = "java")
	public Command[] getJava() {
		return java;
	}

	/**
	 * @param java
	 *            the java to set
	 */
	public void setJava(Command[] java) {
		this.java = java;
	}

	/**
	 * @return the depends
	 */
	@XmlAttribute()
	public String getDepends() {
		return depends;
	}

	/**
	 * @param depends
	 *            the depends to set
	 */
	public void setDepends(String depends) {
		this.depends = depends;
	}

	/**
	 * @return the jar
	 */
	@XmlElement(name = "jar")
	public CommandJar[] getJar() {
		return jar;
	}

	/**
	 * @param jar
	 *            the jar to set
	 */
	public void setJar(CommandJar[] jar) {
		this.jar = jar;
	}

	/**
	 * @return the compile
	 */
	@XmlElement(name = "javac")
	public CommandCompile[] getCompile() {
		return compile;
	}

	/**
	 * @param compile
	 *            the compile to set
	 */
	public void setCompile(CommandCompile[] compile) {
		this.compile = compile;
	}

	public void initialize(File hwLoc, String[] ids) {
		this.name = "java";
		this.depends = "jar";
		this.java = new Command[ids.length];
		for (int i = 0; i < java.length; i++) {
			this.java[i] = new Command();
			this.java[i].initialize(hwLoc, ids[i]);
		}
	}

	public void initializeJars(File hwLoc, String[] ids) {
		this.name = "jar";
		// this.depends = "compile";
		this.jar = new CommandJar[ids.length];
		for (int i = 0; i < jar.length; i++) {
			this.jar[i] = new CommandJar();
			this.jar[i].initializeJars(hwLoc, ids[i]);
		}
	}

	/**
	 * @param hwLoc
	 * @param ids
	 */
	public void initializeCompiler(File hwLoc, String[] ids) {
		// this.name = "compile";
		// this.failOnError = "false";
		// this.compile = new CommandCompile[ids.length];
		// for (int i = 0; i < compile.length; i++) {
		// this.compile[i] = new CommandCompile();
		// this.compile[i].initializeCompiler(hwLoc, ids[i]);
		// }
	}

}
