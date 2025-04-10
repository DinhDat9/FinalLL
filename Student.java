/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal;

/**
 *
 * @author DELL
 */
public class Student {
    private int id;
    private String name;
    private Double mark;

    public Student(int id, String name, double mark) {
        this.id = id;
        this.name = name;
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMark() {
        return mark;
    }

    public String getRanks() {
        if (mark < 5.0) return "Fail";
        else if (mark < 6.5) return "Medium";
        else if (mark < 7.5) return "Good";
        else if (mark < 9.0) return "Very Good";
        else return "Excellent";
    }

    @Override
    public String toString() {
        return "Student{" + "ID:" + id + ", Name:" + name + ", Mark:" + mark + ", Rank:" + getRanks() + '}';
    }
}
