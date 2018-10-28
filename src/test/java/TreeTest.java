import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TreeTest {
    private SuffixTreeImplementation tree = new SuffixTreeImplementation("Forester$");

    @Test
    public void hasString() {
        assertTrue(tree.itHasSuchBranch("ster$"));
        assertTrue(tree.itHasSuchBranch("Forester$"));
        assertFalse(tree.itHasSuchBranch("blahblahblah"));
        assertFalse(tree.itHasSuchBranch("Forester"));
    }

    @Test
    public void amountOfNodes() {
        assertTrue(tree.getNodes().size() == 12);
    }

    @Test
    public void exceptionTest() {
        boolean thrown = false;
        try {
            new SuffixTreeImplementation("Forester");
        } catch (IllegalArgumentException ex) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}
