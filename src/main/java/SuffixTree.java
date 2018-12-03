import java.util.*;

class SuffixTree {
    private List<Node> nodes = new ArrayList<>(); //list of nodes of the tree

    SuffixTree(String inputString) {
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

    private int counter = 0; //initializing a count for prefixes

    private void addSuffix(String stringToAdd) {
        int currentNodeIndices = 0; //indices of the current node. when 0 - root node
        int position = 0; //index of symbol in the suffix string
        while (position < stringToAdd.length()) { //adding each symbol
            char currentSymbol = stringToAdd.charAt(position); //getting symbol by its index that we will be now considered
            List<Integer> childrenOfCurrentNode = this.getNode(currentNodeIndices).getChildren();//getting list of children of the current node
            int positionInSuffix = 0;
            int newNodeIndices = 0;
            while (true) {
                createNodeForSubstring(positionInSuffix, newNodeIndices, position, childrenOfCurrentNode, stringToAdd);
                newNodeIndices = childrenOfCurrentNode.get(positionInSuffix); //position in the suffix is greater than the list size of childrenOfCurrentNode
                // of the current node;
                if (this.getNode(newNodeIndices).getEdge().charAt(0) == currentSymbol) {
                    break; // we have already inserted the last symbol into the tree. break the loop
                }
                positionInSuffix++;//move on to the next symbol in the considered suffix
            }
            String prefixOfSuffixRemained = this.getNode(newNodeIndices).getEdge(); // finding prefix of remaining suffix in common with child
            split(prefixOfSuffixRemained, stringToAdd, position, newNodeIndices, currentNodeIndices, positionInSuffix);
            position += counter;  // advancing past part in common
            currentNodeIndices = newNodeIndices;  // continue down the tree
            counter = 0;
        }
    }

    private void split(String prefixOfSuffixRemained, String stringToAdd, int position, int newNodeIndices,
                       int currentNodeIndices, int positionInSuffix) {
        while (counter < prefixOfSuffixRemained.length()) {
            if (stringToAdd.charAt(position + counter) != prefixOfSuffixRemained.charAt(counter)) {// splitting Node with `newNodeIndices` number
                int replaceNumber = newNodeIndices;  //switching nodes numbers: the last number goes to the prefix added
                newNodeIndices = this.getNodes().size();
                Node nodeForPartInCommon = new Node("", new ArrayList<>(), 0); //new node for the part in common
                nodeForPartInCommon.setEdge(prefixOfSuffixRemained.substring(0, counter));//inserting prefix of the prefix to the split node
                nodeForPartInCommon.addChild(replaceNumber); //the number of the node added becomes what was the last before the split
                nodeForPartInCommon.setIndices(newNodeIndices);
                this.getNodes().add(nodeForPartInCommon);
                this.getNodes().get(replaceNumber).setEdge(prefixOfSuffixRemained.substring(counter));  // old node loses the part in common
                this.getNodes().get(currentNodeIndices).addChild(positionInSuffix, newNodeIndices);//adding new nodes to the current root
                break;  // continue down the tree
            }
            counter++;
        }
    }

    private void createNodeForSubstring(int positionInSuffix, int newNodeIndices, int position,
                                        List<Integer> childrenOfCurrentNode, String stringToAdd) {
        if (positionInSuffix == childrenOfCurrentNode.size()) { // if there are no matching child, remainder of stringToAdd becomes new node
            // the symbol under `position` is the last symbol of this node`s edge)
            newNodeIndices = this.getNodes().size(); //giving an inserted node indices
            Node newNode = new Node(stringToAdd.substring(position), new ArrayList<>(), newNodeIndices); // creating this node,  edge: adding substring of a string, starting with index `position`
            this.getNodes().add(newNode); //adding a node that has just been created to the tree`s list of nodes
            childrenOfCurrentNode.add(newNodeIndices);//adding a new node number to the list of childrenOfCurrentNode of the current node
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

    private String count = "";

    boolean search(String target, List<Integer> currentChildren) {
        StringBuilder builder = new StringBuilder();
        deepSearch(target, currentChildren);
        if (count.equals(target)) {
            count = "";
            return true;
        }
        count = "";
        return false;
    }

    private void deepSearch(String target, List<Integer> currentChildren) {
        List<String> prefixes = new ArrayList<>();
        for (int i = 1; i < target.length() + 1; i++) {
            prefixes.add(target.substring(0, i));
        }
        for (String prefix : prefixes) {
            for (Integer child : currentChildren) {
                if (this.getNode(child).getEdge().equals(prefix)) {
                    count += this.getNode(child).getEdge();
                    if (count.equals(target)) return;
                }
                if (count.length() >= prefix.length() && count.substring(count.length()
                        - prefix.length()).equals(prefix) && count.length() < target.length()) {
                    List<Integer> youngerChildren = this.getNode(child).getChildren();
                    String remainder = target.substring(count.length());
                    deepSearch(remainder, youngerChildren);

                }
                List<Integer> listForLeafSearch = this.getNode(child).getChildren();
                for (int node: listForLeafSearch) {
                    if (this.getNode(node).getChildren().size() != 0 && count.length() >= prefix.length()
                            && count.substring(count.length()
                            - prefix.length()).equals(prefix) && count.length() < target.length()) {
                        deepSearch(target.substring(count.length()), this.getNode(node).getChildren());
                    }
                }
                if (this.getNode(child).getEdge().equals(prefix)) {
                    currentChildren = this.getNode(child).getChildren();
                }
            }
        }
    }
}




