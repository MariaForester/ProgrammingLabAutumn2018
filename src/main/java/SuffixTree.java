import java.util.*;

class SuffixTreeImplementation {
    private List<Node> nodes = new ArrayList<>(); //list of nodes of the tree

    SuffixTreeImplementation(String inputString) {
        nodes.add(new Node("", new ArrayList<>(), 0));
        for (int i = 0; i < inputString.length(); ++i) {
            addSuffix(inputString.substring(i)); // add every suffix of the string given
        }
    }

    List<Node> getNodes() {
        return this.nodes;
    }

    Node getNode(int node) {
        return this.getNodes().get(node);
    }

    private void addSuffix(String stringToAdd) {
        int currentNodeIndices = 0; //number of the current node. when 0 - root node
        int position = 0; //index of symbol in the suffix string
        while (position < stringToAdd.length()) { //adding each symbol
            char currentSymbol = stringToAdd.charAt(position); //getting symbol that we will be now considering by its index
            List<Integer> childrenOfCurrentNode = this.getNodes().get(currentNodeIndices).getChildren();//getting list of children of the current node
            int positionInSuffix = 0;
            int newNodeIndices;
            while (true) {
                if (positionInSuffix == childrenOfCurrentNode.size()) { // if there are no matching child, remainder of stringToAdd becomes new node
                    // the symbol under `position` is the last symbol of this node`s edge)
                    newNodeIndices = this.getNodes().size(); //giving an inserted node a number. which is the previous node`s number + 1
                    Node newNode = new Node("", new ArrayList<>(), 0); // creating this node
                    newNode.setEdge(stringToAdd.substring(position)); //adding substring of a string, starting with index `position`
                    newNode.setIndices(newNodeIndices);
                    this.getNodes().add(newNode); //adding a node that has just been created to the tree`s list of nodes
                    childrenOfCurrentNode.add(newNodeIndices);//adding a new node number to the list of childrenOfCurrentNode of the current node
                    return;
                }
                newNodeIndices = childrenOfCurrentNode.get(positionInSuffix); //position in the suffix is greater than the list size of childrenOfCurrentNode
                // of the current node;
                if (this.getNodes().get(newNodeIndices).getEdge().charAt(0) == currentSymbol)
                    break; // we have already inserted the last symbol into the tree. break the loop
                positionInSuffix++;//move on to the next symbol in the considered suffix
            }
            String prefixOfSuffixRemained = this.getNodes().get(newNodeIndices).getEdge(); // finding prefix of remaining suffix in common with child
            int i = 0; //initializing a count for prefix
            while (i < prefixOfSuffixRemained.length()) {
                if (stringToAdd.charAt(position + i) != prefixOfSuffixRemained.charAt(i)) {// splitting Node with `newNodeIndices` number
                    int replaceNumber = newNodeIndices;  //switching nodes numbers: the last number goes to the prefix added
                    newNodeIndices = this.getNodes().size();
                    Node nodeForPartInCommon = new Node("", new ArrayList<>(), 0); //new node for the part in common
                    nodeForPartInCommon.setEdge(prefixOfSuffixRemained.substring(0, i));//inserting prefix of the prefix to the split node
                    nodeForPartInCommon.addChild(replaceNumber); //the number of the node added becomes what was the last before the split
                    nodeForPartInCommon.setIndices(newNodeIndices);
                    this.getNodes().add(nodeForPartInCommon);
                    this.getNodes().get(replaceNumber).setEdge(prefixOfSuffixRemained.substring(i));  // old node loses the part in common
                    this.getNodes().get(currentNodeIndices).addChild(positionInSuffix, newNodeIndices);//adding new nodes to the current root
                    break;  // continue down the tree
                }
                i++;
            }
            position += i;  // advancing past part in common
            currentNodeIndices = newNodeIndices;  // continue down the tree
        }
    }

    boolean itHasSuchBranch(String suffixToBeDetected) { // checking if the tree has such a suffix
        for (Node node : this.getNodes()) {
            if (node.getEdge().equals(suffixToBeDetected)) {
                return true;
            }
        }
        return false;
    }
}