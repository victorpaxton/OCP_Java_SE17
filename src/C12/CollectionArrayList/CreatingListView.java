package C12.CollectionArrayList;

import java.util.*;

public class CreatingListView {

    public static void main(String[] args) {
        Integer[] array1 = new Integer[]{9, 1, 1};
        List<Integer> list1 = Arrays.asList(array1); // (1) a list of Integer

        List<Integer> list2 = Arrays.asList(9, 1, 1); // (2) varargs

        int[] array2 = new int[]{9, 1, 1};
        // List<Integer> list3 = Arrays.asList(array2); // (3) compile-time error

        // Operations on list view

        // Elements cannot be added or removed from the list view
        System.out.println(list1);
        // list1.add(10);  // UnsupportedOperationException
        // list1.remove(1); // UnsupportedOperationException

        System.out.println("An element at a given position can be changed, being reflected in both list and array");
        list1.set(0, 12);
        System.out.println("list1: " + list1);
        System.out.println("array1: " + Arrays.toString(array1));

        List<Integer> subList = list1.subList(0, 2);
        System.out.println("sublist1: " + subList);

        System.out.println("Change (sort) the sublist");
        // subList.clear(); // UnsupportedOperationException
        Collections.sort(subList); // [1, 12, 1]
        //subList.replaceAll(e -> e + 1); // [13, 2, 1] in all sublist, list1 and array1
        //array1[0] = 0; // [0, 12, 1] in all sublist, list1 and array1
        System.out.println("sublist1: " + subList);
        System.out.println("list1: " + list1);
        System.out.println("array1: " + Arrays.toString(array1));

        // How duplicates can be eliminated from an array:
        String[] jiveArray = new String[]{"jive", "java", "java", "jive"};
        Set<String> jiveSet = new HashSet<>(Arrays.asList(jiveArray));
        String[] uniqueJiveArray = jiveSet.toArray(new String[0]);
        System.out.println(Arrays.toString(uniqueJiveArray)); // [java, jive]

        // Unmodifiable list VS List view

        // Backing array
        Integer[] yrArray1 = {2020, 2021, 2022};
        List<Integer> yrlist1 = Arrays.asList(yrArray1);
        yrArray1[0] = 2019; // Modify the array, reflecting in both array and list
        yrlist1.set(2, 2024); // Modify the list, reflecting in both array and list
        System.out.println("yrArray1: " + Arrays.toString(yrArray1)); // [2019, 2021, 2022]
        System.out.println("yrlist1: " + yrlist1); // (1) [2019, 2021, 2022]

        Integer[] yrArray2 = {2020, 2021, 2022};
        List<Integer> yrlist2 = List.of(yrArray2);
        yrArray2[0] = 2019; // Modify the array
        System.out.println("yrArray2: " + Arrays.toString(yrArray2)); // [2019, 2021, 2022]
        System.out.println("yrlist2: " + yrlist2); // (2) [2020, 2021, 2022]

        // Mutability
        // The list view returned by the Arrays.asList() method is mutable.
        // In contrast, the unmodifiable list returned by the List.of() method is immutable.
        // Both lists cannot be structurally (add, remove) modified
        List<Integer> yrList3 = Arrays.asList(2020, 2021, 2022);
        yrList3.set(2, 2023); // (1) OK
        System.out.println(yrList3); // [2020, 2021, 2023]
        List<Integer> yrlist4 = List.of(2020, 2021, 2022);
        yrlist4.set(2, 2023); // (2) UnsupportedOperationException

        yrList3.add(2050); // UnsupportedOperationException
        yrlist4.remove(0); // UnsupportedOperationException

        // Null value
        List<Integer> yrList5 = Arrays.asList(2020, 2021, null); // OK.
        List<Integer> yrlist6 = List.of(2020, 2021, null); // NullPointerException

        boolean flag1 = Arrays.asList(2021, 2022).contains(null); // OK.
        boolean flag2 = List.of(2021, 2022).contains(null); // NullPointerException
    }
}
