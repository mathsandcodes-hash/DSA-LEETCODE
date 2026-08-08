class Solution {
    public String mergeAlternately(String word1, String word2) {
        StringBuilder sb1 = new StringBuilder();
        int n= word1.length();
        int m = word2.length();
        int i=0; int j=0;
        while(i<n && j<m){
            sb1.append(word1.charAt(i));
            sb1.append(word2.charAt(j));
            i++;
            j++;
        }
        if(n==m){
             return sb1.toString();
        }
        else if(n>m){
            for(int k = i; k<n;k++){
                sb1.append(word1.charAt(k));
            }
        }else{
             for(int l=j; l<m;l++){
                sb1.append(word2.charAt(l));
            }
        }
        return sb1.toString();

    }
}