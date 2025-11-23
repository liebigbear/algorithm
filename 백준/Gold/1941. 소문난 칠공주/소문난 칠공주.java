import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;



public class Main {
	static int[][] moving = {{-1,0},{1,0},{0,-1},{0,1}}; 
	public static void main(String[] args) throws Exception {
		/*
		 * 5 * 5 보드가 주어지고 보드에 S, Y값이 주어진다
		 * 조건에 따라 S,Y가 7개가 되도록 묶을 수 있는 경우의 수
		 * Y는 최대 3개, 묶을 때는 S,Y가 가로,세로에 인접해야한다
		 */
		
		/**
		 * 테트로미노 문제와 비슷하나 도형의 크기가 7이나 되기 때문에 테트로미노 처럼 모든 모양에 대해 처리하는 것은 힘들다
		 * 25개 좌표중 7개를 선택한 뒤 7개의 도형이 서로 이어져 있는지 확인하는 식으로 문제풀이
		 * 시간을 줄이기 위해 조건 추가
		 * - S가 4개 이상일 때만 BFS 진행
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		char[][] board = new char[5][5];
		boolean[][] visited = new boolean[5][5];
		for(int i = 0; i < 5; i++) {
			String input = br.readLine();
			for(int j = 0; j < 5; j++) {
				board[i][j] = input.charAt(j);
			}
		}
		System.out.println(search(board ,visited, 0, 0, 0));
	}
	static int search(char[][] board, boolean[][] visited, int depth, int Ycnt, int xy) {
		if(Ycnt == 4) return 0;
		if(depth == 7) {
			return BFS(visited, xy-1);
		}
		int ans = 0;
		//[i/5][i%5] 로 for문 하나로 진행
		for(int i = xy; i < 25; i++) {
			int curX = 0;
			int curY = 0;
			if(i > 0) {
				curX = i/5;
				curY = i%5;
			}
			if(board[curX][curY] == 'S') {
				visited[curX][curY] = true;
				ans += search(board, visited, depth+1, Ycnt, i+1);
				visited[curX][curY] = false;
			} else {
				visited[curX][curY] = true;
				ans += search(board, visited, depth+1, Ycnt+1, i+1);
				visited[curX][curY] = false;
			}
		}
		return ans;
	}
	static int BFS(boolean[][] visited, int xy) {
		int startX = xy/5;
		int startY = xy%5;
		boolean[][] bfsVisited = new boolean[25][25];
		LinkedList<Integer> q = new LinkedList<>();
		q.add(startX);
		q.add(startY);
		bfsVisited[startX][startY] = true;
		int cnt = 0;
		while(!q.isEmpty()) {
			int curX = q.poll();
			int curY = q.poll();
			cnt++;
			for(int i = 0; i < 4; i++) {
				int dx = curX + moving[i][0];
				int dy = curY + moving[i][1];
				if(dx < 0 || dx == 5 || dy < 0 || dy == 5 || !visited[dx][dy] || bfsVisited[dx][dy]) continue;
				q.add(dx);
				q.add(dy);
				bfsVisited[dx][dy] = true;
			}
		}
		if(cnt == 7) return 1;
		return 0;
	}
}
