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
        assertTrue(tree.containsSuffix("ter"));
        assertTrue(tree.containsSuffix("r"));
        assertTrue(tree.containsSuffix("er"));
        assertTrue(tree.containsSuffix("Forester"));
        assertTrue(tree.containsSuffix("rester"));
        assertFalse(tree.containsSuffix("F"));
        assertFalse(tree.containsSuffix("Fores"));
        assertFalse(tree.containsSuffix("j"));
    }

    @Test
    public void lookforit() {
        assertTrue(tree.containsSubstring("ter"));
        assertTrue(tree.containsSubstring("r"));
        assertTrue(tree.containsSubstring("er"));
        assertTrue(tree.containsSubstring("Forester"));
        assertTrue(tree.containsSubstring("o"));
        assertTrue(tree.containsSubstring("rester"));
        assertTrue(tree.containsSubstring("F"));
        assertTrue(tree.containsSubstring("Fores"));
        assertTrue(tree.containsSubstring("ores"));
        assertFalse(tree.containsSubstring("j"));
        assertFalse(tree.containsSubstring("oForest"));
    }

}
