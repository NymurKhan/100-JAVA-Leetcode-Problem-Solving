class Solution {
    public int countPrefixSuffixPairs(String[] words) {

        int count=0;
        for( int i=0;i<words.length;i++ ) { 

            String s1= words[i];
            for( int j=i+1;j<words.length;j++ ) { 

                String s2= words[j];
                boolean ch =true;
                for( int k=0;k<Math.min(s1.length(),s2.length());k++ ) { 
                    
                    if( s1.length() > s2.length()) { ch=false;break;}
                    if( (s1.charAt(k) != s2.charAt(k)) || (s1.charAt(s1.length()-1-k) != s2.charAt(s2.length()-1-k))) { ch=false;break;}
                }

                if( ch)  count++; 
                    
            }
        }   

        return count;
    }
}