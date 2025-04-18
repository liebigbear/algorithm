class Solution {
    public int solution(int n) {
        int answer = isPrime(n-1);
        return answer;
    }
    static int isPrime(int n) {
        for(int i = 2; i <= n/2; i++) {
            if(n%i == 0) {
                return isPrime(i);
            }
        }
        return n;
    }
}