import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Node {
	int x;
	int y;
	Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int[][] moving = {{-1,0}, {1,0},{0,-1},{0,1}};
	static StringBuilder sb = new StringBuilder();
	static int R,C;
	static int[][] board;
	static boolean[] visited;
	static int MOD = 1000000007;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//N = Integer.parseInt(br.readLine());
		//int T = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] board = new int[N+1][N+1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken()) + board[i-1][j] + board[i][j-1] - board[i-1][j-1];
			}
		}
		
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int sum = board[c][d] - (board[a-1][d] + board[c][b-1] - board[a-1][b-1]);
			sb.append(sum).append('\n');
		}
		System.out.println(sb);
		
	}
}