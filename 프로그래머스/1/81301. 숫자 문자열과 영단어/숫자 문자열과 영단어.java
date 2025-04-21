import java.util.*;
class Solution {
    public int solution(String s) {
        /*
        일부 자리수를 영단어로 바꾼 카드를 준다
        이 자리수를 정수로 바꾼다.
        */
        /*
        
        */
        int answer = 0;
        HashMap<String, String> map = new HashMap<>();
        map.put("zero", "0");
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        map.put("four", "4");
        map.put("five", "5");
        map.put("six", "6");
        map.put("seven", "7");
        map.put("eight", "8");
        map.put("nine", "9");
        
        Iterator<String> keys = map.keySet().iterator();
        while(keys.hasNext()) {
            String key = keys.next();
            s = s.replaceAll(key, map.get(key));
        }
        answer = Integer.parseInt(s);
        return answer;
    }
}