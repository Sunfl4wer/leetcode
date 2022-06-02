package medium.eliminatemaximumnumberofmonsters;

import java.util.Arrays;
import java.util.Comparator;

class Solution2 {
    public int eliminateMaximum(int[] dist, int[] speed) {
        Monster[] monsters = new Monster[dist.length];
        for (int i = 0; i < dist.length; i++) {
            Monster m = new Monster(dist[i], speed[i]);
            monsters[i] = m;
        }
        Arrays.sort(monsters, new MonsterComparator());
        int res = 0;
        for (int i = 0; i < monsters.length; i++) {
            if (monsters[i].div() < i || monsters[i].div()==i && monsters[i].mod()==0) {
                break;
            }
            res+=1;
        }

        return res;
    }

    class MonsterComparator implements Comparator<Monster> {
        public int compare(final Monster m1, final Monster m2) {
            if (m1.div() > m2.div()) {
                return 1;
            } else if (m1.div() < m2.div()) {
                return -1;
            } else {
                if (m1.mod() == m2.mod()) {
                    return 0;
                } else if (m1.mod() > m2.mod()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
    }

    class Monster {
        private int speed;
        private int dist;
        public Monster(final int dist, final int speed) {
            this.speed = speed;
            this.dist = dist;
        }
        public int div() {
            return this.dist/this.speed;
        }

        public int mod() {
            return this.dist%this.speed;
        }
    }
}
