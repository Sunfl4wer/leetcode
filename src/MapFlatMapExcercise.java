import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    }

    private static List<Integer> flatmapAndSort(List<List<Integer>> ll) {
        List<Integer> res = new ArrayList<>();
        // Your solution goes here
        return res;
    }
}
