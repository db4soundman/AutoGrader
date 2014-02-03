import javax.xml.bind.annotation.XmlAttribute;

/**
 * 
 */

/**
 * @author Doug Blase
 * 
 */
public class Attribute {
	private String name;
	private String value;

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
	 * @return the value
	 */
	@XmlAttribute(name = "value")
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	public void initialize() {
		this.name = "Main-Class";
		this.value = "Grader";
	}
}
