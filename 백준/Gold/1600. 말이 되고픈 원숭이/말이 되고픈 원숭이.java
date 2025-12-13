import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class xy {
	int x;
	int y;
	int k;
	int move;
	xy(int x, int y, int k, int move) {
		this.x = x;
		this.y = y;
		this.k = k;
		this.move = move;
	}
}

public class Main {
	static int[][] moving = {{-1,0},{1,0},{0,-1},{0,1}}; 
	public static void main(String[] args) throws Exception {
		/*
		 * 원숭이는 K번만큼 나이트 움직임으로 이동할 수 있고 그 외에는 상하좌우로 이동 가능
		 * 맨 왼쪽 0,0부터 시작해서 맨 오른쪽 아래까지 이동해야한다. 최소한의 동작으로 갈수있는 방법을 구해라
		 */
		
		/**
		 * bfs
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		int K = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[H][W];
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < W; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Queue<xy> q = new LinkedList<>();
		q.add(new xy(0,0,K,0));
		boolean[][][] visited = new boolean[H][W][K+1];
		visited[0][0][0] = true;
		
		int ans = 100_000;
		
		while(!q.isEmpty()) {
			xy cur = q.poll();
			if(cur.x == H-1 && cur.y == W-1) {
				ans = Math.min(ans, cur.move);
				continue;
			}
			//나이트 이동
			if(cur.k > 0) {
				for(int i = 0; i < 4; i++) {
					int dx = cur.x + moving[i][0] + moving[i][0];
					int dy = cur.y + moving[i][1] + moving[i][1];
					if(moving[i][0] == 0) {
						for(int j = 0; j < 2; j++) {
							dx = cur.x + moving[j][0];
							if(dx < 0 || dx >= H || dy < 0 || dy >= W || visited[dx][dy][K-cur.k+1]) continue;
							if(arr[dx][dy] == 1) continue;
							q.add(new xy(dx,dy,cur.k-1,cur.move+1));
							visited[dx][dy][K-cur.k+1] = true;
						}
					} else {
						for(int j = 2; j < 4; j++) {
							dy = cur.y + moving[j][1];
							if(dx < 0 || dx >= H || dy < 0 || dy >= W || visited[dx][dy][K-cur.k+1]) continue;
							if(arr[dx][dy] == 1) continue;
							q.add(new xy(dx,dy,cur.k-1, cur.move+1));
							visited[dx][dy][K-cur.k+1] = true;
						}
					}
				}
			}
			for(int i = 0; i < 4; i++) {
				int dx = cur.x + moving[i][0];
				int dy = cur.y + moving[i][1];
				if(dx < 0 || dx >= H || dy < 0 || dy >= W || visited[dx][dy][K-cur.k]) continue;
				if(arr[dx][dy] == 1) continue;
				q.add(new xy(dx,dy,cur.k, cur.move+1));
				visited[dx][dy][K-cur.k] = true;
			}
		}
		
		if(ans == 100_000) ans = -1;
		System.out.println(ans);
	}
}
