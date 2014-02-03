import javax.xml.bind.annotation.XmlAttribute;

/**
 * 
 */

/**
 * Holds the location of the class
 * 
 * @author Doug Blase
 * 
 */
public class Classpath {

	private String pathelement;

	/**
	 * @return the pathelement
	 */
	@XmlAttribute(name = "value")
	/**
	 * @return the pathelement
	 */
	public String getPathelement() {
		return pathelement;
	}

	/**
	 * @param pathelement
	 *            the pathelement to set
	 */
	public void setPathelement(String pathelement) {
		this.pathelement = pathelement;
	}

	// public void initialize(File hwLoc, String id) throws
	// IOException {
	// this.pathelement = new PathElement();
	// this.pathelement.setLocation(hwLoc.getPath() + "/" + id);
	// }

	public void initialize(String id) {
		this.pathelement = id;
	}
}
