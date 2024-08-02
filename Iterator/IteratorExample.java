import java.util.ArrayList;
import java.util.Iterator;
/* Clay-Tech Technology - July 13, 2024
 
An iterator is an object that enables traversing through a collection, such as lists or sets. The main purpose of an iterator is to provide a standardized way to access and remove elements from a collection without exposing its underlying structure.

Key methods provided by the Iterator interface include:

hasNext(): Returns true if the iteration has more elements.
next(): Returns the next element in the iteration.
remove(): Removes the last element returned by the iterator from the underlying collection (optional operation).
 
 */
public class IteratorExample {
    public static void main(String[] args) {
    	// Example 1: Using an Iterator with a List
        ArrayList<String> list = new ArrayList<>();
        list.add("C");
        list.add("A");
        list.add("B");
        list.add("D");
 
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println(element);
        }
    }
}