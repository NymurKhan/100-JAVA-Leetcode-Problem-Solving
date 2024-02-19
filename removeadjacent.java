public class Solution {
    public int removeAlmostEqualCharacters(String word) {
        int count = 0;
        char[] wordArray = word.toCharArray();

        for (int i = 1; i < wordArray.length; i++) {
            if (Math.abs(wordArray[i] - wordArray[i - 1]) <= 1) {
                count++;
                wordArray[i] = (i + 1 < wordArray.length && (wordArray[i + 1] != 'a' && wordArray[i + 1] != 'b')) ? 'a' : 'z';
            }
        }

        return count;
    }
}
