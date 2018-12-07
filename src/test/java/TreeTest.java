import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        assertTrue(tree.search("ter"));
        assertTrue(tree.search("r"));
        assertTrue(tree.search("er"));
        assertTrue(tree.search("Forester"));
        assertTrue(tree.search("rester"));
        assertFalse(tree.search("F"));
        assertFalse(tree.search("Fores"));
        assertFalse(tree.search("j"));
    }
}
