/**
 * Morsey
 * LinkedBinaryTree class of generic type K implements BinaryTree of generic type K and Position of generic type K
 *      Node static inner class of generic type K implements Position of generic type K
 * @author Vazyboi
 */
public class LinkedBinaryTree<K> extends AbstractBinaryTree<K> {

    protected static class Node<K> implements Position<K> {
        private K elem;
        private Node<K> parent;
        private Node<K> left;
        private Node<K> right;

        /**
         * Constructs Node object with given parameters.
         * 
         * @param e value of generic type K to be stored in Node object
         * @param p parent of the Node object
         * @param l left child of the Node object
         * @param r right child of the Node object
         */
        public Node(K e, Node<K> p, Node<K> l, Node<K> r) {
            elem = e;
            parent = p;
            left = l;
            right = r;
        }

        /**
         * Returns the K value stored in the Node object.
         * From the Position<K> interface.
         * 
         * @return value of generic type K
         */
        public K getElement() throws IllegalStateException {
            return elem;
        }

        /**
         * Returns the parent of the Node object.
         * 
         * @return Node object
         */
        public Node<K> getParent() {
            return parent;
        }

        /**
         * Returns the left child of the Node object.
         * 
         * @return Node object
         */
        public Node<K> getLeft() {
            return left;
        }

        /**
         * Returns the right child of the Node object.
         * 
         * @return Node object
         */
        public Node<K> getRight() {
            return right;
        }

        /**
         * Sets the K value stored in the Node object to the given parameter.
         * 
         * @param value of generic type K
         */
        public void setElement(K newE) {
            elem = newE;
        }

        /**
         * Sets the parent of the Node object to the given parameter.
         * 
         * @param Node object
         */
        public void setParent(Node<K> newP) {
            parent = newP;
        }

        /**
         * Sets the left child of the Node object to the given parameter.
         * 
         * @param Node object
         */
        public void setLeft(Node<K> newL) {
            left = newL;
        }

        /**
         * Sets the right child of the Node object to the given parameter.
         * 
         * @param Node object
         */
        public void setRight(Node<K> newR) {
            right = newR;
        }

        /**
         * Returns a String representation of Node. Includes its element, parent, left, and right.
         * 
         * @return String value
         */
        public String toString() {
            return elem + ": " + parent + ", " + left + ", " + right;
        }

    } // end of inner Node class

    // Nonpublic utility
    /**
     * Factory function to create and return new Node object with fields set to given parameters.
     * 
     * @param v value of generic type K
     * @param parent parent of the Node object
     * @param left left child of the Node object
     * @param right right child of the Node object
     * @return Node object
     */
    protected Node<K> createNode(K v, Node<K> parent, Node<K> left, Node<K> right) {
        return new Node<K>(v, parent, left, right);
    }

    /**
     * Validates position and returns the Node object located there.
     * 
     * @param p Position object
     * @return Node object
     * @throws IllegalArgumentException
     */
    protected Node<K> validate(Position<K> p) throws IllegalArgumentException {
        // check if position is proper
        if (!(p instanceof Node)) {
            throw new IllegalArgumentException("Provided position passed poorly. ;)");
        }

        // check if node exists in tree
        Node<K> node = (Node<K>) p;
        if (node.getParent() == node) { // convention for nodes that have been removed from tree
            throw new IllegalArgumentException("P does not exist (ʘᗩʘ')");
        }

        return node;
    }

    // LinkedBinaryTree fields
    protected Node<K> root = null; // keeps track of root of tree
    private int size = 0; // keeps track of number of Node objects part of the tree

    /**
     * Creates empty LinkedBinaryTree object.
     */
    public LinkedBinaryTree() {
        // empty tree
    }

    // Accessor methods
    /**
     * Returns Position of tree's root (null if empty).
     * 
     * @return Position object
     */
    public Position<K> root() {
        return root;
    }

    /**
     * Validates provided Position object and returns the parent of the Node object stored at the Position.
     * 
     * @param p Position object
     * @return Position object
     * @throws IllegalArgumentException
     */
    public Position<K> parent(Position<K> p) throws IllegalArgumentException {
        Node<K> node = validate(p);
        return node.getParent();
    }

    /**
     * Validates provided Position object and returns the left child of the Node object stored at the Position.
     * 
     * @param p Position object
     * @return Position object
     * @throws IllegalArgumentException
     */
    public Position<K> left(Position<K> p) throws IllegalArgumentException {
        Node<K> node = validate(p);
        return node.getLeft();
    }

    /**
     * Validates provided Position object and returns the right child of the Node object stored at the Position.
     * 
     * @param p Position object
     * @return Position object
     * @throws IllegalArgumentException
     */
    public Position<K> right(Position<K> p) throws IllegalArgumentException {
        Node<K> node = validate(p);
        return node.getRight();
    }

