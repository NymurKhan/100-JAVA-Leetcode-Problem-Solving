class Solution 
{
    public int longestCommonPrefix(int[] arr1, int[] arr2) 
    {
        Trie trie = new Trie();
        // Insert numbers from both arrays into the Trie
        for (int num : arr1) 
        {
            trie.insert(Integer.toString(num), true);
        }
        for (int num : arr2) 
        {
            trie.insert(Integer.toString(num), false);
        }
        // Find and return the length of the longest common prefix
        String longestPrefix = trie.getLongestCommonPrefix();
        return longestPrefix.length();
    }
}

class Trie 
{
    private TrieNode root;
    private String longestPrefix;

    public Trie() 
    {
        root = new TrieNode();
        longestPrefix = "";
    }

    // Unified insert method with a flag indicating which array it belongs to
    public void insert(String word, boolean isFirstArray) 
    {
        TrieNode current = root;
        for (char c : word.toCharArray()) 
        {
            if (current.children[c - '0'] == null) 
            {
                current.children[c - '0'] = new TrieNode();
            }
            current = current.children[c - '0'];
            // Set the appropriate flag based on the array
            if (isFirstArray) 
            {
                current.flag1 = true;
            } 
            else 
            {
                current.flag2 = true;
            }
        }
    }

    public String getLongestCommonPrefix() 
    {
        explore(root, new StringBuilder());
        return this.longestPrefix;
    }

    private void explore(TrieNode node, StringBuilder currentPrefix) 
    {
        if (node == null) return;

        for (int i = 0; i < 10; i++) 
        {
            if (node.children[i] != null && node.children[i].flag1 && node.children[i].flag2) 
            {
                currentPrefix.append(i);
                if (currentPrefix.length() > this.longestPrefix.length()) 
                {
                    this.longestPrefix = currentPrefix.toString();
                }
                explore(node.children[i], currentPrefix);
                currentPrefix.deleteCharAt(currentPrefix.length() - 1);
            }
        }
    }
}

class TrieNode 
{
    TrieNode[] children;
    boolean flag1; // Represents presence in arr1
    boolean flag2; // Represents presence in arr2

    public TrieNode()
    {
        this.children = new TrieNode[10];
        this.flag1 = false;
        this.flag2 = false;
    }
}