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
public class CommandJar {

	private String basedir;
	private String destfile;
	private Manifest manifest;

	/**
	 * @return the manifest
	 */
	@XmlElement(name = "manifest")
	public Manifest getManifest() {
		return manifest;
	}

	/**
	 * @param manifest
	 *            the manifest to set
	 */
	public void setManifest(Manifest manifest) {
		this.manifest = manifest;
	}

	/**
	 * @return the basedir
	 */
	@XmlAttribute()
	public String getBasedir() {
		return basedir;
	}

	/**
	 * @param basedir
	 *            the basedir to set
	 */
	public void setBasedir(String basedir) {
		this.basedir = basedir;
	}

	@XmlAttribute()
	public String getDestfile() {
		return destfile;
	}

	/**
	 * @param destfile
	 *            the destfile to set
	 */
	public void setDestfile(String destfile) {
		this.destfile = destfile;
	}

	public void initializeJars(File hwLoc, String id) {
		this.basedir = hwLoc.getPath() + "/" + id;
		this.destfile = id + "Grader.jar";
		this.manifest = new Manifest();
		manifest.initialize();
	}
}
