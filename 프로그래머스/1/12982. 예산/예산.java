import java.util.*;
class Solution {
    public int solution(int[] d, int budget) {
        /*
        각 부서가 신청한 금액만큼 정확히 지원해야한다.
        최대한 많은 부서의 물품을 구매할 수 있게 해라
        */
        int answer = 0;
        int sum = 0;
        Arrays.sort(d);
        for(int i : d) {
            if(sum+i > budget) break;
            sum += i;
            answer++;
        }
        return answer;
    }
}