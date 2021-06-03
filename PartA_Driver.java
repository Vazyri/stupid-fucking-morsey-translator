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

        // Position<String> node = decisionTree.root(); // start at root
        // Scanner scan = null;
        // try {
        //     int guess = 0, celebrated = 0; // contains working guess, if user has celebrated birthday yet
        //     String answer = "";

        //     System.out.println(node.getElement());
        //     scan = new Scanner(System.in);
        //     String input = "";

        //     do {
        //         input = scan.nextLine(); // get input from user

        //         // parse through inputs
        //         if(input.equalsIgnoreCase("yes")) {
        //             node = decisionTree.left(node); // yes = left
        //         } else if(input.equalsIgnoreCase("no")) {
        //             node = decisionTree.right(node); // no = right
        //         } else if(Character.isDigit(input.charAt(0))) { // separate so the rest of the tree functionality can work with any other tree
        //             if(node.getElement().contains("Pick")) { // if at "Pick a number..." node
        //                 guess = guessAlgoPickStage(node, Integer.parseInt(input));
        //             } else if(node.getElement().contains("celebrated")) { // if at "if you have celebrated your..." node
        //                 celebrated = Integer.parseInt(input);
        //             } else if(node.getElement().contains("YOB")) { // if at "what is your year..." node
        //                 input = guessAlgoYOBStage(node, Integer.parseInt(input), guess, celebrated);
        //                 answer = "The number you chose is " + input.charAt(0) + " and your age is " + input.substring(1, 3); // prepare final answer
        //                 decisionTree.set(decisionTree.left(node), answer); // update next node
        //             }

        //             node = decisionTree.left(node); // every node that takes in a number does not have a sibling and are a left child
        //         } else {
        //             throw new IllegalArgumentException("Input may only be yes, no, or a number.");
        //         }

        //         System.out.println(node.getElement()); // print new node's value

        //     } while(!decisionTree.isExternal(node));
        // } finally {
        //     if(scan != null) {
        //         scan.close();
        //     }
        // }
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

    /**
     * Below lies the magic... if you are eager to understand it, for not all take advantage, you must journey past your mind and your planet. 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     *
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     *
     * 
     * 
     * Are you sure?
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     *
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     *
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * You cannot handle...
     *
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     *
     * 
     * 
     * Oh, cmon...
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     *
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     *
     */

    /**
     * First part of the algorithm to guess the user's age. This is implemented for the stage where the user chooses their number. Returns the updated number.
     * 
     * @param num integer value
     * @return integer value
     */
    public static int guessAlgoPickStage(Position<String> node, int saveNum) {
        saveNum *= 2;
        saveNum += 5;
        saveNum *= 50;

        return saveNum;
    }

    /**
     * Second part of the algorithm to guess the user's age. This is implemented for the stage where the user provides their YOB. Returns a String of the final guess.
     * 
     * @param node Position object of type String 
     * @param celebrated integer value
     * @param saveNum integer value
     * @param yob integer value
     * @return
     */
    public static String guessAlgoYOBStage(Position<String> node, int yob, int saveNum, int celebrated) {
        if(celebrated % 2 == 0)  { // if celebrated birthday already
            saveNum += 1771;
            saveNum -= yob;
        } else { // if not
            saveNum += 1770;
            saveNum -= yob;
        }

        return String.valueOf(saveNum);
    }

}
