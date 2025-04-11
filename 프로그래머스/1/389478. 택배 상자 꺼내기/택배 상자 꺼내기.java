class Solution {
    public int solution(int n, int w, int num) {
        int answer = 1;
        int startNum = (num-1) % w;
        answer += (n-num) / (2*w) * 2;
        int remainBox = (n-num) % (2*w);
        int nextBox = (w-startNum-1) * 2 + 1;
        if(remainBox >= nextBox) answer += 1;
        return answer;
    }
}