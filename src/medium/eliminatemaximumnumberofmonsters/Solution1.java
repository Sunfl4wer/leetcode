package medium.eliminatemaximumnumberofmonsters;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringJoiner;

class Solution1 {
    public int eliminateMaximum(int[] dist, int[] speed) {
        Monster[] monsters = new Monster[dist.length];
        PriorityQueue<Monster> mq = new PriorityQueue<Monster>(new MonsterComparator());
        for (int i = 0; i < dist.length; i++) {
            Monster m = new Monster(dist[i], speed[i]);
            monsters[i] = m;
            mq.add(m);
        }
        int res = 0;
//        viewMonsters(monsters);
        Monster cm = mq.poll();
        while (!cm.reachedCity()) {
            res++;
            cm.setEliminated(true);
//            viewMonsters(monsters);
            if (mq.size() == 0) {
                break;
            }
            mq = updateQueue(mq);
            cm = mq.poll();
        }
        return res;
    }

    private void viewMonsters(final Monster[] monsters) {
        StringJoiner sj = new StringJoiner(", ");
        for (int i = 0; i < monsters.length; i++) {
            final Monster monster = monsters[i];
            if (monster.isEliminated()) {
                sj.add("[  X  ]");
            } else {
                String dist = String.valueOf(monster.getDist());
                String paddingDist = String.join("", Collections.nCopies(2-dist.length()," "));
                String speed = String.valueOf(monster.getSpeed());
                String paddingSpeed = String.join("", Collections.nCopies(2-speed.length()," "));
                sj.add("["+paddingDist+ monster.getDist()+","+paddingSpeed+monster.getSpeed()+"]");
            }
        }
        System.out.println(sj.toString());
    }

    private PriorityQueue<Monster> updateQueue(final PriorityQueue<Monster> mq) {
        PriorityQueue<Monster> nmq = new PriorityQueue<Monster>(new MonsterComparator());
        while (mq.size() != 0) {
            Monster m = mq.poll();
            m.move();
            nmq.add(m);
        }
        return nmq;
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

            // if (m1.getDist() < m2.getDist()) {
            //   return -1;
            // } else if (m1.getDist() > m2.getDist()) {
            //   return 1;
            // } else {
            //   if (m1.getSpeed() == m2.getSpeed()) {
            //     return 0;
            //   } else if (m1.getSpeed() > m2.getSpeed()) {
            //     return -1;
            //   } else {
            //     return 1;
            //   }
            // }
        }
    }

    class Monster {
        private int speed;
        private int dist;
        private boolean eliminated;
        public Monster(final int dist, final int speed) {
            this.speed = speed;
            this.dist = dist;
        }
        public int getDist() {
            return this.dist;
        }
        public int getSpeed() {
            return this.speed;
        }
        public void move() {
            this.dist = this.dist - this.speed;
        }
        public boolean reachedCity() {
            return this.dist <= 0;
        }

        public int div() {
            return this.dist/this.speed;
        }

        public int mod() {
            return this.dist%this.speed;
        }

        public void setEliminated(final boolean eliminated) {
            this.eliminated = eliminated;
        }

        public boolean isEliminated() {
            return this.eliminated;
        }
    }
}
