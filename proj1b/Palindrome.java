public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> strDeque = new LinkedListDeque<>();
        int length = word.length();
        for (int i = 0;i < length;i++) {
            strDeque.addLast(word.charAt(i));
        }
        return strDeque;
    }
//  The isPalindrome method should return true if the given word is a palindrome, and false otherwise
    public boolean isPalindrome(String word) {
        if(word.length() == 0 || word.length() == 1) {
            return true;
        }
        int first = 0;
        int last = word.length() - 1;
        while (first < last) {
            if (word.charAt(first) != word.charAt(last)) {
                return false;
            }
            first++;
            last--;
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null || word.length() <= 1) {
            return true;
        }
        int len = word.length();
        for (int i = 0; i < len / 2; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(len - i - 1))) {
                return false;
            }
        }
        return true;
    }
}
