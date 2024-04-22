import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void test1() {
        int n = 6;
        int[][] edges = {
                {0, 1, 4},
                {0, 2, 1},
                {1, 3, 2},
                {1, 4, 3},
                {1, 5, 1},
                {2, 3, 1},
                {3, 5, 3},
                {4, 5, 2}
        };

        boolean[] expected = {true, true, true, false, true, true, true, false};
        boolean[] actual = new Solution().findAnswer(n, edges);

        Assert.assertArrayEquals(expected, actual);

    }
}
