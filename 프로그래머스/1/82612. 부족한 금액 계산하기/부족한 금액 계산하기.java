class Solution {
    public long solution(int price, int money, int count) {
        long answer = 0;
        for(int i = 1; i <= count; i++) answer += i;
        answer = (answer*price) - money;
        if(answer < 0) answer = 0;
        return answer;
    }
}