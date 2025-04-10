/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal;

/**
 *
 * @author DELL
 */
public class MyStack {
    private Node top;

    public MyStack() {
        this.top = null;
    }

    public void push(Student student) {
        Node newNode = new Node(student);
        newNode.next = top;
        top = newNode;
    }

    public Student pop() {
        if (top == null) {
            System.out.println("Stack is empty.");
            return null;
        }
        Student student = top.student;
        top = top.next;
        return student;
    }

    public void displayStack() {
        if (top == null) {
            System.out.println("Stack is empty.");
            return;
        }
        Node p = top;
        while (p != null) {
            System.out.println(p.student.toString());
            p = p.next;
        }
    }
}
