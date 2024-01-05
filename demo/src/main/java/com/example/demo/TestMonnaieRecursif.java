package com.example.demo;

public class TestMonnaieRecursif {

    static class Change {
        long coin2 = 0;
        long bill5 = 0;
        long bill10 = 0;
        
        public static Change optimalChange(long s) {
            return optimalChangeRecursive(s,  new Change());
        }
        
        private static Change optimalChangeRecursive(long s, Change c) {
            int decrement;
            if (s == 0) {
                return c;
            } else if (s < 2) {
                return null;
            } else if (s < 5) {
                c.coin2++;
                decrement = 2;
            } else if (s < 10 && s % 2 != 0) {
                c.bill5++;
                decrement = 5;
            } else if (s < 10) {
                c.coin2++;
                decrement = 2;
            } else {
                c.bill10++;
                decrement = 10;
            }
            return optimalChangeRecursive(s - decrement, c);
        }
    }

    public static void main(String[] args) {
        Change m = Change.optimalChange(19L);

        if (m == null) {
            System.out.println("no change possible ...");
            return;
        }

        System.out.println("Coin  2E: " + m.coin2);
        System.out.println("bill  5E: " + m.bill5);
        System.out.println("bill 10E: " + m.bill10);

        long result = m.coin2 * 2 + m.bill5 * 5 + m.bill10 * 10;

        System.out.println("Change given: " + result);
    }
}

    