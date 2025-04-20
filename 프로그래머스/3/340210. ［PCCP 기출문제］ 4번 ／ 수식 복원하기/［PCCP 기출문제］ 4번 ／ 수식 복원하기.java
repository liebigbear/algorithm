import java.util.*;
class Solution {
    public String[] solution(String[] expressions) {
        /*
        진법 변환
        브루트포스
        */
        String[] answer = solve(expressions);
        return answer;
    }
    
    static String[] solve(String[] exp) {
        int[][] cp = changeExp(exp);
        //최소 진법값 탐색
        int minN = posNum(cp);
        int[] posN = expNum(minN, cp);
        //진법값 X식에 대입
        posResult(cp , posN);
        //문자열로 반환
        return rchangeExp(cp);
    }
    
    
    //2~9진법 탐색
    static int posNum(int[][] result) {
        int answer = -1;
        for(int i = 0; i < result.length; i++) {
            for(int j = 0; j < 5; j += 2) {
                if(result[i][j] == -1) continue;
                answer = Math.max(answer, maxNum(result[i][j]));
            }
        }
        return answer+1;
    }
    //최소 진법값 탐색
    static int maxNum(int N) {
        int answer = -1;
        while(N > 0) {
            answer = Math.max((N%10),answer);
            N /= 10;
        }
        return answer;
    } 
    //문자열 -> 배열로 변환
    static int[][] changeExp(String[] exp) {
        //+ = 0, - = 1 로 변환
        //X는 -1
        int[][] result = new int[exp.length][5];
        int idx = 0;
        for(String str : exp) {
            String[] tmp = str.split(" ");
            if(tmp[4].equals("X")) {
                tmp[4] = "-1";
                result[idx][3] = -1;
            }
            for(int i = 0; i < 5; i += 2) {
                result[idx][i] = Integer.parseInt(tmp[i]);
            }
            if(tmp[1].equals("+"))
                result[idx][1] = 0;
            else
                result[idx][1] = 1;
            idx++;
        }
        return result;
    }
    //배열 -> 문자열 변환
    static String[] rchangeExp(int[][] result) {
        String[] answer = {""};
        ArrayList<String> ans = new ArrayList<>();
        
        for(int i[] : result) {
            if(i[3] == -1) {
                StringBuilder sb = new StringBuilder();
                char a;
                if(i[1] == 0) a = '+';
                else a = '-';
                sb.append(String.valueOf(i[0]))
                    .append(" "+a+" ")
                    .append(String.valueOf(i[2]))
                    .append(" = ");
                if(i[4] == -2) sb.append("?");
                else sb.append(String.valueOf(i[4]));
                ans.add(sb.toString());
            }
        }
        return ans.toArray(new String[ans.size()]);
    }
    
    //배열에 있는 수식이 현재 진법으로 가능한지 여부
    static boolean posExpNum(int[][] result, int num) {
        for(int[] i : result) {
            if(i[4] == -1) {
                continue;
            }
            int a = changeNum(i[0], num);
            int b = changeNum(i[2], num);
            int c = changeNum(i[4], num);
            if(i[1] == 0) {
                if(a+b != c) {
                    return false;
                }
            } else {
                if(a-b != c) {
                    return false;
                }
            }
        }
        return true;
    }
    //십진수 진법으로 변환
    static int changeNum(int num, int ch) {
        int answer = 0;
        int idx = 0;
        while(num > 0) {
            answer += (num % 10) * Math.pow(ch, idx);
            num /= 10;
            idx++;
        }
        return answer;
    }
    //특정 진법으로 변환
    static int rchangeNum(int num, int ch) {
        //입력값으로 세자리수 이하의 정수
        int idx = 2;
        int answer = 0;
        while(num > 0) {
            int tmp =  (int)Math.pow(ch, idx);
            int ans = num / tmp;
            answer += ans * Math.pow(10, idx);
            num %= tmp;
            idx--;
        }
        return answer;
    }
    //가능한 진법 값 배열로 전달
    static int[] expNum(int start, int[][] result) {
        int[] answer = new int[9];
        int idx = 0;
        for(int i = start; i <= 9; i++) {
            if(posExpNum(result, i))
                answer[idx++] = i;
        }
        return answer;
    }
    //가능한 진법이 2개 이상인지 여부
    static int[][] posResult(int[][] result, int[] posN) {
        for(int i = 0; i < posN.length; i++) {
            if(posN[i] == 0) break;
            for(int j = 0; j < result.length; j++) {
                if(result[j][4] == -1)
                    result[j][4] = calResult(result, j, posN[i]);
                else if(result[j][4] != -2) {
                    int tmp = calResult(result, j, posN[i]);
                    //답이 여러개
                    if(result[j][4] != tmp) {
                        result[j][4] = -2;
                    }
                }
            }
        }
        return result;
    }
    static int calResult(int[][] result ,int idx, int num) {
        int a = changeNum(result[idx][0], num);
        int b = changeNum(result[idx][2], num);
        if(result[idx][1] == 0)
            return rchangeNum(a+b, num);
        else
            return rchangeNum(a-b, num);
    }
}