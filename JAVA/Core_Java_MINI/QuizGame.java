import java.util.Scanner;

public class QuizGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int score = 0;

        System.out.println("Q1: Capital of India?");
        System.out.println("a) Delhi  b) Mumbai  c) Kolkata");
        String ans1 = sc.nextLine();
        if (ans1.equalsIgnoreCase("a")) score++;

        System.out.println("Q2: 2 + 2 = ?");
        System.out.println("a) 3  b) 4  c) 5");
        String ans2 = sc.nextLine();
        if (ans2.equalsIgnoreCase("b")) score++;

        System.out.println("Q3: Java is a ...?");
        System.out.println("a) Language  b) Car  c) Fruit");
        String ans3 = sc.nextLine();
        if (ans3.equalsIgnoreCase("a")) score++;

        System.out.println("Final Score: " + score + "/3");
    }
}