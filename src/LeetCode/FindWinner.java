package LeetCode;

import java.util.*;
import java.util.stream.Collectors;

public class FindWinner {

    // 2225 - Find Players With Zero or One Losses - Medium

    /*
    Input: matches = [[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]
    Output: [[1,2,10],[4,5,7,8]]
    Explanation:
    Players 1, 2, and 10 have not lost any matches.
    Players 4, 5, 7, and 8 each have lost one match.
    Players 3, 6, and 9 each have lost two matches.
    Thus, answer[0] = [1,2,10] and answer[1] = [4,5,7,8].
     */
    public static List<List<Integer>> findWinners(int[][] matches) {
        Map<Integer, Integer> map = new TreeMap<>();

        for (int[] match : matches) {
            map.putIfAbsent(match[0], 0);
            map.put(match[1], map.getOrDefault(match[1], 0) + 1);
        }

        List<Integer> noLost = new ArrayList<>();
        List<Integer> oneLost = new ArrayList<>();

        for (int key : map.keySet()) {
            if (map.get(key) == 0) {
                noLost.add(key);
            } else if (map.get(key) == 1) {
                oneLost.add(key);
            }
        }

//        List<Integer> noLost = map.entrySet().stream()
//                .filter(entry -> entry.getValue() == 0)
//                .map(Map.Entry::getKey)
//                .toList();
//
//        List<Integer> oneLost = map.entrySet().stream()
//                .filter(entry -> entry.getValue() == 1)
//                .map(Map.Entry::getKey)
//                .toList();

        return List.of(noLost, oneLost);
    }

    public static void main(String[] args) {
        int[][] matches = {{1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5}, {4, 8}, {4, 9}, {10, 4}, {10, 9}};
        findWinners(matches);
    }
}
