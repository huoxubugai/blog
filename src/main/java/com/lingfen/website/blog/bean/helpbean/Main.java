package com.lingfen.website.blog.bean.helpbean;

import java.util.Scanner;
public class Main {
    private static int gcd(int a, int b) {
        int temp = 0;
        if (a < b) {
            temp = a;
            a = b;
            b = temp;
        }
        while (b != 0) {
            temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int max=0;
        for (int i = n/2; i <=n ; i++) {
            for (int j = i+1; j <=n; j++) {
                int cur = Main.gcd(i, j);
                max = max <(i*j/cur-cur)?(i*j/cur-cur):max;
            }
        }
        System.out.println(max);
    }
}
////最大公约数一样 那就数字越大越好


