import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.math.BigInteger;

public class SuperPalindromes {
  public static void main(final String[] args) {
    String left = "4";
    String right = "1000";
    String left1 = "40000000000000000";
    String right1 = "50000000000000000";
    String falseCase = "2000010002";

    System.out.println(new SuperPalindromes()
        .generatePalindromes(new Number(left), new Number(right)));

    long start = System.currentTimeMillis();
    System.out.println(new SuperPalindromes()
        .superpalindromesInRange(left1, right1));
    long end = System.currentTimeMillis();
    System.out.println("Execution time: " + (end-start)/1000.0 + "ms");
  }

  public int superpalindromesInRange(String left, String right) {
    final Number leftNumber = new Number(left);
    final Number rightNumber = new Number(right);
    final Number ONE = new Number("1");
    Number point = new Number(Objects.toString(new BigInteger(left).sqrt()));
    Number upperBound = new Number(Objects.toString
        (new BigInteger(right).sqrt()));

    int noSuperPalinedromes = 0;
    List<Number> palindromes = generatePalindromes(new Number("1"), upperBound);
    for (Number palindrome : palindromes) {
      final Number sqr = Number.multiply(palindrome, palindrome);
      if (sqr.isPalindrome() && Number.compare(sqr, leftNumber) >= 0) {
        noSuperPalinedromes++;
      }
    }
    return noSuperPalinedromes;
  }

  private List<Number> generatePalindromes(final Number begin,
      final Number end) {
    final List<Number> palindromes = new ArrayList<>();
    final int newLength = (begin.length()+1)/2;
    final int[] startDigits = new int[newLength];
    for (int i = 0; i < (begin.length()+1)/2; i++) {
      startDigits[i] = Math.max(begin.digits[i], 
          begin.digits[begin.length()-1-i]);
    }
    Number startNumber = new Number(startDigits);

    final Number ONE = new Number("1");
    while(true) {
      if (startNumber.length() % 2 == 0 
          && startNumber.isPalindrome()) {
        palindromes.add(startNumber);
      }
      final Number oddPalindrome = startNumber.generateOddPalindrome();
      if (Number.compare(oddPalindrome, end) == 1) {
        break;
      } else {
        palindromes.add(oddPalindrome);
      }
      startNumber = Number.sum(startNumber, ONE);
    }

    return palindromes;
  }

  private static class Number {
    private int[] digits;

    public Number(final String number) {
      this.digits = new int[number.length()];
      for (int i = 0; i < number.length(); i++) {
        this.digits[i] = number.charAt(i)-'0';
      }
    }

    public Number(final int[] digits) {
      this.digits = digits;
    }

    public int length() {
      return this.digits.length;
    }

    public boolean isPalindrome() {
      int half = this.digits.length/2;
      for (int i = 0; i < digits.length/2; i++) {
        if (this.digits[i] != this.digits[digits.length-i-1]) {
          return false;
        }
      }
      return true;
    }

    public Number generateEvenPalindrome() {
      int[] newDigits = new int[this.digits.length*2];
      for (int i = 0; i < this.digits.length; i++) {
        newDigits[i] = this.digits[i];
        newDigits[newDigits.length-1-i] = this.digits[i];
      }
      return new Number(newDigits);
    }

    public Number generateOddPalindrome() {
      int[] newDigits = new int[this.digits.length*2-1];
      for (int i = 0; i < this.digits.length; i++) {
        newDigits[i] = this.digits[i];
        newDigits[newDigits.length-1-i] = this.digits[i];
      }
      return new Number(newDigits);
    }
    
    public Number getHalf() {
      if (this.digits.length == 1 && 
          (this.digits[0] == 1 || this.digits[0] == 0)) {
        return new Number("0");
      }
      int[] result = new int[this.digits.length];
      for (int i = 0; i < this.digits.length; i++) {
        int sum = result[i]*10+this.digits[i];
        result[i] = sum/2;
        if (i < this.digits.length-1) {
          result[i+1] += sum%2;
        }
      }
      if (result[0] == 0) {
        return new Number(Arrays.copyOfRange(result, 1, result.length));
      }
      return new Number(result);
    }

    public String toString() {
      final StringBuilder stringBuilder = new StringBuilder();
      for (int digit : digits) {
        stringBuilder.append(Objects.toString(digit));
      }
      return stringBuilder.toString();
    }

    public static Number multiply(final Number a, final Number b) {
      int newLength = a.length()+b.length();
      final int[] result = new int[newLength];
      for (int ai = a.length()-1; ai > -1; ai--) {
        for (int bi = b.length()-1; bi > -1; bi--) {
          final int product = a.digits[ai]*b.digits[bi];
          final int sum = result[ai+bi+1]+product;
          result[ai+bi+1] = sum%10;
          result[ai+bi] += sum/10;
        }
      }
      if (result[0] != 0) {
        return new Number(result);
      }
      return new Number(Arrays.copyOfRange(result, 1, result.length));
    }

    public static Number sum(final Number a, final Number b) {
      int newLength = Math.max(a.length(), b.length());
      int minLength = Math.min(a.length(), b.length());
      final int[] result = new int[newLength+1];
      int remainder = 0;
      for (int i = 0; i < newLength+1; i++) {
        int aDigit = i > a.length()-1 ? 0 : a.digits[a.length()-1-i];
        int bDigit = i > b.length()-1 ? 0 : b.digits[b.length()-1-i];
        final int sum = aDigit + bDigit + remainder;
        result[result.length-1-i]+=sum%10;
        remainder = sum/10;
      }
      if (result[0] != 0) {
        return new Number(result);
      }
      return new Number(Arrays.copyOfRange(result, 1, result.length));
    }

    public static int compare(final Number a, final Number b) {
      if (a.length() > b.length()) {
        return 1;
      } else if (a.length() < b.length()) {
        return -1;
      } else {
        for (int i = 0; i < a.length(); i++) {
          if (a.digits[i] > b.digits[i]) {
            return 1;
          }
          if (a.digits[i] < b.digits[i]) {
            return -1;
          }
        }
        return 0;
      }
    }
  }
}
