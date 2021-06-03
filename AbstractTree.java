import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Morsey
 * AbstractTree abstract class of generic type K implements Tree of generic type K
 *      ElementIterator static inner class of generic type K implements Iterator of generic type K
 * @author Vazyboi
 */
public abstract class AbstractTree<K> implements Tree<K> {

    private class ElementIterator implements Iterator<K> {
        Iterator<Position<K>> posIterator = positions().iterator();

        /**
         * Returns boolean based on whether Iterator has an element available after the current one it is accessing.
         * 
         * @return boolean value
         */
        public boolean hasNext() {
            return posIterator.hasNext();
        }

        /**
         * Returns value of generic type K of the next element in Iterator.
         * 
         * @return generic type K value
         */
        public K next() {
            return posIterator.next().getElement();
        }

        /**
         * Removes the last element returnec by Iterator.
         */
        public void remove() {
            posIterator.remove();
        }

        /**
         * Returns a String representation of ElementIterator. Includes all elements in Iterator.
         * 
         * @return String value
         */
        public String toString() {
            StringBuilder print = new StringBuilder();

            while(posIterator.hasNext()) {
                print.append(posIterator.next() + ", ");
            }
            print.delete(print.length()-2, print.length()-1);

            return print.toString();
        }
        
    }// end of inner ElementIterator class

// ************************************************** Traversal **************************************************

    /**
     * Returns Iterator object containing elements of each Node in tree.
     * 
     * @return Iterator object of generic type K
     */
    public Iterator<K> iterator() {
        return new ElementIterator();
    }

    /**
     * Returns Iterable object with tree nodes in order defined by the chosen traversal method.
     * 
     * @return Iterable object of type Position of generic type K
     */
    public Iterable<Position<K>> positions() {
        return preorder(); // preorder traversal as default tree traversal method
        //return postorder(); // for postorder traversal
        //return breadthfirst(); // for breadth-first traversal
    }

    /**
     * Adds position of subtree rooted at provided Position to given snapshot.
     * 
     * @param p Position object of generic type K
     * @param snappity List object of type Position of generic type K
     */
    private void preorderSubtree(Position<K> p, List<Position<K>> snappity) {
        snappity.add(p);

        for(Position<K> c : children(p)) {
            preorderSubtree(c, snappity);
        }
    }

    /**
     * Returns Iterable collection containing Positions in tree reported in preorder.
     * 
     * @return Iterable object of type Position of generic type K
     */
    public Iterable<Position<K>> preorder() {
        List<Position<K>> snappity =  new ArrayList<>();

        if(!isEmpty()) {
            preorderSubtree(root(), snappity);
        }

        return snappity;
    }

    /**
     * Adds positions of subtree rooted at provided Position to given snapshot.
     * 
     * @param p Position object of generic type K
     * @param snappity List object of type Position of generic type K
     */
    private void postorderSubtree(Position<K> p, List<Position<K>> snappity) {
        for(Position<K> c : children(p)) {
            postorderSubtree(c, snappity);
        }

        snappity.add(p); // add p AFTER traversing subtrees
    }

    /**
     * Returns Iterable collection containing Positions in tree reported in postorder.
     * 
     * @return Iterable object of type Position of generic type K
     */
    public Iterable<Position<K>> postorder() {
        List<Position<K>> snappity = new ArrayList<>();

        if(!isEmpty()) {
            postorderSubtree(root(), snappity);
        }

        return snappity;
    }

    /**
     * Returns Iterable collection containing Positions in tree reported in breadth-first order (traverse each node frmo left to right).
     * 
     * @return Iterable object of type Position of generic type K
     */
    public Iterable<Position<K>> breadthfirst() {
        List<Position<K>> snappity = new ArrayList<>();

        if(!isEmpty()) {
            Queue<Position<K>> pringels = new ArrayQueue<>();
            pringels.enqueue(root()); // starts with roots

            while(!pringels.isEmpty()) {
                Position<K> p = pringels.dequeue(); // remove from front
                snappity.add(p); // visited element

                for(Position<K> c : children(p)) {
                    pringels.enqueue(c); // add children to queue
                }
            }
        }

        return snappity;
    }

// ************************************************** Traversal **************************************************
    
    /**
     * Returns boolean value based on whether the Node located at the provided Position object is an internal Node (atleast one child).
     * 
     * Originates from Tree interface and is defined in AbstractTree abstract class.
     * 
     * @param p Position object
     * @return boolean value
     */
    public boolean isInternal(Position<K> p) {
        return numChildren(p) > 0;
    }

    /**
     * Returns boolean value based on whether the Node located at the provided Position object is an external Node (no children).
     * 
     * Originates from Tree interface and is defined in AbstractTree abstract class.
     * 
     * @param p Position object
     * @return boolean value
     */
    public boolean isExternal(Position<K> p) {
        return numChildren(p) == 0;
    }

    /**
     * Returns boolean value based on whether the Node located at the provided Position object is the root of the tree.
     * 
     * Originates from Tree interface and is defined in AbstractTree abstract class.
     * 
     * @param p Position object
     * @return boolean value
     */
    public boolean isRoot(Position<K> p) {
        return p == root();
    }

    /**
     * Returns boolean based on whether tree has any Node objects.
     * 
     * Originates from Tree interface and is defined in AbstractTree abstract class.
     * 
     * @return boolean value
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns number of levels separating provided Position from root.
     * 
     * @param p Position object of generic type K
     * @return integer value
     */
    public int depth(Position<K> p) {
        if(isRoot(p)) {
            return 0;
        } else {
            return 1 + depth(parent(p));
        }
    }

    /**
     * Returns height of subtree rooted at provided Position.
     * 
     * @param p Position object of generic type K
     * @return integer value
     */
    public int height(Position<K> p) {
        int h=0;

        for(Position<K> c :  children(p)) {
            h = Math.max(h, 1 + height(c));
        }

        return h;
    }

}
