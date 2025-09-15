import java.util.*;
class Solution {
    public int solution(String dartResult) {
        /*
        총 3번 던질 수 있고 0~10 점수에 *1 *2 *3 제곱의 점수를 얻을 수 있다
        스타상은 바로 전에 얻은 점수를 각 2배로, 아차상은 해당 점수 마이너스
        스타상, 아차상 끼리는 중첩될 수 있다.
        */
        
        int answer = 0;
        ArrayList<Integer> score = new ArrayList<>();
        for(int i = 0; i < dartResult.length(); i++) {
            char a = dartResult.charAt(i);
            if(a == '#') {
                int prev = score.size()-1;
                score.set(prev, score.get(prev)*-1);
            } else if(a == '*') {
                int prev = score.size()-1;
                int pprev = score.size()-2;
                score.set(prev, score.get(prev)*2);
                if(pprev >= 0) score.set(pprev, score.get(pprev)*2);
            } else {
                if(i+1 == dartResult.length()) continue;
                char bonus = dartResult.charAt(i+1);
                int num = a - '0';
                if(bonus == '0') {
                    bonus = dartResult.charAt(i+2);
                    num = 10;
                    i++;
                }
                if(bonus == 'T') score.add((int)Math.pow(num, 3));
                else if(bonus == 'D') score.add((int)Math.pow(num, 2));
                else score.add(num);
                i++;
            }
        }
        for(int i : score) {
            
            answer += i;
        }
        return answer;
    }
}