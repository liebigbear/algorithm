import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;


class CurrentBoard {
	int[][] board;
	int depth;
	CurrentBoard(int[][] board, int depth) {
		this.board = board;
		this.depth = depth;
	}
}

public class Main {
	static int[][] board;
	static int[][] moving = {{-1,0},{0,-1},{1,0},{0,1}};
	static int N, M;
	public static void main(String[] args) throws Exception {
		/*
		 * 보드 크기 N이 주어질 때 최대 5번 기울여서 얻을 수 있는 가장 큰 블록을 구해라
		 * 기울인다 = 한 방향으로 끝까지 이동
		 * 한 번의 이동으로 합쳐진 블록은 또 합쳐질 수 없다.
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		int result = 0;
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				result = Math.max(result, board[i][j]);
			}
		}
		Queue<CurrentBoard> q = new LinkedList<>();
		q.add(new CurrentBoard(board, 0));

		while(!q.isEmpty()) {
			CurrentBoard cur = q.poll();
			if(cur.depth == 5) {
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						result = Math.max(cur.board[i][j], result);
					}
				}
				continue;
			}
			for(int k = 0; k < 4; k++) {
				int[][] newBoard = moveBoard(cur.board, k);
				if(isArrEq(cur.board, newBoard)) continue;	
				
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						result = Math.max(newBoard[i][j], result);
					}
				}
				q.add(new CurrentBoard(newBoard, cur.depth+1));
			}
		}
		System.out.println(result);
	}
	static int[][] moveBoard(int[][] curBoard, int dir) {
		int[][] copy = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				copy[i][j] = curBoard[i][j];
			}
		}
		
		for(int i = 0; i < N; i++) {
			copy = sumBoard(copy, dir, i);
		}
		return copy;
	}
	static int[][] sumBoard(int[][] copy, int dir, int fixIdx) {
		boolean LR = false;
		int startIdx = 0;
		//0-하 1-우 2-상 3-좌
		if(dir == 1 || dir == 3) LR = true;
		if(dir == 0 || dir == 1) startIdx = N-1;
		int curX;
		int curY;
		if(LR) {
			//board[][fixIdx]
			curX = fixIdx;
			curY = startIdx;
		} else {
			curX = startIdx;
			curY = fixIdx;
		}
		while(curX >= 0 && curY >= 0 && curX < N && curY < N) {
			if(copy[curX][curY] != 0) {
				int dx = curX + moving[dir][0];
				int dy = curY + moving[dir][1];
				while(true) {
					if(dx < 0 || dx == N || dy < 0 || dy == N) break;
					if(copy[dx][dy] != 0 && copy[dx][dy] != copy[curX][curY]) break;
					if(copy[dx][dy] == copy[curX][curY]) {
						copy[curX][curY] *= 2;
						copy[dx][dy] = 0;
						break;
					}
					dx = dx + moving[dir][0];
					dy = dy + moving[dir][1];
				}
			}
			curX += moving[dir][0];
			curY += moving[dir][1];
		}
		
		if(LR) {
			curX = fixIdx;
			curY = startIdx;
		} else {
			curX = startIdx;
			curY = fixIdx;
		}
		while(curX >= 0 && curY >= 0 && curX < N && curY < N) {
			if(copy[curX][curY] == 0) {
				int dx = curX + moving[dir][0];
				int dy = curY + moving[dir][1];
				while(true) {
					if(dx < 0 || dx == N || dy < 0 || dy == N) break;
					if(copy[dx][dy] != 0) {
						copy[curX][curY] = copy[dx][dy];
						copy[dx][dy] = 0;
						break;
					}
					dx = dx + moving[dir][0];
					dy = dy + moving[dir][1];
				}
			}
			curX += moving[dir][0];
			curY += moving[dir][1];
		}
		return copy;
	}
	static boolean isArrEq(int[][] origin, int[][] copy) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(origin[i][j] != copy[i][j]) return false;
			}
		}
		return true;
	}
}
