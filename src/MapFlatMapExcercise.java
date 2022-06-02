import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapFlatMapExcercise {
    public static void main(final String arg[]) {
        /*
        Given a list of list of integer
         */
        List<List<Integer>> ll = List.of(List.of(9,3,1), List.of(43,2,4,2), List.of());

        /*
        Return a list of Integer that has ALL the value in ll sorted ASC using stream API
        */
        List<Integer> expectedResult = List.of(1,2,2,3,4,9,43);
        List<Integer> sortedElems = flatmapAndSort(ll);
        if (Objects.deepEquals(expectedResult, sortedElems)) {
            System.out.println("First problem solved");
        } else {
            System.out.println("First problem not solved, chuc ban may man lan sau");
        }

        /*
        Given a list of string
         */
        List<String> randomList = List.of("9", "3", "1", "mew", "43", "2", "4", "2");

        /*
        Some one decided that he need to convert the value into integers using the provided valueOf method.
        However due to FunctionalInterface constraint, the checked exception cannot be thrown.
        Please help fixing this issue using two approach:
        1. Writing a wrapper method to throw unchecked exception
        2. Writing a functional interface that override the behavior of the default one
        Note: you must you the provided valueOf() method in this class
         */
        try {
            List<Integer> converted = randomList.stream()
                    .map(MapFlatMapExcercise::valueOf)
                    .collect(Collectors.toList());
        } catch (RuntimeException exception) {
            System.out.println("Second problem solved.");
        }
    }

    @FunctionalInterface
    public interface ThrowableFunction<T, R> extends Function<T, R> {

    }

    static class CheckedException extends Exception {
        static final long serialVersionUID = -4903755590695128422L;

        public CheckedException(final String message) {
            super(message);
        }
    }

    private static Integer valueOf(final String val) throws CheckedException {
        try{
            return Integer.valueOf(val);
        } catch (NumberFormatException e) {
            throw new CheckedException("Invalid number format!");
        }
    }

    private static List<Integer> flatmapAndSort(List<List<Integer>> ll) {
        List<Integer> res = new ArrayList<>();
        // Your solution goes here
        return res;
    }
}
