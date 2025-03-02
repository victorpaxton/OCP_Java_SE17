package InterviewPractice;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class RemoveDuplicate {

    public static void main(String[] args) {
        /*
        The principal has a problem with repetitions.
        Everytime someone sends the same email twice he becomes angry and starts yelling.
        His personal assistant filters the mails so that all the unique mails are sent only once,
        and if there is someone sending the same mail again and again, he deletes them.
        Write a program which will see the list of roll numbers of the student and find how many emails are to be deleted.
         */

        /*
        Sample Input:
        6
        1
        3
        3
        4
        3
        3

        Sample Output:
        3
         */

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Set<Integer> set = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            set.add(sc.nextInt());
        }

        System.out.println(n - set.size());
    }
}
