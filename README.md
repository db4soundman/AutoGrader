AutoGrader
==========
To use the auto grader, set up an eclipse project first where you will run this program from. Then, download the files from this site (sorry for the .class files, you don’t need to add those to eclipse). I have eclipse sending both the binary files (.class) and the regular .java files to the same folder because I thought it would make things easier for this program, though in the long run I don’t think I needed to do this.

When creating the grader, you need to have any supplementary source code classes present and created in Eclipse, as well as a dummy file for the student turnin so that the use of java.reflect does not cause errors in eclipse.

When running the program, you need to give the program the name (and number) of every file that is part of the turnin set. So, for example, in HW1, besides PokerStats, there was also a Card class and a Deck class. The program needs to know that all three of those files exist and need to be compiled. (Maybe just giving it PokerStats would compile the others???) Regardless, follow the on-screen prompts to complete the grading of the homework.