/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkgfinal;


import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author DELL
 */
public class Final {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
        MyList studentList = new MyList();
        MyStack studentStack = new MyStack();
        int section = 0;
        while (section != 8)
        {
            System.out.println("MENU");
            System.out.println("1. Add Student");
            System.out.println("2. Display Student");
            System.out.println("3. Edit Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Sort Student");
            System.out.println("6. Search Student");
            System.out.println("7. Stack");
            System.out.println("8. Exit");

            // Add try-catch block to handle invalid input
            boolean validInput = false;
            while (!validInput) {
                System.out.println("Enter the choice: ");
                try {
                    section = sc.nextInt();
                    if (section < 1 || section > 8) {
                        System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                    } else {
                        validInput = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    sc.nextLine(); // Consume the invalid input
                }
            }

            switch(section)
            {
                case 1:
                    studentList.addStudentFromKeyBoard();
                    System.out.println("Student added successfully!");
                    break;

                case 2:
                    studentList.traverse();
                    break;

                case 3:
                    // Handling potential input errors for the edit student case
                    try {
                        int id = -1;
                        boolean validId = false;
                        while (!validId) {
                            System.out.println("Enter the ID of the student to update: ");
                            try {
                                id = sc.nextInt();
                                if (id <= 0) {
                                    System.out.println("Invalid ID! Please enter a positive number.");
                                } else {
                                    validId = true;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input! Please enter a valid number.");
                                sc.nextLine(); // Consume invalid input
                            }
                        }

                        sc.nextLine();  // Consume the newline left by nextInt()


                        String newName = "";
                        boolean validName = false;
                        while (!validName) {
                            System.out.println("Enter the new name of the student (letters and spaces only): ");
                            newName = sc.nextLine().trim();

                            // Debug: in ra giá trị nhập vào
                            System.out.println("DEBUG: You entered -> '" + newName + "'");


                            if (!newName.matches("^[a-zA-Z\\s]+$")) {
                                System.out.println("Invalid name! Please enter a name with only letters and spaces.");
                            } else {
                                validName = true;
                            }
                        }


                        // Repeatedly ask for valid mark input between 0 and 10
                        double newMark = -1;
                        boolean validMark = false;
                        while (!validMark) {
                            System.out.println("Enter the new mark of the student (between 0 and 10): ");
                            try {
                                newMark = sc.nextDouble();
                                // Ensure mark is between 0 and 10
                                if (newMark < 0 || newMark > 10) {
                                    System.out.println("Invalid mark! Please enter a value between 0 and 10.");
                                } else {
                                    validMark = true;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input! Please enter a valid number for the mark.");
                                sc.nextLine(); // Consume invalid input
                            }
                        }

                        studentList.editStudent(id, newName, newMark);
                        System.out.println("Student updated successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while editing the student.");
                    }
                    break;

                case 4:
                    int deleteId = -1;
                    boolean validDeleteId = false;

                    // Repeat the prompt until a valid ID is entered
                    while (!validDeleteId) {
                        System.out.println("Enter the ID of the student to delete: ");
                        try {
                            deleteId = sc.nextInt();
                            if (deleteId <= 0) {
                                System.out.println("Invalid ID! Please enter a positive number.");
                            } else {
                                validDeleteId = true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a valid number.");
                            sc.nextLine(); // Consume invalid input
                        }
                    }

                    // Call the delete method with the valid ID
                    studentList.deleStudent(deleteId);
                    break;


                case 5:
                    System.out.println("1.Bubble Sort(By points): ");
                    System.out.println("2. Quick Sort(By points): ");
                    System.out.println("- Enter your choice: ");
                    int sortOption = sc.nextInt();

                    if (sortOption == 1) {
                        studentList.bubbleSort(true);
                        System.out.println("List sorted by Bubble Sort in ascending order.");
                    } else if (sortOption == 2) {
                        studentList.quickSort(false);
                        System.out.println("List sorted by Quick Sort in descending order.");
                    } else {
                        System.out.println("Invalid choice!");
                    }
                    studentList.traverse();
                    break;

                case 6:
                    System.out.println("1. Linear Search");
                    System.out.println("2. Binary Search");
                    System.out.println("- Enter your choice: ");
                    int searchOption = sc.nextInt();

                    System.out.println("- Enter the ID student to search: ");
                    int searchId = sc.nextInt();

                    if (searchOption == 1) {
                        int index = studentList.linearSearch(searchId);
                        if (index != -1) {
                            Node p = studentList.head;
                            for (int i = 0; i < index; i++) {
                                p = p.next;
                            }
                            System.out.println("Student found: " + p.student.toString());
                        } else {
                            System.out.println("Student with ID " + searchId + " not found.");
                        }
                    }
                    else if (searchOption == 2) {
                        int index = studentList.binarySearch(searchId);
                        if (index != -1) {
                            Node p = studentList.head;
                            for (int i = 0; i < index; i++) {
                                p = p.next;
                            }
                            System.out.println("Student found: " + p.student.toString());
                        } else {
                            System.out.println("Student with ID " + searchId + " not found.");
                        }
                    } else {
                        System.out.println("Invalid choice!");
                    }
                    break;

                case 7:
                    System.out.println("1. Push student onto stack");
                    System.out.println("2. Pop student from stack");
                    System.out.println("3. Display stack");
                    System.out.println("Enter your choice: ");
                    int stackOption = sc.nextInt();

                    if (stackOption == 1) {
                        studentList.pushStack(studentStack);
                        System.out.println("Students pushed onto the stack.");
                    } else if (stackOption == 2) {
                        studentStack.pop();
                        System.out.println("Student popped from the stack.");
                    } else if (stackOption == 3) {
                        studentStack.displayStack();
                    } else {
                        System.out.println("Invalid choice!");
                    }
                    break;

                case 8:
                    System.out.println("Exit.");
                    break;

                default:
                    System.out.println("Incorrect. Please again.");
            }
        }
    }
    
}
