import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;



class Ball {
	int redX;
	int redY;
	int blueX;
	int blueY;
	int cnt;
	
	Ball(int redX, int redY, int blueX, int blueY, int cnt) {
		this.redX = redX;
		this.redY = redY;
		this.blueX = blueX;
		this.blueY = blueY;
		this.cnt = cnt;
	}
}

public class Main {
	static char[][] board;
	static int[][] moving = {{-1,0},{0,-1},{1,0},{0,1}};
	static int N, M;
	public static void main(String[] args) throws Exception {
		/*
		 * 세로 크기 N 가로 크기 M 가장 바깥 행과 열이 막혀있는 보드에 구멍이 하나 있다
		 * 빨간 구슬을 구멍을 통해 빼내는 것이다 파란 구슬이 구멍에 들어가면 안된다.
		 * 상하좌우로 보드를 기울여서 이동 가능 파란 구슬 빨간 구슬이 동시에 빠져서도 안된다. 
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		boolean[][][][] visited = new boolean[N][M][N][M];
		
		Ball startBall = new Ball(0,0,0,0,0);
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < M; j++) {
				board[i][j] = input.charAt(j);
				if(board[i][j] == 'R') {
					startBall.redX = i;
					startBall.redY = j;
					board[i][j] = '.';
				}
				if(board[i][j] == 'B') {
					startBall.blueX = i;
					startBall.blueY = j;
					board[i][j] = '.';
				}
			}
		}
		
		int result = 11;
		
		Queue<Ball> q = new LinkedList<>();
		q.add(startBall);
		while(!q.isEmpty()) {
			Ball b = q.poll();
			if(b.cnt == 10) continue;
			int rx = b.redX;
			int ry = b.redY;
			int bx = b.blueX;
			int by = b.blueY;

			
			
			
			for(int k = 0; k < 4; k++) {
				int newRedX = rx;
				int newRedY = ry;
				int newBlueX = bx;
				int newBlueY = by;
				boolean redHole = false;
				boolean blueHole = false;
				//빨간 구슬 끝까지
				while(true) {
					int dx = newRedX + moving[k][0];
					int dy = newRedY + moving[k][1];
					if(board[dx][dy] == '#') break;
					if(board[dx][dy] == 'O') {
						redHole = true;
						break;
					}
					newRedX = dx;
					newRedY = dy;
				}
				//파란 구슬 끝까지
				while(true) {
					int dx = newBlueX + moving[k][0];
					int dy = newBlueY + moving[k][1];
					if(board[dx][dy] == '#') break;
					if(board[dx][dy] == 'O') {
						blueHole = true;
						break;
					}
					newBlueX = dx;
					newBlueY = dy;
				}
				if(blueHole) continue;
				if(redHole) {
					result = Math.min(b.cnt + 1, result);
					break;
				}
				//같은 선상에 있었다면 기울어진 방향 기준 앞쪽 공을 우선
				// #RB..# 같은 경우 왼쪽은 R 우선 오른쪽은 B 우선
				if(newRedX == newBlueX && newRedY == newBlueY) {
					// 0 상, 2 하
					if(k == 0) {
						if(rx < bx) newBlueX++;
						else newRedX++;
					}
					if(k == 2) {
						if(rx < bx) newRedX--;
						else newBlueX--;
					}
					// 1 좌, 3 우
					if(k == 1) {
						if(ry < by) newBlueY++;
						else newRedY++;
					}
					if(k == 3) {
						if(ry < by) newRedY--;
						else newBlueY--;
					}
				}
				if(newRedX == rx && newRedY == ry && newBlueX == bx && newBlueY == by) continue;
                q.add(new Ball(newRedX,newRedY,newBlueX,newBlueY,b.cnt+1));
				
			}
		}
		if(result == 11) result = -1;
		System.out.println(result);
	}
}
