
// Given an String, Write a java method that returns the decimal value for the given binary string.
import java.util.*;
import java.util.Scanner;
public class Solution {
    /*
       Do not modify this main function.
       */
    public final static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            String res = binaryToDecimal(s);//Write binaryToDecimal function
            System.out.println(res);
        }
    }

    public static String binaryToDecimal(String s) {
        int num = 0;
        String l = "";
        char[] arr = s.toCharArray();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (arr[i] == '1') {
                num += Math.pow(2, i);
            }
        }
        l += num;
        return l;
    }
}
