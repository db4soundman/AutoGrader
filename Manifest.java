import javax.xml.bind.annotation.XmlElement;

public class Manifest {
	private Attribute attribute;

	/**
	 * @return the attribute
	 */
	@XmlElement(name = "attribute")
	public Attribute getAttribute() {
		return attribute;
	}

	/**
	 * @param attribute
	 *            the attribute to set
	 */
	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public void initialize() {
		this.attribute = new Attribute();
		attribute.initialize();
	}
}
