import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TreeTest {
    private SuffixTree tree = new SuffixTree("Forester");

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
        assertTrue(tree.containsSubstring("ter"));
        assertTrue(tree.containsSubstring("r"));
        assertTrue(tree.containsSubstring("er"));
        assertTrue(tree.containsSubstring("Forester"));
        assertTrue(tree.containsSubstring("rester"));
        assertFalse(tree.containsSubstring("F"));
        assertFalse(tree.containsSubstring("Fores"));
        assertFalse(tree.containsSubstring("j"));
    }
}
