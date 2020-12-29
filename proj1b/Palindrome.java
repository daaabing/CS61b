public class Palindrome {

    public Deque<Character> wordToDeque(String word) {

        Deque<Character> results = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++){
            results.addLast(word.charAt(i));
        }
        return results;
    }

    // iteratively
    public boolean isPalindrome(String word) {
        if (word.length() == 1) {
            return true;
        }

        Deque<Character> words = wordToDeque(word);

        while (words.size() > 1) {
            if (words.removeFirst() != words.removeLast()) {
                return false;
            }
        }

        return true;
    }

    // recursive
    public boolean isPalindrome2(String word) {
        Deque<Character> words = wordToDeque(word);
        return _isPalindrome(words);
    }

    private boolean _isPalindrome(Deque<Character> words) {
        if (words.size() <= 1) {
            return true;
        } else return words.removeFirst() == words.removeLast();
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 1) {
            return true;
        }

        Deque<Character> words = wordToDeque(word);

        while (words.size() > 1) {
            if (!cc.equalChars(words.removeFirst(), words.removeLast())) {
                return false;
            }
        }
        return true;
    }

}
