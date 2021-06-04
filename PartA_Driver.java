import java.util.Scanner;

/**
 * Morsey
 * Part A driver class
 * @author Vazyboi
 */
public class PartA_Driver {

    /**
     * Main class of project.
     * 
     * @param args String array
     */
    public static void main(String[] args) {
        LinkedBinaryTree<String> decisionTree = buildMorseCodeDecodingTree();
        System.out.println(decisionTree.toString()); // print out tree

        Position<String> node = decisionTree.root(); // start at root
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("What shall I convert into morsey for you today? :D\n\ta. Word\n\tb. Phrase\n\tc. File");
            String entry = scan.nextLine(); // a, b, or c
            if(!entry.equalsIgnoreCase("c")) {
                System.out.println("Please enter the word or phrase to convert into morsey :)");
                entry = scan.nextLine(); // the word/phrase

                for(Character c : entry.toCharArray()) {
                    //search through tree for the letter (BFS or DFS, shouldnt really matter tho probably I think)
                    //once letter found, retrace route back to route (left=dah, right=dit)
                    //reverse should be conversion...
                }
            }

        } finally {
            if(scan != null) {
                scan.close();
            }
        }
    }

    /**
     * Builds my tree designed to guess the user's age.
     * 
     * @return LinkedBinaryTree object of type String
     */
    public static LinkedBinaryTree<String> buildMorseCodeDecodingTree() {
        LinkedBinaryTree<String> decisionTree = new LinkedBinaryTree<>();

        decisionTree.addRoot(" "); // root (START)

        Position<String> leftFirstMajorSplit = decisionTree.addLeft(decisionTree.root(), "T"); // gen 1: root-L (-)
        decisionTree.addLeft(leftFirstMajorSplit, "M"); // gen 2: root-L-L (--)
        Position<String> leftSecondMajorSplit = decisionTree.addRight(leftFirstMajorSplit, "N"); // gen 2: root-L-R (-.)

        decisionTree.addLeft(decisionTree.left(leftFirstMajorSplit), "O"); // gen 4: root-L-L-L (---)
        Position<String> pickup = decisionTree.addRight(decisionTree.left(leftFirstMajorSplit), "G"); // gen 4: root-L-L-R (--.)
        
        decisionTree.addLeft(pickup, "Q"); // gen 5: root-L-L-R-L (--.-)
        decisionTree.addRight(pickup, "Z"); // gen 5: root-L-L-R-R (--..)
        
        decisionTree.addLeft(leftSecondMajorSplit, "K"); // gen 4: root-L-R-L (-.-)
        decisionTree.addRight(leftSecondMajorSplit, "D"); // gen 4: root-L-R-R (-..)

        decisionTree.addLeft(decisionTree.left(leftSecondMajorSplit), "Y"); // gen 5: root-L-R-L-L (-.--)
        decisionTree.addRight(decisionTree.left(leftSecondMajorSplit), "C"); // gen 5: root-L-R-L-R (-.-.)
        decisionTree.addLeft(decisionTree.right(leftSecondMajorSplit), "X"); // gen 5: root-L-R-R-L (-..-)
        decisionTree.addRight(decisionTree.right(leftSecondMajorSplit), "B"); // gen 5: root-L-R-R-R (-...)
        
        Position<String> rightFirstMajorSplit = decisionTree.addRight(decisionTree.root(), "E"); // gen 1: root-R (.)
        decisionTree.addLeft(rightFirstMajorSplit, "A"); // gen 2: root-R-L (.-)
        Position<String> rightSecondMajorSplit = decisionTree.addRight(rightFirstMajorSplit, "I"); // gen 2: root-R-R (..)

        pickup = decisionTree.addLeft(decisionTree.left(rightFirstMajorSplit), "W"); // gen 3: root-R-L-L (.--)
        decisionTree.addLeft(pickup, "J"); // gen 5: root-R-L-L-L (.---)
        decisionTree.addRight(pickup, "P"); // gen 5: root-R-L-L-R (.--.)

        pickup = decisionTree.addRight(decisionTree.left(rightFirstMajorSplit), "R"); // gen 3: root-R-L-R (.-.)
        decisionTree.addRight(pickup, "L"); // gen 4: root-R-L-R-R (.-..)

        pickup = decisionTree.addLeft(rightSecondMajorSplit, "U"); // gen 3: root-R-R-L (..-)
        decisionTree.addRight(pickup, "F"); // gen 4: root-R-R-L-R (..-.)
        
        pickup = decisionTree.addRight(rightSecondMajorSplit, "S"); // gen 3: root-R-R-R (...)
        decisionTree.addLeft(pickup, "V"); // gen 4: root-R-R-R-L (...-)
        decisionTree.addRight(pickup, "H"); // gen 4: root-R-R-R-R (....)

        return decisionTree;
    }

}
