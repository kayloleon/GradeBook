package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        /* Asks user to either view or edit grades
          runs reader or writer accordingly, if bad input it restarts
         */

        boolean rerun = false;
        do {
            System.out.println("Would you like to 'view' or 'edit' your grades?");
            String studentChoice = scan.nextLine().toUpperCase();
            //System.out.println(studentChoice);

            if (studentChoice.equals("VIEW")) {
                //System.out.println("runs grade display");
                GradeManagement.gradebookReader();
                System.exit(0);

            } else if (studentChoice.equals("EDIT")) {
                    GradeManagement.gradebookWriter();
                    rerun = false;

            } else {
                System.out.println("Bad Input, try again");
                rerun = true;
            }
        } while (rerun);

    }
}












