import java.util.ArrayList;
import java.util.List;

/**
 * Morsey
 * AbstractBinaryTree class of generic type K extends AbstractTree of generic type K and implements BinaryTree of generic type K
 * @author Von Jamora
 */
public abstract class AbstractBinaryTree<K> extends AbstractTree<K> implements BinaryTree<K> {
    
    /**
     * Checks if the Node located at the provided Position is root. If is, returns null since root does not have siblings.
     * If not, returns the sibling of the Node.
     * 
     * Originates from BinaryTree interface and is defined in AbstractBinaryTree abstract class.
     * 
     * @param p Position object
     * @return Position object
     */
    public Position<K> sibling(Position<K> p) {
        Position<K> parent = parent(p);
        if(parent == null) {
            return null;
        }

        Position<K> sibling = p == left(parent) ? right(parent) : left(parent);
        return sibling;
    }

    /**
     * Returns the amount of children the Node located at the provided Position has.
     * 
     * Originates from Tree interface and is defined in AbstractBinaryTree abstract class.
     * 
     * @param p Position object
     * @return int value
     */
    public int numChildren(Position<K> p) {
        int count = 0;
        if(left(p) != null) {
            count++;
        } if(right(p) != null) {
            count++;
        }

        return count;
    }

    /**
     * Originates from Tree interface and is defined in AbstractBinaryTree abstract class.
     * 
     * @param p Position object
     * @return Iterable object of type Position object of generic type K
     */
    public Iterable<Position<K>> children(Position<K> p) {
        List<Position<K>> snappity = new ArrayList<>(2);

        if(left(p) != null) {
            snappity.add(left(p));
        } if(right(p) != null) {
            snappity.add(right(p));
        }

        return snappity;
    }

}
