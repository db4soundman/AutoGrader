import java.io.File;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 */

/**
 * The root element of the ant build script
 * 
 * @author Doug Blase
 * 
 */
@XmlRootElement(name = "project")
public class Project {
	private String name;
	private String defaultTask;
	private Target[] tar;

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
	 * @return the defaultTask
	 */
	@XmlAttribute(name = "default")
	public String getDefaultTask() {
		return defaultTask;
	}

	/**
	 * @param defaultTask
	 *            the defaultTask to set
	 */
	public void setDefaultTask(String defaultTask) {
		this.defaultTask = defaultTask;
	}

	/**
	 * @return the tar
	 */
	@XmlElement(name = "target")
	public Target[] getTar() {
		return tar;
	}

	/**
	 * @param tar
	 *            the tar to set
	 */
	public void setTar(Target[] tar) {
		this.tar = tar;
	}

	public void initialize(File hwLoc, String[] ids) {
		this.name = "RunGrader";
		this.defaultTask = "java";
		this.tar = new Target[2];
		for (int i = 0; i < 2; i++)
			tar[i] = new Target();
		tar[1].initializeJars(hwLoc, ids);
		tar[0].initialize(hwLoc, ids);
	}

}
