import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testPalindrome() {
        assertTrue(palindrome.isPalindrome("aba"));
        assertFalse(palindrome.isPalindrome("cat"));

        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertFalse(palindrome.isPalindrome("aA"));
    }

    @Test
    public void testOboPalindrome() {

        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertTrue(palindrome.isPalindrome("a", offByOne));

        assertFalse(palindrome.isPalindrome("base", offByOne));
        assertFalse(palindrome.isPalindrome("baseball", offByOne));
        assertFalse(palindrome.isPalindrome("aba", offByOne));

    }

}
