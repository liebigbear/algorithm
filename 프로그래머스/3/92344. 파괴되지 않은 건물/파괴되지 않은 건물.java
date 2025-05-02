class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        int[][] memo = new int[board.length+2][board[0].length+2];
        
        //stkill 누적합 구하기
        sumSkill(memo, skill);
        
        //skill을 기록한 board
        calcMemo(board, memo);
        
        //파괴되지 않은 건물 구하기
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] > 0) answer++;
            }
        }
        return answer;
    }
    static void sumSkill(int[][] memo, int[][] skill) {
        for(int i = 0; i < skill.length; i++) {
            int sx = skill[i][1];
            int sy = skill[i][2];
            int ex = skill[i][3]+1;
            int ey = skill[i][4]+1;
            if(skill[i][0] == 1) { //attack
                memo[sx][sy] += -skill[i][5];
                memo[sx][ey] += skill[i][5];
                memo[ex][sy] += skill[i][5];
                memo[ex][ey] += -skill[i][5];
            } else { //recover
                memo[sx][sy] += skill[i][5];
                memo[sx][ey] += -skill[i][5];
                memo[ex][sy] += -skill[i][5];
                memo[ex][ey] += skill[i][5];
            }
        }
    }
    static void calcMemo(int[][] board,int[][] memo) {
        for(int i = 0; i < memo.length; i++) {
            for(int j = 1; j < memo[i].length; j++) {
                memo[i][j] += memo[i][j-1];
            }
        }
        for(int i = 0; i < memo[0].length; i++) {
            for(int j = 1; j < memo.length; j++) {
                memo[j][i] += memo[j-1][i];
            }
        }
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                board[i][j] += memo[i][j];
            }
        }
    }
}