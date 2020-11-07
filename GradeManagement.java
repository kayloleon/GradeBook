package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class GradeManagement {

    // Hashmap of grades, used in later methods
    private static HashMap<String, Integer> g;

    // Asks student for name, used later on in writer
    public static String studentName( ) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your name:");
        String studentName = scan.nextLine();
        //System.out.println(studentName);
        return studentName;
    }

    /**
     * Asks user to pick from list of classes, not restricted
     * Additional classes will go through
     * @return class which will be used in file writer
     */

    protected static String classSelected() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Please choose a class: Math, Science, Reading");
        String inputClass = scan.nextLine().toUpperCase();

        //System.out.println(inputClass);

        return inputClass;
    }

    /** Asks user for grades in chosen class
     * Types of assignments are asked individually
     * @return a HasMap with assignment as key
     */

    protected static HashMap<String, Integer> classGrades() {

        Scanner scan = new Scanner(System.in);

        HashMap<String, Integer> assignmentGrade = new HashMap<>();

        System.out.println("Grades for selected class (0-100)");

        System.out.println("Enter QUIZ grade:");
        int quizGrade = scan.nextInt();
        System.out.println("Enter EXAM grade:");
        int examGrade = scan.nextInt();
        System.out.println("Enter HOMEWORK grade:");
        int homeworkGrade = scan.nextInt();
        System.out.println("Enter PROJECT grade:");
        int projectGrade = scan.nextInt();

        assignmentGrade.put("quiz", quizGrade);
        assignmentGrade.put("exam", examGrade);
        assignmentGrade.put("homework", homeworkGrade);
        assignmentGrade.put("project", projectGrade);

        //System.out.println(assignmentGrade);


        return assignmentGrade;
    }

    /**
     * Takes grades (int) from assignmentGrade
     * Multiplies each value by the percentage of weight
     * @return the sum of grades after being weighed
     */

    protected static double gradesWeighted() {

        double weightedQuiz = g.get("quiz") * .20;
        double weightedExam = g.get("exam") * .30;
        double weightedHomework = g.get("homework") * .25;
        double weightedProject = g.get("project") * .25;

        return weightedQuiz + weightedExam + weightedHomework + weightedProject;
    }

    /**
     * Creates a file writer, adds studentName at the first line
     * writes down class, grade for each assignment, and weighted score once
     * if user wants to add more classes, everything except for name will loop
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
     * After the loops done by the writer, reader reads all lines.
     */

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



