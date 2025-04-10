/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal;

import java.util.Scanner;
import java.util.InputMismatchException;
/**
 *
 * @author DELL
 */
public class MyList {
    Node head, tail;

    public MyList() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public static boolean isValidName(String name) {
        // Chỉ cho phép chữ cái tiếng Anh không dấu (a-z, A-Z) và khoảng trắng
        String regex = "^[A-Za-z ]+$";
        return name.matches(regex);
    }

    public void addStudentFromKeyBoard() {

        Scanner sc = new Scanner(System.in);

        Integer id = null;

        while (id == null || id <= 0 || isIdExists(id)) {
            System.out.println("Enter the ID student: ");
            try {
                id = sc.nextInt();
                if (id <= 0) {
                    System.out.println("ID must be greater than 0. Please try again.");
                } else if (isIdExists(id)) {
                    System.out.println("This ID already exists. Please enter a unique ID.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer for ID.");
                sc.nextLine(); // Consume the invalid input
            }
        }

        sc.nextLine(); // Consume the newline character left by nextInt()

        String name = null;
        do {
            System.out.println("Enter the name student: ");
            name = sc.nextLine();
            if (!isValidName(name)) {
                System.out.println("Please re-enter the name:");
            }
        } while (!isValidName(name));

        double mark = -1;
        boolean validMark = false;
        while (!validMark) {
            System.out.print("Enter mark (between 0 and 10): ");
            try {
                mark = sc.nextDouble();
                if (mark < 0 || mark > 10) {
                    System.out.println("Invalid mark! Mark should be between 0 and 10. Please enter again.");
                } else {
                    validMark = true;  // Valid mark, exit the loop
                    add(new Student(id, name, mark));  // Add the student
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number for the mark.");
                sc.nextLine(); // Consume the invalid input
            }
        }
    }

    private boolean isIdExists(int id) {
        Node p = head;
        while (p != null) {
            if (p.student.getId() == id) {
                return true; // ID exists
            }
            p = p.next;
        }
        return false; // ID does not exist
    }




    public void add(Student s) {
        Node newNode = new Node(s);
        if (isEmpty() == true) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void editStudent(int id, String newName, double newMark) {
        Node p = head;
        boolean studentFound = false;

        while (p != null) {
            if (p.student.getId() == id) {
                try {
                    // Ensure valid name input
                    if (newName == null || newName.trim().isEmpty()) {
                        throw new IllegalArgumentException("Name cannot be empty.");
                    }

                    // Ensure valid mark input
                    if (newMark < 0) {
                        throw new IllegalArgumentException("Marks cannot be negative.");
                    }

                    // Update student info
                    p.student = new Student(id, newName, newMark);
                    System.out.println("The student with ID " + id + " has been updated successfully.");
                    studentFound = true;
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                    return;
                }
            }
            p = p.next;
        }

        if (!studentFound) {
            System.out.println("No student found with ID " + id);
        }
    }



    public void deleStudent(int id)
    {
        try {
            if (head == null) {
                System.out.println("No student to delete.");
                return;
            }

            // Case where the student to be deleted is the head
            if (head.student.getId() == id) {
                head = head.next;
                System.out.println("The student with ID " + id + " has been deleted.");
                return;
            }

            // Traverse the list to find the student to delete
            Node p = head;
            while (p.next != null) {
                if (p.next.student.getId() == id) {
                    p.next = p.next.next;
                    System.out.println("The student with ID " + id + " has been deleted.");
                    return;
                }
                p = p.next;
            }

            // If we reach here, the student was not found
            System.out.println("No student found with ID " + id + " to delete.");
        } catch (Exception e) {
            System.out.println("An error occurred while deleting the student: " + e.getMessage());
        }
    }

    public void bubbleSort(boolean ascending)
    {
        if (head == null || head.next == null)
        {
            return;
        }

        for (boolean swapped = true; swapped;)
        {
            swapped = false;
            Node p = head;

            while (p.next != null)
            {
                boolean condition;
                if (ascending)
                {
                    condition = p.student.getMark() > p.next.student.getMark();
                }else
                {
                    condition = p.student.getMark() < p.next.student.getMark();
                }
                if (condition)
                {
                    Student temp = p.student;
                    p.student = p.next.student;
                    p.next.student = temp;
                    swapped = true;
                }
                p = p.next;
            }
        }
    }

    public void quickSort(boolean ascending) {
        head = quickSortRec(head, ascending);
        Node p = head;
        while (p != null && p.next != null) {
            p = p.next;
        }
        tail = p;
    }

    private Node quickSortRec(Node start, boolean ascending) {
        if (start == null || start.next == null) {
            return start;
        }

        Node pivot = start;
        Node less = null, greater = null;
        Node current = start.next;

        while (current != null) {
            Node next = current.next;
            if ((ascending && current.student.getMark() < pivot.student.getMark()) || (!ascending && current.student.getMark() > pivot.student.getMark())) {
                current.next = less;
                less = current;
            } else {
                current.next = greater;
                greater = current;
            }
            current = next;
        }

        less = quickSortRec(less, ascending);
        greater = quickSortRec(greater, ascending);

        if (less == null) {
            pivot.next = greater;
            return pivot;
        } else {
            Node temp = less;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = pivot;
            pivot.next = greater;
            return less;
        }
    }

    public int linearSearch(int id) {
        Node p = head;
        int index = 0;
        while (p != null) {
            if (p.student.getId() == id) {
                return index;
            }
            p = p.next;
            index++;
        }
        return -1;
    }


    public int binarySearch(int id) {

        bubbleSort(true);

        Node p = head;
        int low = 0, high = 0;

        while (p != null) {
            high++;
            p = p.next;
        }
        high--;


        while (low <= high) {
            int mid = (low + high) / 2;


            p = head;
            for (int i = 0; i < mid; i++) {
                p = p.next;
            }


            if (p.student.getId() == id) {
                return mid;
            } else if (p.student.getId() < id) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public void traverse() {
        Node p = head;
        while (p != null) {
            System.out.println(p.student.toString());
            p = p.next;
        }
    }
    public void pushStack(MyStack stack) {
        Node p = head;
        while (p != null) {
            stack.push(p.student);
            p = p.next;
        }
    }
}
