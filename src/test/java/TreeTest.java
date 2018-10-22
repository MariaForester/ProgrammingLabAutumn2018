import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TreeTest {
    private SuffixTreeImplementation tree = new SuffixTreeImplementation("Forester$");

    @Test
    public void hasString() {
        assertTrue(tree.doesItHaveSuchSuffix("ster$"));
        assertTrue(tree.doesItHaveSuchSuffix("Forester$"));
        assertFalse(tree.doesItHaveSuchSuffix("blahblahblah"));
        assertFalse(tree.doesItHaveSuchSuffix("Forester"));
    }

    @Test
    public void amountOfNodes() {
        assertTrue(tree.getNodes().size() == 12);
    }
}
