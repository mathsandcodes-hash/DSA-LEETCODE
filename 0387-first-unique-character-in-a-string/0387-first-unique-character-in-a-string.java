import java.util.*;
class Solution {
    public int firstUniqChar(String s) {
        HashMap<Character , Integer> map =new HashMap<>();
        char ch[] = s.toCharArray();
        for(int i = 0 ; i<ch.length;i++){
            map.put(ch[i],map.getOrDefault(ch[i],0)+1);
        }
        for(int i = 0 ; i<ch.length;i++){
            if(map.get(ch[i])==1){
                return i;
            }
        }
        return -1;

    }
}