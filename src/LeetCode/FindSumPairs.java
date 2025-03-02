package LeetCode;

import java.util.*;

public class FindSumPairs {

    // 1865. Finding Pairs With a Certain Sum

    int[] nums1, nums2;

    Map<Integer, Integer> map2 = new HashMap<>();

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;

        for (int i : nums2) {
            map2.put(i, map2.getOrDefault(i, 0) + 1);
        }

    }

    public void add(int index, int val) {
        this.map2.put(nums2[index], this.map2.get(nums2[index]) - 1);
        this.nums2[index] += val;
        this.map2.put(nums2[index], this.map2.getOrDefault(nums2[index], 0) + 1);
    }

    public int count(int tot) {
        int count = 0;

        for (int num : nums1) {
            count += map2.getOrDefault(tot - num, 0);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,1,2,2,2,3};
        int[] nums2 = {1,4,5,2,5,4};

        FindSumPairs f = new FindSumPairs(nums1, nums2);
        f.count(7);
    }

    public int[] sortArray(int[] nums) {
        return Arrays.stream(nums).sorted().toArray();
    }

    public String[] findRelativeRanks(int[] score) {
        List<String> res = new LinkedList<>();
        List<Integer> rank = Arrays.stream(score).boxed().sorted(Comparator.reverseOrder()).toList();
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "Gold Medal");
        map.put(1, "Silver Medal");
        map.put(2, "Bronze Medal");
        for (int i = 4; i <= score.length; i++) {
            map.put(i, Integer.toString(i));
        }
        for (int s : score) {
            res.add(map.get(rank.indexOf(s)));
        }
        return res.toArray(new String[0]);
    }

    public long countFairPairs(int[] nums, int lower, int upper) {
        //nums = [0,1,7,4,4,5], lower = 3, upper = 6
        /*
        [0,1,4,4,5,7]
        0 -> 4, 4, 5
        1 -> 4, 4, 5
         */

        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        int count = 0;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum >= lower && sum <= upper)
                count++;
            else if (sum < lower)
                left++;
            else
                right--;
        }
    }
}
