import java.util.ArrayList;
import java.util.ListIterator;

/*
 * Example 4: Using a ListIterator
ListIterator is a more powerful iterator that allows bidirectional traversal of a list and modifying elements.

 */
public class ListIteratorExample {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            String element = listIterator.next();
            System.out.println(element);
        }

        System.out.println("Iterating backwards:");
        while (listIterator.hasPrevious()) {
            String element = listIterator.previous();
            System.out.println(element);
        }
    }
}