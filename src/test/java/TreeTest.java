import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TreeTest {
    private SuffixTree tree = new SuffixTree("Forester$");

    @Test
    public void hasBranch() {
        assertTrue(tree.itHasSuchBranch("ster$"));
        assertTrue(tree.itHasSuchBranch("Forester$"));
        assertFalse(tree.itHasSuchBranch("blahblahblah"));
        assertFalse(tree.itHasSuchBranch("Forester"));
    }

    @Test
    public void amountOfNodes() {
        assertEquals(tree.getNodes().size(), 12);
    }

    @Test
    public void lookupForSuffix() {

        List<Integer> children = new ArrayList<>();
        for (Node f : tree.getNodes()) {
            children.add(f.getIndices());
        }

        assertTrue(tree.search("ter$", children));
        assertTrue(tree.search("r$", children));
        assertTrue(tree.search("$", children));
        assertTrue(tree.search("Forester$", children));
        assertTrue(tree.search("rester$", children));
        assertFalse(tree.search("F$", children));
        assertFalse(tree.search("Fores$", children));
        assertFalse(tree.search("j", children));
    }

}
