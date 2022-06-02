package codeforce;

import java.util.Scanner;

public class DoorsAndKeys {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int noTests = scanner.nextInt();
        while (noTests-- > 0) {
            final String map = scanner.next();
            boolean hasRedKey = false;
            boolean hasBlueKey = false;
            boolean hasGreenKey = false;
            boolean canEscape = true;
            for (int i = 0; i < map.length(); i++) {
                final char c = map.charAt(i);
                if (c == 'r') {
                    hasRedKey = true;
                }
                if (c == 'g') {
                    hasGreenKey = true;
                }
                if (c == 'b') {
                    hasBlueKey = true;
                }
                if (c == 'R') {
                    if (!hasRedKey) {
                        canEscape = false;
                        break;
                    }
                }
                if (c == 'G') {
                    if (!hasGreenKey) {
                        canEscape = false;
                        break;
                    }
                }
                if (c == 'B') {
                    if (!hasBlueKey) {
                        canEscape = false;
                        break;
                    }
                }
            }
            if (canEscape) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
