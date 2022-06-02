package medium.eliminatemaximumnumberofmonsters;

import java.util.Arrays;
import java.util.Comparator;

class Solution3 {
    public int eliminateMaximum(int[] dist, int[] speed) {
        int[] times = new int[dist.length];
        for (int i = 0; i < dist.length; i++) {
            times[i] = (int)Math.ceil(dist[i]*1.0/speed[i]);
        }
        Arrays.sort(times);
        int res = 0;
        for (int i = 0; i < times.length; i++) {
            if (times[i] < i) {
                break;
            }
            res+=1;
        }

        return res;
    }
}
