package LeetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ContainDuplicate {

    // 217 - Easy

    public boolean containsDuplicate(int[] nums) {
//        return Arrays.stream(nums).distinct().count() == nums.length;
//        return Arrays.stream(nums).boxed().collect(Collectors.toSet()).size() != nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (set.contains(i)) return true;
            set.add(i);
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
