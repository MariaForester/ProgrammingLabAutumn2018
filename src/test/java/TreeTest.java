import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TreeTest {
    private SuffixTree firstTree = new SuffixTree("Forester");
    private SuffixTree secondTree = new SuffixTree("");
    private SuffixTree thirdTree = new SuffixTree("aaaaa");
    private SuffixTree fourthTree = new SuffixTree("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");


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

    @Test
    public void lookupForSuffix() {
        assertTrue(firstTree.containsSuffix("ter"));
        assertTrue(firstTree.containsSuffix("r"));
        assertTrue(firstTree.containsSuffix("er"));
        assertTrue(firstTree.containsSuffix("Forester"));
        assertTrue(firstTree.containsSuffix("rester"));
        assertFalse(firstTree.containsSuffix("F"));
        assertFalse(firstTree.containsSuffix("Fores"));
        assertFalse(firstTree.containsSuffix("j"));
        assertFalse(firstTree.containsSuffix("fForester"));

        assertFalse(secondTree.containsSuffix("a"));
        assertFalse(secondTree.containsSuffix("ffdfdf"));
        assertFalse(secondTree.containsSuffix("j"));


        assertTrue(thirdTree.containsSuffix("aaaaa"));
        assertTrue(thirdTree.containsSuffix("a"));
        assertTrue(thirdTree.containsSuffix("aaaa"));
        assertFalse(thirdTree.containsSuffix("aaaaaa"));
        assertFalse(thirdTree.containsSuffix("b"));
        assertFalse(thirdTree.containsSuffix("ab"));
        assertFalse(thirdTree.containsSuffix("ba"));

        assertTrue(fourthTree.containsSuffix("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        assertTrue(fourthTree.containsSuffix("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        assertTrue(fourthTree.containsSuffix("a"));
        assertTrue(fourthTree.containsSuffix("aaaaaaaaa"));
        assertFalse(fourthTree.containsSuffix("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        assertFalse(fourthTree.containsSuffix("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        assertFalse(fourthTree.containsSuffix("aaab"));
        assertFalse(fourthTree.containsSuffix("baaaa"));
    }

    @Test
    public void lookforit() {
        assertTrue(firstTree.containsSubstring("ter"));
        assertTrue(firstTree.containsSubstring("r"));
        assertTrue(firstTree.containsSubstring("er"));
        assertTrue(firstTree.containsSubstring("Forester"));
        assertTrue(firstTree.containsSubstring("o"));
        assertTrue(firstTree.containsSubstring("rester"));
        assertTrue(firstTree.containsSubstring("F"));
        assertTrue(firstTree.containsSubstring("Fores"));
        assertTrue(firstTree.containsSubstring("ores"));
        assertFalse(firstTree.containsSubstring("j"));
        assertFalse(firstTree.containsSubstring("oForest"));

        assertFalse(secondTree.containsSubstring("a"));
        assertFalse(secondTree.containsSubstring("ff"));
        assertFalse(secondTree.containsSubstring("j"));
        assertFalse(secondTree.containsSubstring("1"));

        assertTrue(thirdTree.containsSubstring("aaaaa"));
        assertTrue(thirdTree.containsSubstring("aaaa"));
        assertTrue(thirdTree.containsSubstring("aaa"));
        assertTrue(thirdTree.containsSubstring("aa"));
        assertTrue(thirdTree.containsSubstring("a"));
        assertFalse(thirdTree.containsSubstring("aaaaaa"));
        assertFalse(thirdTree.containsSubstring("aabaaa"));
        assertFalse(thirdTree.containsSubstring("ab"));
        assertFalse(thirdTree.containsSubstring("b"));

        assertTrue(fourthTree.containsSubstring("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        assertTrue(fourthTree.containsSubstring("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        assertTrue(fourthTree.containsSubstring("a"));
        assertTrue(fourthTree.containsSubstring("aa"));
        assertFalse(fourthTree.containsSubstring("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        assertFalse(fourthTree.containsSubstring("ab"));
        assertFalse(fourthTree.containsSubstring("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        assertFalse(fourthTree.containsSubstring("baaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

}
