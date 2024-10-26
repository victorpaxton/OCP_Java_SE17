package C12.CollectionArrayList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ModifyArrayList {

    private static final List<String> languages = new ArrayList<>();

    static {
        // add elements to the ArrayList
        languages.add("java");
        languages.add("javascript");
        languages.add("swift");
        languages.add("python");
        languages.add("golang");
        languages.add("Pascal");
        languages.add(null);
        System.out.println("ArrayList: " + languages);
    }

    public static void addingElement() {
        // boolean add(E element), at the end of the list
        System.out.println(languages.add("Ruby"));

        // void add(int index, E element), insert at a specified index
        languages.add(1, "C#");
        System.out.println("Updated ArrayList: " + languages);

        // boolean addAll(Collection<? extends E> c)
        List<String> newList = new LinkedList<>();
        newList.add("Scala");
        newList.add("Golang");

        languages.addAll(newList);
        System.out.println("Updated ArrayList: " + languages);

        // boolean addAll(int index, Collection<? extends E> c)
        languages.addAll(0, newList);
        System.out.println("Updated ArrayList: " + languages);
    }

    public static void replacingElement() {
        // E set(int index, E element)
        languages.set(0, "JAVA");
        System.out.println("Updated ArrayList: " + languages);

        // default void replaceAll(UnaryOperator<E> operator)
        languages.replaceAll(e -> e.toUpperCase());
        System.out.println("Updated ArrayList: " + languages);
    }

    public static void removingElement() {
        // E remove(int index)
        System.out.println("Removed element: " + languages.remove(1));
        System.out.println("Updated ArrayList: " + languages + '\n');

        // boolean remove(Object element) -> remove first occurrence, USING OBJECT VALUE EQUALITY
        // DOES NOT throw exception if it is passed a null value, or there is a null value in the list
        System.out.println(languages.remove(null)); // true
        System.out.println("Updated ArrayList: " + languages + '\n');

        // StringBuilder class does not provide equals method
        List<StringBuilder> sbList = new ArrayList<StringBuilder>();
        sbList.add(new StringBuilder("Java"));
        sbList.add(new StringBuilder("Python"));
        System.out.println("sbList: " + languages + '\n');

        StringBuilder deletedElement = new StringBuilder("Java");
        System.out.println(sbList.remove(deletedElement) + "\n"); // false

        // boolean removeAll(Collection<?> c) -> remove from this list all elements contained in c
        List<String> deletedElements = new ArrayList<>();
        deletedElements.add("java");
        deletedElements.add("golang");
        deletedElements.add("dart");
        languages.removeAll(deletedElements);
        System.out.println("Deleted ArrayList: " + deletedElements);
        System.out.println("Updated ArrayList: " + languages + '\n');

        // boolean removeIf(Predicate<? super E> filter)
        languages.removeIf(e -> e.equals("python"));
        System.out.println("Updated ArrayList: " + languages + '\n');

        languages.clear();
        System.out.println("Updated ArrayList: " + languages + '\n');
    }

    public static void modifyingCapacity() {
        // void trimToSize()
        // void ensureCapacity(int minCapacity)
    }

    public static void primitiveValue() {
        List<Integer> intList = new ArrayList<Integer>();
        intList.add(10); intList.add(20); intList.add(1); // autoboxed to Integer objects
        System.out.println(intList); // [10, 20, 1]

        // We are removing the value at index 1 (i.e, the value 20)
        System.out.println("Element removed: " + intList.remove(1)); // 20

        // If we want to delete value 1 from the list,
        System.out.println("Element removed: " + intList.remove(Integer.valueOf(1))); // true
    }

    public static void main(String[] args) {
//        replacingElement();
//        addingElement();
//        removingElement();
        primitiveValue();
    }
}
