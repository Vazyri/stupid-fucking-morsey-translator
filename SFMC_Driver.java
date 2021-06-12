import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Morsey
 * Part A driver class
 * @author Vazyboi
 */
public class SFMC_Driver {

    /**
     * Main class of project.
     * SFMC_Driver driver class
     * @param args String array.
     */
    public static void main(String[] args) {
        LinkedBinaryTree<Character> decoderTree = buildMorseCodeDecodingTree();
        System.out.println(decoderTree.toString()); // print out tree

        Scanner scan = new Scanner(System.in);
        try {
            System.out.print("> What shall I convert into morsey for you today? :D\n\ta. Word/Phrase\n\tb. File\n\tc. Cancel\n> ");
            String entry = scan.nextLine(); // a, b, c
            while(!entry.equalsIgnoreCase("c")) {
                if(entry.equalsIgnoreCase("a")) {
                    System.out.print("> Give me give me for morsey morsey! :)\n> ");
                    entry = scan.nextLine(); // the word/phrase
    
                    ArrayList<ArrayList<Character>> morsey = new ArrayList<>();
                    Iterable<Position<Character>> findMe = decoderTree.positions();
                    for(Character c : entry.toCharArray()) {
                        for(Position<Character> k : findMe) {
                            if(Character.toUpperCase(c) == k.getElement()) {
                                morsey.add(traceRoutetoRoot(decoderTree, k));
                            }
                        }
                    }
    
                    System.out.print("\n> Morsey: " +morsey.toString().replace("[","").replace("]","").replace(","," "));

                    System.out.print("\n> Again?\n\ta. Word/Phrase\n\tb. File\n\tc. Cancel\n> ");
                    entry = scan.nextLine();
                }
            }

        } finally {
            if(scan != null) {
                scan.close();
            }
        }

        System.out.println("Goodbye...");
    }

    /**
     * Traces the route between the letter to root. Route consists of Ls and Rs.
     * 
     * Left = dah (-)
     * Right = dit (.)
     * 
     * @param foundYou The position of the letter in the tree.
     * @return Array of Characters containing containing dits and dahs that represent the letter.
     */
    public static ArrayList<Character> traceRoutetoRoot(LinkedBinaryTree<Character> decoderTree, Position<Character> foundYou) {
        ArrayList<Character> route = new ArrayList<>();
        Position<Character> origy = foundYou;
        while(foundYou.getElement() != ' ') { // not at root
            foundYou = decoderTree.isLeft(foundYou);
            if(foundYou == null) {
                foundYou = decoderTree.isRight(origy);
                route.add('.');
            } else {
                route.add('-');
            }   
        }

        Collections.reverse(route);
        return route;
    }

    /**
     * Builds morsey decoder tree.
     * 
     * @return LinkedBinaryTree object of type Character.
     */
    public static LinkedBinaryTree<Character> buildMorseCodeDecodingTree() {
        LinkedBinaryTree<Character> decoderTree = new LinkedBinaryTree<>();

        decoderTree.addRoot(' '); // root (START)

        Position<Character> leftFirstMajorSplit = decoderTree.addLeft(decoderTree.root(), 'T'); // gen 1: root-L (-)
        decoderTree.addLeft(leftFirstMajorSplit, 'M'); // gen 2: root-L-L (--)
        Position<Character> leftSecondMajorSplit = decoderTree.addRight(leftFirstMajorSplit, 'N'); // gen 2: root-L-R (-.)

        decoderTree.addLeft(decoderTree.left(leftFirstMajorSplit), 'O'); // gen 4: root-L-L-L (---)
        Position<Character> pickup = decoderTree.addRight(decoderTree.left(leftFirstMajorSplit), 'G'); // gen 4: root-L-L-R (--.)
        
        decoderTree.addLeft(pickup, 'Q'); // gen 5: root-L-L-R-L (--.-)
        decoderTree.addRight(pickup, 'Z'); // gen 5: root-L-L-R-R (--..)
        
        decoderTree.addLeft(leftSecondMajorSplit, 'K'); // gen 4: root-L-R-L (-.-)
        decoderTree.addRight(leftSecondMajorSplit, 'D'); // gen 4: root-L-R-R (-..)

        decoderTree.addLeft(decoderTree.left(leftSecondMajorSplit), 'Y'); // gen 5: root-L-R-L-L (-.--)
        decoderTree.addRight(decoderTree.left(leftSecondMajorSplit), 'C'); // gen 5: root-L-R-L-R (-.-.)
        decoderTree.addLeft(decoderTree.right(leftSecondMajorSplit), 'X'); // gen 5: root-L-R-R-L (-..-)
        decoderTree.addRight(decoderTree.right(leftSecondMajorSplit), 'B'); // gen 5: root-L-R-R-R (-...)
        
        Position<Character> rightFirstMajorSplit = decoderTree.addRight(decoderTree.root(), 'E'); // gen 1: root-R (.)
        decoderTree.addLeft(rightFirstMajorSplit, 'A'); // gen 2: root-R-L (.-)
        Position<Character> rightSecondMajorSplit = decoderTree.addRight(rightFirstMajorSplit, 'I'); // gen 2: root-R-R (..)

        pickup = decoderTree.addLeft(decoderTree.left(rightFirstMajorSplit), 'W'); // gen 3: root-R-L-L (.--)
        decoderTree.addLeft(pickup, 'J'); // gen 5: root-R-L-L-L (.---)
        decoderTree.addRight(pickup, 'P'); // gen 5: root-R-L-L-R (.--.)

        pickup = decoderTree.addRight(decoderTree.left(rightFirstMajorSplit), 'R'); // gen 3: root-R-L-R (.-.)
        decoderTree.addRight(pickup, 'L'); // gen 4: root-R-L-R-R (.-..)

        pickup = decoderTree.addLeft(rightSecondMajorSplit, 'U'); // gen 3: root-R-R-L (..-)
        decoderTree.addRight(pickup, 'F'); // gen 4: root-R-R-L-R (..-.)
        
        pickup = decoderTree.addRight(rightSecondMajorSplit, 'S'); // gen 3: root-R-R-R (...)
        decoderTree.addLeft(pickup, 'V'); // gen 4: root-R-R-R-L (...-)
        decoderTree.addRight(pickup, 'H'); // gen 4: root-R-R-R-R (....)

        return decoderTree;
    }

}
