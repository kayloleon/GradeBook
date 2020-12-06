package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Asks student for class info and grades, writes to and reads from 'gradebook.txt'
public class GradeManagement {

    // Hashmap of grades, used in later methods
    private static HashMap<String, Double> g;

    // Asks student for name, used later on in writer
    public static String studentName( ) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your name:");
        String studentName = scan.nextLine();
        //System.out.println(studentName);
        return studentName;
    }

    /**
     * Asks user to pick from list of classes, available classes are listed
     * How many of these classes to add is up to user
     * @return class which will be used in file writer
     */

    protected static String classSelected() {
        Scanner scan = new Scanner(System.in);

        ArrayList<String> possibleClasses = new ArrayList<String>();
        possibleClasses.add("MATH");
        possibleClasses.add("SCIENCE");
        possibleClasses.add("READING");
        possibleClasses.add("SOCIAL STUDIES");

        boolean classExists = true;

        String inputClass = "";
        do {
            System.out.println("Please choose a class: Math, Science, Reading, Social Studies");
            inputClass = scan.nextLine().toUpperCase();

            if (possibleClasses.contains(inputClass)) {
                return inputClass;
            } else {
                classExists = false;
                System.out.println("Class does not exist or is misspelled, try again.");
            }

        } while (!classExists);

        return inputClass;

    }

    /** Asks user for grades in chosen class
     * Types of assignments are asked individually
     * User will be able to keep putting in numbers until '-1' is inputted
     * Any invalid input will not be counter and user will be notified
     * @return a HasMap with assignment type as key and average score for that assignment as value
     */

    protected static HashMap<String, Double> classGrades() {

        Scanner scan = new Scanner(System.in);

        HashMap<String, Double> assignmentGrade = new HashMap<>();

        System.out.println("Enter grades for selected class, only valid inputs will be counted (0-100)");


        // Typing '-1' stops the loop
        System.out.println("Enter QUIZ grades:" + "\nType '-1' when done with all quizzes.");
        boolean continueQuizInput = true;
        int quizGradesAdded = 0;
        double i = 0;

        do {

            double quizGrade = scan.nextDouble();

            if (quizGrade >= 0 && quizGrade <= 100) {
                i++;
                quizGradesAdded += quizGrade;
            }
            else if (quizGrade == (-1)) {
                continueQuizInput = false;
                break;
            }
            else {
                System.out.println("Grade not counted, input a number from 0 to 100");
            }

        } while (continueQuizInput);

        double averageQuizGrade = quizGradesAdded/i;



        System.out.println("Enter EXAM grade:" + "\nType '-1' when done with all exams.");
        boolean continueExamInput = true;
        int examGradesAdded = 0;
        double j = 0;

        do {

            double examGrade = scan.nextDouble();

            if (examGrade >= 0 && examGrade <= 100) {
                j++;
                examGradesAdded += examGrade;
            }
            else if (examGrade == (-1)) {
                continueExamInput = false;
                break;
            }
            else {
                System.out.println("Grade not counted, input a number from 0 to 100");
            }

        } while (continueExamInput);

        double averageExamGrade = examGradesAdded/j;



        System.out.println("Enter HOMEWORK grade:" + "\nType '-1' when done with all homework.");
        boolean continueHomeworkInput = true;
        int homeworkGradesAdded = 0;
        double k = 0;

        do {

            double homeworkGrade = scan.nextDouble();

            if (homeworkGrade >= 0 && homeworkGrade <= 100) {
                k++;
                homeworkGradesAdded += homeworkGrade;
            }
            else if (homeworkGrade == (-1)) {
                continueHomeworkInput = false;
                break;
            }
            else {
                System.out.println("Grade not counted, input a number from 0 to 100");
            }

        } while (continueHomeworkInput);

        double averageHomeworkGrade = homeworkGradesAdded/k;



        System.out.println("Enter PROJECT grade:" + "\nType '-1' when done with all projects.");
        boolean continueProjectInput = true;
        int projectGradesAdded = 0;
        double l = 0;

        do {

            double projectGrade = scan.nextDouble();

            if (projectGrade >= 0 && projectGrade <= 100) {
                l++;
                projectGradesAdded += projectGrade;
            }
            else if (projectGrade == (-1)) {
                continueProjectInput = false;
                break;
            }
            else {
                System.out.println("Grade not counted, input a number from 0 to 100");
            }

        } while (continueProjectInput);

        double averageProjectGrade = projectGradesAdded/l;

        // average grades are calculated above and assigned below

        assignmentGrade.put("Quiz Average", averageQuizGrade);
        assignmentGrade.put("Exam Average", averageExamGrade);
        assignmentGrade.put("Homework Average", averageHomeworkGrade);
        assignmentGrade.put("Project Average", averageProjectGrade);

        return assignmentGrade;
    }

    /**
     * Takes grades (int) from assignmentGrade
     * Multiplies each value by the percentage of weight
     * @return the sum of grades after being weighed
     */

    protected static double gradesWeighted() {

        double weightedQuiz = g.get("Quiz Average") * .20;
        double weightedExam = g.get("Exam Average") * .30;
        double weightedHomework = g.get("Homework Average") * .25;
        double weightedProject = g.get("Project Average") * .25;

        return weightedQuiz + weightedExam + weightedHomework + weightedProject;
    }

    /**
     * Creates a file writer, adds studentName at the first line
     * writes down class, grade for each assignment, and weighted score once
     * if user wants to add more classes, everything except for name will loop
     * and be witten again
     * @throws IOException
     */

    protected static void gradebookWriter() throws IOException {
        Scanner scan = new Scanner(System.in);

        FileWriter writer = new FileWriter("src/gradebook.txt");
        BufferedWriter infoWrite = new BufferedWriter(writer);
        infoWrite.write("Grades for " + studentName() + "\n");

        boolean addClass = false;
        do {

            infoWrite.write(classSelected() + " ");
            g = classGrades();
            infoWrite.write(String.valueOf(g));
            infoWrite.write(" Class grade: " + gradesWeighted());
            infoWrite.newLine();

            System.out.println("Would you like to add another class? (Y/N)");
            String proceed = scan.nextLine().toUpperCase();
            if (proceed.startsWith("Y")) {
                addClass = true;
            } else {
                infoWrite.close();
                System.exit(0);
            }

        } while (addClass);

    }

    /**
     * After the loops are done by gradebookWriter, Main class runs
     * gradebookReader, which prints all lines in 'gradebook.txt'
.     */

    protected static void gradebookReader() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/gradebook.txt"));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
            }
        catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
