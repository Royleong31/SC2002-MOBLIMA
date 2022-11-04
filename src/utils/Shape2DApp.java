package utils;

import java.util.Scanner;
import java.io.Serializable;
import java.lang.Math;

interface Shape{
    public double area();
}

public class Shape2DApp {


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int noOfShapes;
        int shapeType;
        double[] dimenstions = new double[2];
        // boolean calcType;
        Shape[] shapes;
        double result = 0;
        int mode = 0;

        System.out.print("Mode?");
        mode = sc.nextInt();
        if(mode == 2){
            DataUtils du2 = new DataUtils();
            Square s0 = (Square)du2.loadData("Shape0");
            System.out.println("S0 length:" + s0.getLength());
            System.out.println("S0 breadth:" + s0.getBreadth());
            Triangle s1 = (Triangle)du2.loadData("Shape1");
            System.out.println("S1 base:" + s1.getBase());
            System.out.println("S1 height:" + s1.getHeight());
            Circle s2 = (Circle)du2.loadData("Shape2");
            System.out.println("S2 radius:" + s2.getRadius());
            System.exit(0);
        }

        System.out.print("Enter the total number of Shapes:");
        noOfShapes = sc.nextInt();
        shapes = new Shape[noOfShapes];
        for(int i = 0; i < noOfShapes; i++){
            // System.out.print("\nEnter type of calculation (0-Area, 1-Volume): ");
            // calcType = sc.nextBoolean();

            System.out.print("Enter the Shape (1-Square, 2-Triangle, 3-Circle):");
            shapeType = sc.nextInt();
            switch(shapeType){
                case 1:
                    System.out.print("\tEnter length: ");
                    dimenstions[0] = sc.nextDouble();
                    System.out.print("\tEnter breadth: ");
                    dimenstions[1] = sc.nextDouble();
                    shapes[i] = new Square(dimenstions[0], dimenstions[1]);
                    result += shapes[i].area() ;
                    break;
                case 2:
                    System.out.print("\tEnter height: ");
                    dimenstions[0] = sc.nextDouble();
                    System.out.print("\tEnter base: ");
                    dimenstions[1] = sc.nextDouble();
                    shapes[i] = new Triangle(dimenstions[0], dimenstions[1]);
                    result += shapes[i].area();
                    break;
                case 3:
                    System.out.print("\tEnter radius: ");
                    dimenstions[0] = sc.nextDouble();
                    shapes[i] = new Circle(dimenstions[0]);
                    result += shapes[i].area();
                    break;
                default:
                    System.out.println("Error");
                    System.exit(1);
            }
        }
        DataUtils du = new DataUtils();
        int j = 0;
        int k = -1;
        double ta = 0.0; // total area
        for(Shape s: shapes){
            ta += s.area();
            k = du.saveData(s, "Shape"+j);
            System.out.println("Savecode: " + k);
            j++;
        }
        
        System.out.print("\nThe result is " + result);
        sc.close();
        


    }
        
    
}


class Square implements Shape, Serializable{
    private double length;
    private double breadth;

    public Square(double length, double breadth){
        this.length = length;
        this.breadth = breadth;
    }

    
    public double getLength(){
        return length;
    }

    public double getBreadth(){
        return breadth;
    }

    public double area(){
        return length * breadth;
    }
}

class Triangle implements Shape, Serializable{
    private double height;
    private double base;

    public Triangle(double height, double base){
        this.height = height;
        this.base = base;
    }

    public double getHeight(){
        return height;
    }

    public double getBase(){
        return base;
    }

    public double area(){
        return 0.5 * base * height;
    }

}

class Circle implements Shape, Serializable{
    private double radius;

    public Circle(double radius){
        this.radius = radius;
    }

    public double getRadius(){
        return radius;
    }

    public double area(){
        return Math.PI * (radius * radius);
    }
}

