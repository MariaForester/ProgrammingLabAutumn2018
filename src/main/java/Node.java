
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Node {
    private String edge;             // a substring of the input string belonging to the node
    private List<Integer> children;  // list of child nodes
    private int number; //node`s number

    Node() { //a node itself
        this.children = new ArrayList<>();
        this.edge = "";
        this.number = 0;
    }

    private int getNumber() {
        return number;
    }

    void setNumber(int numberToEstablish) {
        this.number = numberToEstablish;
    }

    String getEdge() {
        return edge;
    }

    void setEdge(String edgeToEstablish) {
        this.edge = edgeToEstablish;
    }

    List<Integer> getChildren() {
        return children;
    }

    void addChild(Integer childToInsert) {
        this.children.add(childToInsert);
    }

    void addChild(Integer positionForInsertion, Integer childToInsert) {
        this.children.set(positionForInsertion, childToInsert);
    }

    void addCMultipleChildren(List<Integer> childrenToInsert) {
        this.children.addAll(childrenToInsert);
    }

    boolean hasChild(Integer childNumber) {
        return this.children.contains(childNumber);
    }

    boolean hasChildren(List<Integer> childrenNumbers) {
        return this.children.containsAll(childrenNumbers);
    }

    public String toString() {
        return "This node is node n." + this.getNumber() + "and this node`s edge is" + this.getEdge();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        Node node = (Node) o;
        return Objects.equals(edge, node.edge) && Objects.equals(children, node.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(edge, children);
    }
}
