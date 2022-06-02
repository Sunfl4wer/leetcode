package medium.countnodeswiththehighestscore;

public class CountNodesWithTheHighestScore {
    public static void main(final String[] args) {
        Solution solution = new Solution();
        int[] parents = {-1,2,0};
        int[] parents1 = {-1,0,3,0,3,1};
        System.out.println(solution.countHighestScoreNodes(parents));
    }
}
