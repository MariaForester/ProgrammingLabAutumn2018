import java.util.List;

class Drawer {
    void showTree(SuffixTree tree) {
        if (tree.getNodes().size() == 2) {
            System.out.println("The input string was empty. There is nothing to show");
            return;
        }
        draw(0, "", tree);
    }

    private void draw(int node, String prefix, SuffixTree tree) {
        List<Integer> children = tree.getNode(node).getChildren();
        if (children.isEmpty()) {
            System.out.println("- " + tree.getNode(node).getEdge());
            return;
        }
        System.out.println("┐ " + tree.getNode(node).getEdge());
        for (int i = 0; i < children.size() - 1; i++) {
            Integer child = children.get(i);
            System.out.print(prefix + "├─");
            draw(child, prefix + "│ ", tree);
        }
        System.out.print(prefix + "└─");
        draw(children.get(children.size() - 1), prefix + "  ", tree);
    }
}