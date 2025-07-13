import java.util.*;
class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        for(int i = 1; i <= number; i++) {
            int cnt = 0;
            //약수 짝 1, 12 2 6
            for(int j = 1; j <= Math.sqrt(i); j++) {
                if(i % j == 0) cnt += 2;
                //약수 개수 홀수개
                if(j * j == i) cnt -= 1;
            }
            if(cnt > limit) answer += power;
            else answer += cnt;
        }
        return answer;
    }
}