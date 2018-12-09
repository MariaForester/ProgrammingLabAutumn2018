import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TreeTest {
    private SuffixTree firstTree = new SuffixTree("Forester");
    private SuffixTree secondTree = new SuffixTree("");
    private SuffixTree thirdTree = new SuffixTree("aaaaa");
    private SuffixTree fourthTree = new SuffixTree("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    private SuffixTree fifthTree = new SuffixTree("This also means that in practice, you might use different ways of " +
            "dealing with suffixes that are prefixes of other suffixes, and with non-branching inner nodes. " +
            "For example, if you use the well-known Ukkonen algorithm to construct the tree, you can do that" +
            " without appending a unique character to the end; you just have to make sure that at the end," +
            " after the final iteration, you put non-branching inner nodes to the end of every implicit suffix (" +
            "i.e. every suffix that ends in the middle of an edge).");

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

        assertTrue(fifthTree.containsSuffix("."));
        assertTrue(fifthTree.containsSuffix("Ukkonen algorithm to construct the tree, you can do that " +
                "without appending a unique character to the end; you just have to make sure that at the end, " +
                "after the final iteration, you put non-branching inner nodes to the end of every implicit suffix " +
                "(i.e. every suffix that ends in the middle of an edge)."));
        assertTrue(fifthTree.containsSuffix(" edge)."));
        assertTrue(fifthTree.containsSuffix(" final iteration, you put non-branching inner nodes to the end of every " +
                "implicit suffix (i.e. every suffix that ends in the middle of an edge)."));
        assertTrue(fifthTree.containsSuffix("This also means that in practice, you might use different ways of dealing" +
                " with suffixes that are prefixes of other suffixes, and with non-branching inner nodes. For example," +
                " if you use the well-known Ukkonen algorithm to construct the tree, you can do that without appending " +
                "a unique character to the end; you just have to make sure that at the end, after the final iteration," +
                " you put non-branching inner nodes to the end of every implicit suffix (i.e. every suffix that ends" +
                " in the middle of an edge)."));
        assertFalse(fifthTree.containsSuffix("This also means that in practice"));
        assertFalse(fifthTree.containsSuffix(" edge)"));
        assertFalse(fifthTree.containsSuffix(")"));
        assertFalse(fifthTree.containsSuffix("kkkThis also means that in practice, you might use different ways of dealing\" +\n" +
                "                \" with suffixes that are prefixes of other suffixes, and with non-branching inner" +
                " nodes. For example,\" +\n" +
                "                \" if you use the well-known Ukkonen algorithm to construct the tree, you can do that " +
                "without appending \" +\n" +
                "                \"a unique character to the end; you just have to make sure that at the end, after the " +
                "final iteration,\" +\n" +
                "                \" you put non-branching inner nodes to the end of every implicit suffix (i.e. every s" +
                "uffix that ends\" +\n" +
                "                \" in the middle of an edge)."));
        assertFalse(fifthTree.containsSuffix("in middle of an edge)."));
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
        assertTrue(thirdTree.containsSubstring("a"));
        assertFalse(thirdTree.containsSubstring("aaaaaa"));
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

        assertTrue(fifthTree.containsSubstring("This also means that in practice, you might use different ways of dealing" +
                " with suffixes that are prefixes of other suffixes, and with non-branching inner nodes. For example," +
                " if you use the well-known Ukkonen algorithm to construct the tree, you can do that without appending " +
                "a unique character to the end; you just have to make sure that at the end, after the final iteration," +
                " you put non-branching inner nodes to the end of every implicit suffix (i.e. every suffix that ends" +
                " in the middle of an edge)."));
        assertTrue(fifthTree.containsSubstring("This"));
        assertTrue(fifthTree.containsSubstring("For example"));
        assertFalse(fifthTree.containsSubstring("meens"));
        assertFalse(fifthTree.containsSubstring("oThis also means that in practice, you might use different ways of dealing" +
                " with suffixes that are prefixes of other suffixes, and with non-branching inner nodes. For example," +
                " if you use the well-known Ukkonen algorithm to construct the tree, you can do that without appending " +
                "a unique character to the end; you just have to make sure that at the end, after the final iteration," +
                " you put non-branching inner nodes to the end of every implicit suffix (i.e. every suffix that ends" +
                " in the middle of an edge)."));
        assertFalse(fifthTree.containsSubstring("edgggfgfghgedge"));
        assertFalse(fifthTree.containsSubstring("fdfdhfihdkjhkjgr"));
    }

}
