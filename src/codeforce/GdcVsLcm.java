package codeforce;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class GdcVsLcm {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int noTests = scanner.nextInt();
        while (noTests-- > 0) {
            Integer n = scanner.nextInt();
            int[] res = findLastTwo(n);
            int third = res[1];
            int forth = res[2];
            int first = res[0];
            int second = n - first - third - forth;
            System.out.println(String.format("%d %d %d %d", first, second, third, forth));
        }
    }

    private static int[] findLastTwo(int n) {
        int lcm = 0;
        var limit = Math.sqrt(n);
        for(int third = 1; third < limit; third++) {
            for(int forth = 1; forth < limit; forth++) {
                lcm = lcm(third,forth);
                if ((n - (third+forth)) % lcm == 0) {
                    return new int[]{lcm, third, forth};
                }
            }
        }
        return new int[]{lcm, 0, 0};
    }
    public static int lcm(int number1, int number2) {
        if(number1 == 0 || number2 == 0) {
            return 0;
        }

        Map<Integer, Integer> primeFactorsForNum1 = getPrimeFactors(number1);
        Map<Integer, Integer> primeFactorsForNum2 = getPrimeFactors(number2);

        Set<Integer> primeFactorsUnionSet = new HashSet<>(primeFactorsForNum1.keySet());
        primeFactorsUnionSet.addAll(primeFactorsForNum2.keySet());

        int lcm = 1;

        for (Integer primeFactor : primeFactorsUnionSet) {
            lcm *= Math.pow(primeFactor,
                    Math.max(primeFactorsForNum1.getOrDefault(primeFactor, 0),
                            primeFactorsForNum2.getOrDefault(primeFactor, 0)));
        }

        return lcm;
    }
    public static Map<Integer, Integer> getPrimeFactors(int number) {
        int absNumber = Math.abs(number);

        Map<Integer, Integer> primeFactorsMap = new HashMap<>();

        for (int factor = 2; factor <= absNumber; factor++) {
            while (absNumber % factor == 0) {
                Integer power = primeFactorsMap.get(factor);
                if (power == null) {
                    power = 0;
                }
                primeFactorsMap.put(factor, power + 1);
                absNumber /= factor;
            }
        }

        return primeFactorsMap;
    }
}
