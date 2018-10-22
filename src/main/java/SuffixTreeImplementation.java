import java.util.*;

class SuffixTreeImplementation {
    private List<Node> nodes = new ArrayList<>(); //list of nodes of the tree

    SuffixTreeImplementation(String inputString) {
        String pattern = "(.)*\\$";
        if (!inputString.matches(pattern)) {
            throw new IllegalArgumentException("The entered string is not supported by the tree. Please, put in the string of 'example$' pattern");
        }
        nodes.add(new Node());
        for (int i = 0; i < inputString.length(); ++i) {
            addSuffix(inputString.substring(i)); // add every suffix of the string given
        }
    }

    List<Node> getNodes() {
        return this.nodes;
    }

    private void addSuffix(String stringToAdd) {
        int currentNodeNumber = 0; //number of the current node. when 0 - root node
        int position = 0; //index of symbol in the suffix string
        while (position < stringToAdd.length()) { //adding each symbol
            char currentSymbol = stringToAdd.charAt(position); //getting symbol that we will be now considering by its index
            List<Integer> childrenOfCurrentNode = nodes.get(currentNodeNumber).getChildren();//getting list of children of the current node
            int positionInSuffix = 0;
            int newNodeNumber;
            while (true) {
                if (positionInSuffix == childrenOfCurrentNode.size()) { // if there are no matching child, remainder of stringToAdd becomes new node
                    // the symbol under `position` is the last symbol of this node`s edge)
                    newNodeNumber = nodes.size(); //giving an inserted node a number. which is the previous node`s number + 1
                    Node newNode = new Node(); // creating this node
                    newNode.setEdge(stringToAdd.substring(position)); //adding substring of a string, starting with index `position`
                    newNode.setNumber(newNodeNumber);
                    nodes.add(newNode); //adding a node that has just been created to the tree`s list of nodes
                    childrenOfCurrentNode.add(newNodeNumber);//adding a new node number to the list of childrenOfCurrentNode of the current node
                    return;
                }
                newNodeNumber = childrenOfCurrentNode.get(positionInSuffix); //position in the suffix is greater than the list size of childrenOfCurrentNode
                // of the current node;
                if (nodes.get(newNodeNumber).getEdge().charAt(0) == currentSymbol)
                    break; // we have already inserted the last symbol into the tree. break the loop
                positionInSuffix++;//move on to the next symbol in the considered suffix
            }
            String prefixOfSuffixRemained = nodes.get(newNodeNumber).getEdge(); // finding prefix of remaining suffix in common with child
            int i = 0; //initializing a count for prefix
            while (i < prefixOfSuffixRemained.length()) {
                if (stringToAdd.charAt(position + i) != prefixOfSuffixRemained.charAt(i)) {// splitting Node with `newNodeNumber` number
                    int replaceNumber = newNodeNumber;  //switching nodes numbers: the last number goes to the prefix added
                    newNodeNumber = nodes.size();
                    Node nodeForPartInCommon = new Node(); // new node for the part in common
                    nodeForPartInCommon.setEdge(prefixOfSuffixRemained.substring(0, i));//inserting prefix of the prefix to the split node
                    nodeForPartInCommon.addChild(replaceNumber); //the number of the node added becomes what was the last before the split
                    nodeForPartInCommon.setNumber(newNodeNumber);
                    nodes.add(nodeForPartInCommon);
                    nodes.get(replaceNumber).setEdge(prefixOfSuffixRemained.substring(i));  // old node loses the part in common
                    nodes.get(currentNodeNumber).addChild(positionInSuffix, newNodeNumber);//adding new nodes to the current root
                    break;  // continue down the tree
                }
                i++;
            }
            position += i;  // advancing past part in common
            currentNodeNumber = newNodeNumber;  // continue down the tree
        }
    }
}