    /**
     * Checks if the provided Position is the Left child of its parent.
     * If so, returns its parent's position. If not, returns null.
     * 
     * @param p The Position of the node in question.
     * @return The Position of the node's parent or null.
     * @throws IllegalArgumentException
     */
    public Position<K> isLeft(Position<K> p) throws IllegalArgumentException {
        Node<K> node = validate(p);
        
        if(node.getParent().getLeft() == node) {
            return node.getParent();
        } else {
            return null;
        }
    }

    /**
     * Checks if the provided Position is the Right child of its parent.
     * If so, returns its parent's position. If not, returns null.
     * 
     * @param p The Position of the node in question.
     * @return The Position of the node's parent or null.
     * @throws IllegalArgumentException
     */
    public Position<K> isRight(Position<K> p) throws IllegalArgumentException {
        Node<K> node = validate(p);
        
        if(node.getParent().getRight() == node) {
            return node.getParent();
        } else {
            return null;
        }
    }

    /**
     * Returns number of Node objects in tree.
     * 
     * Originates from Tree interface and is defined in LinkedBinaryTree.
     * 
     * @return int value
     */
    public int size() {
        return size;
    }

    // Update methods
    /**
     * Places element v at root of empty tree and returns the Position.
     * 
     * @param v value of generic type K
     * @return Position object
     * @throws IllegalStateException
     */
    public Position<K> addRoot(K v) throws IllegalStateException {
        if (!isEmpty()) {
            throw new IllegalStateException("Tree is not empty... grr");
        }

        root = createNode(v, null, null, null);
        size++;
        return root;
    }

    /**
     * Adds element v as left child of parent if there is not already a left child present, then returns its Position.
     * 
     * @param p Position object
     * @param v value of generic type K
     * @return Position object
     * @throws IllegalArgumentException
     */
    public Position<K> addLeft(Position<K> p, K v) throws IllegalArgumentException {
        Node<K> parent = validate(p);
        if (parent.getLeft() != null) {
            throw new IllegalArgumentException("Parent at Position p already has a left child.");
        }

        Node<K> child = createNode(v, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    /**
     * Adds element v as right child of parent if there is not already a right child present, then returns its Position.
     * 
     * @param p Position object
     * @param v value of generic type K
     * @return Position object
     * @throws IllegalArgumentException
     */
    public Position<K> addRight(Position<K> p, K v) throws IllegalArgumentException {
        Node<K> parent = validate(p);
        if (parent.getRight() != null) {
            throw new IllegalArgumentException("Parent at Position p already has a right child.");
        }

        Node<K> child = createNode(v, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    /**
     * Replaces element at provided Position object with provided K value and then returns it.
     * 
     * @param p Position object
     * @param v value of generic type K
     * @return value of generic type K
     * @throws IllegalArgumentException
     */
    public K set(Position<K> p, K v) throws IllegalArgumentException {
        Node<K> node = validate(p);
        K temp = node.getElement();
        node.setElement(v);
        return temp;
    }

    /**
     * Validates the provided Position object and then removes the Node object located there if the Node does not
     * already have two children. Will "promote" one of the Node's children, whichever comes first or is not null,
     * to take the Node's place in the tree. If the Node is the root, will have to additionally promote the child
     * to root. If not, complete the initial promotion by setting the grandparent's new child to the promoted child.
     * Lastly, update size of tree, conduct garbage collection, and return the value previously stored in the removed
     * Node.
     * 
     * 
     * @param p Position object
     * @return value of generic type K
     * @throws IllegalArgumentException
     */
    public K remove(Position<K> p) throws IllegalArgumentException {
        Node<K> node = validate(p);
        if (numChildren(p) == 2) {
            throw new IllegalArgumentException("Node already has two children.");
        }

        Node<K> child = (node.getLeft() != null ? node.getLeft() : node.getRight()); // start replacing from left to right
        if (child != null) { // if both children are not null
            child.setParent(node.getParent()); // child's parent is it's "grandparent" now
        }
        if (node == root) { // if node is root
            root = child; // child is now root
        } else {
            Node<K> parent = node.getParent();
            if (node == parent.getLeft()) { // if what is being removed is the left child
                parent.setLeft(child); // set parent's new left child as the next generation child
            } else {
                parent.setRight(child); // set parent's new right child as the next generation child
            }
        }
        size--;

        // garbage collection xD
        K temp = node.getElement();
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node); // convention for removed node
        return temp;
    }

    /**
     * Returns a String representation of the tree in the default traversal order. Indentation of each item denotes the Node's
     * position's depth in the tree.
     * 
     * @return String value
     */
    public String toString() {
        StringBuilder tabs = new StringBuilder(), print = new StringBuilder();
        Iterable<Position<K>> data = positions();

        for(Position<K> v : data) {
            for(int i = 0; i<depth(v); i++) { // tabs to add
                tabs.append("\t");
            }
            print.append(tabs.toString() + "- " + v.getElement() + "\n");
            tabs.setLength(0); // resets StringBuilder instead of creating new one
        }

        return print.toString();
    }

}
