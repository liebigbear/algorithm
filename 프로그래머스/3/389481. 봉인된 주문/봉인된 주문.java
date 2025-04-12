import java.util.*;
class Solution {
    public String solution(long n, String[] bans) {
        /*
        글자 수가 적은 주문부터 먼저 정렬되고 같다면 사전 순서대로 정렬되어 있는 주문이 연달아 있다
        bans만큼의 주문을 지웠을 때 주문서에서 n번째 주문을 찾아라 
        */
        /*
        a,b,c,d,e,f,g...aa,ab,ac
        한글자 26
        두글자 26 * 26
        */
        //총 주문에서 주문을 제거한 수
        long subSP = 0;
        Long[] banN = new Long[bans.length];
        int idx = 0;
        //각 bans을 n번째 수로 바꾼다.
        for(String s : bans) {
            long cal = calN(s);
            banN[idx++] = cal;
        }
        Arrays.sort(banN);
        //제거한 숫자만큼 자리수를 뒤로 민다.
        for(long tmp : banN) {
            if(tmp <= n) n++;
        }
        String answer = createSpell(n);
        return answer;
    }
    static long calN(String ban) {
        long result = 0;
        long jarisu = 1;
        for(int i = ban.length()-1; i >= 0; i--) {
            int alphabet = ban.charAt(i)-'a' + 1;
            result += alphabet * jarisu;
            jarisu *= 26;
        }
        return result;
    }
    static String createSpell(long n) {
        StringBuilder sb = new StringBuilder();
        while(n > 0) {
            long a = (n-1) % 26;
            sb.append((char)(a+'a'));
            n = (n-1) / 26;
        }
        return sb.reverse().toString();
    }
}