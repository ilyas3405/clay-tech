import java.util.ArrayList;
import java.util.Iterator;

public class RemoveElementExample {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("B");

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            // Example 2: Removing Elements Using an Iterator
            if (element.equals("B")) {
                iterator.remove();
            }
        }

        System.out.println(list); // Output: [A, C]
    }
}