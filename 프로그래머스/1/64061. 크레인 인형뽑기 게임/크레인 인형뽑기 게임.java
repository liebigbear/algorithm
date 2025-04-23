import java.util.*;
class Solution {
    public int solution(int[][] board, int[] moves) {
        /*
        N N 크기 정사각 격자 크레인이 있다
        바구니에 같은 인형이 쌓이면 터트려진다.
        크레인을 모두 작동시킨 후 터트려져 사라진 인형의 개수를 구해라
        */
        /*
        00000
        00103
        02501
        42442
        35131 4311324
        */
        int answer = 0;
        Queue[] q = new LinkedList[board.length];
        for(int i = 0; i < board.length; i++) {
            q[i] = new LinkedList<Integer>();
        }
        input(q, board);
        answer = solve(q, moves);
        return answer;
    }
    static void input(Queue[] q, int[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == 0) continue;
                q[j].add(board[i][j]);
            }
        }
    }
    static int solve(Queue[] q, int[] moves) {
        int answer = 0;
        Stack<Integer> dolls = new Stack<>();
        for(int i : moves) {
            if(q[i-1].isEmpty()) continue;
            dolls.push((int)q[i-1].poll());
        }
        Stack<Integer> right = new Stack<>();
        while(dolls.size() > 1) {
            right.add(dolls.pop());
            while(right.peek() == dolls.peek()) {
                answer += 2;
                right.pop();
                dolls.pop();
                if(right.isEmpty() || dolls.isEmpty()) break;
            }
        }
        return answer;
    }
}