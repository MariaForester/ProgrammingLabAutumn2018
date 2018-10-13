import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Node {
    String edge;             // a substring of the input string
    List<Integer> children;  // list of child nodes

    public Node() {
        this.children = new ArrayList<>();
        this.edge = "";
    }

    String getEdge() {
        return edge;
    }

    List<Integer> getChildren() {
        return children;
    }

    boolean hasChild(Integer childNumber) {
        return this.children.contains(childNumber);
    }

    boolean hasChildren(List<Integer> childrenNumbers) {
        return this.children.containsAll(childrenNumbers);
    }

    void addChild(Integer newChildNumber) {
        this.children.add(newChildNumber);
    }

    void addChild(Integer positionForInsertion, Integer newChildNumber) {
        this.children.set(positionForInsertion, newChildNumber);
    }

    void addCMultipleChildren(List<Integer> newChildrenNumbers) {
        this.children.addAll(newChildrenNumbers);
    }

    public String toString() {
        return "This node`s edge is" + this.getEdge();
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
