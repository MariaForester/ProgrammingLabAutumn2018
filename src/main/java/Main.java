
public class Main {
    public static void main(String[] args) {
        Drawer drawer = new Drawer();
        drawer.showTree(new SuffixTree("forester"));
        drawer.showTree(new SuffixTree("aaaaa"));
        drawer.showTree(new SuffixTree("This also means that in practice, you might use different ways of " +
                "dealing with suffixes that are prefixes of other suffixes, and with non-branching inner nodes. " +
                "For example, if you use the well-known Ukkonen algorithm to construct the tree, you can do that" +
                " without appending a unique character to the end; you just have to make sure that at the end," +
                " after the final iteration, you put non-branching inner nodes to the end of every implicit suffix (" +
                "i.e. every suffix that ends in the middle of an edge)."));
    }
}