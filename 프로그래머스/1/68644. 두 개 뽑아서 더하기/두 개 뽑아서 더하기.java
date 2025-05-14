import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < numbers.length-1; i++) {
            for(int j = i+1; j < numbers.length; j++) {
                set.add(numbers[i]+numbers[j]);
            }
        }
        ArrayList<Integer> ans = new ArrayList<>(set);
        Collections.sort(ans);
        int[] answer = new int[ans.size()];
        for(int i = 0; i < answer.length; i++) answer[i] = ans.get(i);
        return answer;
    }
}