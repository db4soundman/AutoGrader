import javax.xml.bind.annotation.XmlAttribute;

/**
 * 
 */

/**
 * The filepath to the Grader class in the build file
 * 
 * @author Doug Blase
 * 
 */
public class PathElement {

	private String location;

	/**
	 * @return the location
	 */
	@XmlAttribute(name = "location")
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
}
