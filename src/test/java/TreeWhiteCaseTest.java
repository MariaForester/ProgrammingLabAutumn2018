import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TreeWhiteCaseTest {
    private SuffixTree firstTree = new SuffixTree("Forester");
    private SuffixTree secondTree = new SuffixTree("");

    @Test
    public void hasBranch() {
        assertTrue(firstTree.itHasSuchBranch("ster$"));
        assertTrue(firstTree.itHasSuchBranch("Forester$"));
        assertFalse(firstTree.itHasSuchBranch("blahblahblah"));
        assertFalse(firstTree.itHasSuchBranch("Forester"));

        assertFalse(secondTree.itHasSuchBranch("a"));
        assertFalse(secondTree.itHasSuchBranch("Forester"));
    }

    @Test
    public void amountOfNodes() {
        assertEquals(firstTree.getNodes().size(), 12);

        assertEquals(secondTree.getNodes().size(), 2);
    }
}
