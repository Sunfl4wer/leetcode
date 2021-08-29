package hard;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class MinimumIntervalToIncludeEachQuery {
  public static void main(String[] args) {
//    int[][] intervals = {{1,4},{2,4},{3,6},{4,4}};
//    int[] queries = {2,3,4,5};
    int[][] intervals1 = {{2,3},{2,5},{1,8},{20,25}};
    int[] queries1 = {2,19,5,22};
    int[][] intervals2 = {{6,6},{5,5},{10,10},{3,6},{9,9}};
    int[] queries2 = {7,9,2,4,5};
    int[][] intervals3 = {{54,82},{55,66},{81,89},{38,67},{81,86},{47,47},{13,61},{33,39},{61,66},{97,97},{52,68},{96,98},{89,92},{1,41},{81,89},{9,57},{81,90},{41,73},{29,80},{98,98},{61,95},{93,98},{5,65},{91,96},{91,99},{28,68},{55,71},{35,45},{1,89},{48,48},{26,36},{5,83},{20,83},{73,92},{69,69},{77,89},{12,52},{5,53},{33,53},{70,83},{81,98},{69,69},{28,90},{9,77},{40,53},{53,71},{7,55},{7,28},{5,88},{61,68},{25,93},{45,73},{13,51},{27,70},{47,87},{71,91},{93,98},{1,35},{24,39},{86,90},{19,33},{1,69},{21,100},{85,85},{99,99},{25,25},{90,94},{13,61},{69,85},{89,97},{1,43},{11,35},{41,95},{31,99},{86,94},{33,63},{22,91},{61,75},{71,83},{31,85},{28,83},{1,21},{81,97},{5,29},{74,83},{33,83},{13,24},{92,94},{71,71},{59,79},{21,37},{33,87},{97,97},{34,57},{11,59},{57,62},{22,23},{13,53},{84,85},{71,80}};
    int[] queries3 = {31,9,21,91,91,58,13,76,21,69,41,1,73,2,71,51,69,89,31,85,61,61,39,76,36,50,1,33,41,38,29,91,93,47,1,11,33,79,15,7,21,36,65,1,1,93,45,51,33,5,15,65,49,81,59,21,1,7,81,6,1,80,81,21,24,41,47,85,38,26,100,33,57,24,71,16,65,96,81,83,17,75,76,21,85,47,77,49,31,61,9,49,1,73,32,66,96,97,30,21};

    int[][] intervals = null;
    int[] queries = null;
    try {
      InputStream is = MinimumIntervalToIncludeEachQuery.class.getResourceAsStream("minimum-interval-to-include-each-query-test-cases.txt");
      Scanner scanner = new Scanner(is);
      int i = 0;
      while(scanner.hasNextLine()) {
        String line = scanner.nextLine();
        line = line.replace("]", "");
        line = line.replace("[", "");
        List<String> elements = Arrays.asList(line.split(","));
        if (i == 2) {
          intervals = new int[elements.size()/2][2];
          for (int j = 0; j < elements.size(); j+=2) {
            int[] temp = {Integer.parseInt(elements.get(j)), Integer.parseInt(elements.get(j+1))};
            intervals[j/2] = temp;
          }
        }
        if (i == 3) {
          queries = new int[elements.size()];
          int k = 0;
          for (String element : elements) {
            queries[k] = Integer.parseInt(element);
            k++;
          }
        }
        i++;
      }
    } catch (Exception e) {

    }

    MinimumIntervalToIncludeEachQuery sol = new MinimumIntervalToIncludeEachQuery();
    long startTime = System.currentTimeMillis();
    System.out.println(Arrays.toString(sol.minInterval(intervals, queries)));
    long endTime = System.currentTimeMillis();
    System.out.println("Execution time: " + (endTime-startTime)/1000 + "s");
  }

  public int[] minInterval(int[][] intervals, int[] queries) {
    int[] result = new int[queries.length];
    long startTime = System.currentTimeMillis();
    List<IntervalSize> intervalSizes = init(intervals);
    long endTime = System.currentTimeMillis();
    System.out.println("Init time: " + (endTime-startTime)/1000 + "s");
    for (int i = 0; i < queries.length; i++) {
      Integer query = queries[i];
      boolean hasAMatch = false;
      for (IntervalSize is : intervalSizes) {
        if (is.min > query) {
          continue;
        }
        if (is.hasIntervalContains(query)) {
          result[i] = is.value;
          hasAMatch = true;
          break;
        }
      }
      if (!hasAMatch) {
        result[i] = -1;
      }
    }
    return result;
  }

  public List<IntervalSize> init(int[][] intervals) {
    Map<Integer, IntervalSize> sizeToObject = new HashMap<>();
    final Comparator<IntervalSize> comparator = new Comparator<IntervalSize>() {
      @Override
      public int compare(IntervalSize o1, IntervalSize o2) {
        return o1.value < o2.value ? -1 : (o1.value > o2.value ? 1 : 0);
      }
    };
    PriorityQueue<IntervalSize> queue = new PriorityQueue<>(comparator);
    for (int[] interval : intervals) {
      Integer size = interval[1] - interval[0];
      if (sizeToObject.containsKey(size)) {
        sizeToObject.get(size).add(interval);
      } else {
        IntervalSize is = new IntervalSize(interval);
        sizeToObject.put(size, is);
        queue.add(is);
      }
    }
    List<IntervalSize> result = new ArrayList<>();
    while(!queue.isEmpty()) {
      IntervalSize intervalSize = queue.poll();
      result.add(intervalSize);
    }
    return result;
  }

  public class IntervalSize {
    Integer value;
    Integer min = Integer.MAX_VALUE;
    Integer max = Integer.MIN_VALUE;
    List<List<Integer>> intervals = new ArrayList<>();
    Map<Integer, Set<Integer>> contained = new HashMap<>();
    public IntervalSize(Integer value) {
      this.value = value;
    }

    public IntervalSize(int[] interval) {
      this.value = interval[1]-interval[0]+1;
      List<Integer> temp = new ArrayList<>();
      temp.add(interval[0]);
      temp.add(interval[1]);
      if (interval[0] < min) {
        min = interval[0];
      }
      if (interval[0] > max) {
        max = interval[0];
      }
      this.intervals.add(temp);
    }

    public void add(int[] interval) {
      if (contained.containsKey(interval[0])) {
        if (contained.get(interval[0]) != null && contained.get(interval[0]).contains(interval[1])) {
          return;
        } else {
          contained.get(interval[0]).add(interval[1]);
        }
      } else {
        Set<Integer> set = new HashSet<>();
        set.add(interval[1]);
        contained.put(interval[0], set);
      }

      int i = next(intervals, interval[0]);
      List<Integer> temp = new ArrayList<>();
      temp.add(interval[0]);
      temp.add(interval[1]);
      if (interval[0] < min) {
        min = interval[0];
      }
      if (interval[0] > max) {
        max = interval[0];
      }
      if (i == -1) {
        this.intervals.add(temp);
      } else {
        this.intervals.add(i, temp);
      }
    }

    public boolean hasIntervalContains(Integer value) {
      if (value < this.min) {
        return false;
      }
      if (value > this.max+this.value) {
        return false;
      }
      int index = next(intervals, value);
      if (index == -1) {
        index = intervals.size()-1;
      }
      if (intervals.get(index).get(0) > value) {
        index--;
      }
      for (int i = index; i > -1; i--) {
        List<Integer> interval  = intervals.get(i);
        if (interval.get(1) < value) {
          break;
        }
        if (interval.get(0) > value) {
          break;
        }
        if (interval.get(0) <= value && interval.get(1) >= value) {
          return true;
        }
      }
      return false;
    }
  }

  private static int next(List<List<Integer>> arr, int target) {
    int start = 0, end = arr.size() - 1;

    int ans = -1;
    while (start <= end) {
      int mid = (start + end) / 2;

      if (arr.get(mid).get(0) <= target) {
        start = mid + 1;
      }
      else {
        ans = mid;
        end = mid - 1;
      }
    }
    return ans;
  }
}
