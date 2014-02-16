/**
 * 
 */

/**
 * @author Doug Blase
 * 
 */
public class Student {

	private String name;
	private int numExams;
	private double totalScore;

	/**
	 * @param name
	 */
	public Student(String name) {
		this.name = name;
		this.numExams = 0;
		this.totalScore = 0.;
	}

	/**
	 * @param name
	 * @param numExams
	 * @param totalScore
	 */
	public Student(String name, int numExams, double totalScore) {
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

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}
}
