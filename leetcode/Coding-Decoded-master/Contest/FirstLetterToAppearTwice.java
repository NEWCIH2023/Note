

// https://leetcode.com/problems/first-letter-to-appear-twice/
class Solution {
    public char repeatedCharacter(String s) {
        int[] freq = new int[26];
        for(char c: s.toCharArray()){

            freq[c-'a']++;
            if(freq[c-'a']==2) {
                return c;
            }
        }
        return 'a';
    }
}

// @author: anuj0503
class Solution {
    public char repeatedCharacter(String s) {
        // we could also use set instead of map.
        Map<Character, Integer> map = new HashMap<>();
        
        for(int i = 0; i < s.length(); i++){
            if(map.get(s.charAt(i)) != null){
                return s.charAt(i);
            }
            
            map.put(s.charAt(i), 1);
        }
        
        
        return 'a';
    }
}
