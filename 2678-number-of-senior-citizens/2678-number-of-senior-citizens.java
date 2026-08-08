class Solution {
    public int countSeniors(String[] details) {
        int n= details.length;
        int c=0;
        for( int i =0 ;i<n;i++){
            char c1 = details[i].charAt(11);
            char c2 = details[i].charAt(12);
            String age = "" + c1 + c2;

            if(Integer.parseInt(age)>60){
                c++;
            }
        }
        return c;
    }
}