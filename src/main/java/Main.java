
public class Main {
    public static void main(String[] args) {
        Drawer drawer = new Drawer();
        drawer.showTree(new SuffixTree("forester"));
        drawer.showTree(new SuffixTree("aaaaa"));
    }
}