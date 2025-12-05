// User function Template for Java
class Solution {

    public List<Integer> findSplit(int[] arr) {
        // Return an array of possible answer, driver code will judge and return true or
        // false based on
        List<Integer> ans = new ArrayList<>();
        int n=arr.length;
        int sum=0;

        for(int num: arr){
            sum+=num;
        }
        if(sum%3!=0){
            ans.add(-1);
            ans.add(-1);
            return ans;
        }
        int target=sum/3;
        int currSum=0;
        int first=-1,second=-1;
        for (int i = 0; i < n; i++) {
            currSum += arr[i];
            if (currSum == target) {
                first = i;
                break;
            }
        }
        for (int i = first+1; i < n; i++) {
            currSum += arr[i];
            if (currSum == 2*target) {
                second = i;
                break;
            }
        }
        if (first != -1 && second != -1) {
            ans.add(first);
            ans.add(second);
        } else {
            ans.add(-1);
            ans.add(-1);
        }

        return ans;
        
    }
}