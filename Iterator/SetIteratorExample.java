import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetIteratorExample {
    public static void main(String[] args) {
    	// Example 3: Using an Iterator with a Set
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            System.out.println(element);
        }
    }
}