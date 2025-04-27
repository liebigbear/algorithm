import java.util.*;
class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        /*

        */
        int[] position = new int[12];
        position[1] = 1;
        position[2] = 2;
        position[3] = 1;
        position[4] = 2;
        position[5] = 3;
        position[6] = 2;
        position[7] = 3;
        position[8] = 4;
        position[9] = 3;
        position[10] = 4;
        position[11] = 5;
        
        int curLeft = 10;
        int curRight = 12;
        for(int i : numbers) {
            if(i == 0) i = 11;
            if(i == 1 || i == 4 || i == 7) {
                answer += 'L';
                curLeft = i;
            } else if(i == 3 || i == 6 || i == 9) {
                answer += 'R';
                curRight = i;
            } else {
                char a = midFunc(i, curLeft, curRight, position, hand);
                if(a == 'L') curLeft = i;
                else curRight = i;
                answer += a;
            }
        }
        return answer;
    }
    static char midFunc(int i, int L, int R, int[] position, String hand) {
        int Ldis = position[(int)Math.abs(L-i)];
        int Rdis = position[(int)Math.abs(R-i)];
        if(Ldis < Rdis) {
            return 'L';
        } else if(Ldis > Rdis) {
            return 'R';
        } else {
            if(hand.equals("left")) return 'L';
            else return 'R';
        }
    }
}