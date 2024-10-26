package C12.CollectionArrayList;

import java.util.ArrayList;
import java.util.List;

public class Declaration {
    public static void main(String[] args) {
        List<StringBuilder> list1 = new ArrayList<>();
        list1.add(new StringBuilder("Anna"));
        list1.add(new StringBuilder("Bob"));
        list1.add(new StringBuilder("Ada"));

        List<StringBuilder> list2 = new ArrayList<>(list1);
        list2.set(1, new StringBuilder("Ryan"));

        System.out.println(list1);
        System.out.println(list2);

        List<StringBuilder> sbListOne = new ArrayList<>();
        sbListOne.add(new StringBuilder("Anna"));
        sbListOne.add(new StringBuilder("Ada"));
        sbListOne.add(new StringBuilder("Bob"));
        List<StringBuilder> sbListTwo = new ArrayList<>(sbListOne);
        sbListOne.add(null);
        sbListTwo.get(1).reverse();
        System.out.println(sbListOne);
    }
}
