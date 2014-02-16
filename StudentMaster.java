/**
 * 
 */

/**
 * Dummy class for AutoGrader creation.
 * 
 * @author Doug Blase
 * 
 */
public class StudentMaster {

	private String name;
	private int numExams;
	private double totalScore;

	/**
	 * @param name
	 */
	public StudentMaster(String name) {
		this.name = name;
		this.numExams = 0;
		this.totalScore = 0.;
	}

	public static void main(String[] args) {
		StudentMaster d = new StudentMaster("Doug");
		d.addExamGrade(98.1);
		System.out.println(d.toString());

		Student f = new Student("Doug");
		f.addExamGrade(98.1);
		System.out.println(f.toString());
		System.out.println(d.equals(f));
	}

	/**
	 * @param name
	 * @param numExams
	 * @param totalScore
	 */
	public StudentMaster(String name, int numExams, double totalScore) {
		this.name = name;
		this.numExams = numExams;
		this.totalScore = totalScore;
	}

	public void addExamGrade(double grade) {
		numExams++;
		totalScore += grade;
	}

	public int getExamCount() {
		return numExams;
	}

	public String getName() {
		return name;
	}

	public double getAverage() {
		return totalScore / numExams;
	}

	public char getLetterGrade() {
		if (numExams == 0)
			return 'I';
		if (getAverage() >= 90)
			return 'A';
		if (getAverage() >= 80)
			return 'B';
		if (getAverage() >= 70)
			return 'C';
		if (getAverage() >= 60)
			return 'D';
		return 'F';

	}

	public String toString() {
		StringBuilder info = new StringBuilder();
		info.append(name + ", " + numExams);

		if (numExams == 1)
			info.append(" exam, ");
		else info.append(" exams, ");

		if (this.getLetterGrade() == 'I')
			info.append("no grade yet");
		else info.append(this.getAverage() + ", "
				+ this.getLetterGrade());

		return info.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public String equals(Student stu) {
		String x = "";
		if (!name.equals(stu.getName())) {
			x += "Names don't match\n";
		}
		if (numExams != stu.getExamCount())
			x += "Number of exams don't match\nI have "
					+ getExamCount() + " while the student has "
					+ stu.getExamCount() + "\n";
		if (getAverage() != stu.getAverage())
			x += "Averages don't match\nI have " + getAverage()
					+ " while the student has " + stu.getAverage()
					+ "\n";

		if (getLetterGrade() != stu.getLetterGrade())
			x += "Letter grades don't match\n I have "
					+ getLetterGrade() + ", while the student has "
					+ stu.getLetterGrade() + "\n";

		if (!this.toString().equals(stu.toString()))
			x += "To string does not match. I have:\n" + toString()
					+ "\n while they have\n" + stu.toString() + "\n";

		return x;
	}

}
