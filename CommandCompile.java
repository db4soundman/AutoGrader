import java.io.File;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * 
 */

/**
 * @author Doug Blase
 * 
 */
public class CommandCompile {

	private String srcdir;
	private String destdir;

	/**
	 * @return the srcdir
	 */
	@XmlAttribute()
	public String getSrcdir() {
		return srcdir;
	}

	/**
	 * @param srcdir
	 *            the srcdir to set
	 */
	public void setSrcdir(String srcdir) {
		this.srcdir = srcdir;
	}

	/**
	 * @return the destdir
	 */
	@XmlAttribute()
	public String getDestdir() {
		return destdir;
	}

	/**
	 * @param destdir
	 *            the destdir to set
	 */
	public void setDestdir(String destdir) {
		this.destdir = destdir;
	}

	/**
	 * @param hwLoc
	 * @param string
	 */
	public void initializeCompiler(File hwLoc, String id) {
		this.destdir = srcdir = hwLoc.getPath() + "/" + id;
	}

}
