import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

class Xy {
	int x;
	int y;
	Xy(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int[][] board;
	static int[][] moving = {{-1,0},{0,-1},{1,0},{0,1}};
	static int N, M;
	public static void main(String[] args) throws Exception {
		/*
		 * 사과를 먹으면 뱀의 길이가 늘어난다 벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다
		 * N N 정사각형 보드위에서 진행
		 * 보드의 상하좌우 끝에 벽이 있다
		 * 게임 시작시 뱀은 맨위 맨좌측에 위치하고 뱀의 길이는 1이다
		 * 뱀은 처음에 오른쪽을 향한다
		 * 사과를 먹으면 꼬리는 그대로, 사과르 먹지 못했으면 꼬리가 이동
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		int K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			board[x-1][y-1] = 1;
		}
		int L = Integer.parseInt(br.readLine());
		int[] time = new int[L];
		char[] move = new char[L];
		for(int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int sec = Integer.parseInt(st.nextToken());
			char mo = st.nextToken().charAt(0);
			time[i] = sec;
			move[i] = mo;
		}
		
		//현재 시간
		int answer = 0;
		//방향 인덱스 
		int dirIdx = 3;
		//방향 바뀌는 시간과 정보
		int LIdx = 0;
		
		//뱀의 꼬리 기록
		Queue<Xy> q = new LinkedList<>();
		boolean[][] snake = new boolean[N][N];
		
		snake[0][0] = true;
		q.add(new Xy(0,0));
		int x = 0;
		int y = 0;
		while(true) {
			//뱀의 길이, 현재 위치
			//뱀의 길이 : 뱀의 길이에 따라 boolean[]으로 뱀의 몸을 보드에 표시 큐에다 넣어서 먹지못했다면 poll
			//현재 위치 : L : +1 D : +1
			answer++;
			
			int dx = x + moving[dirIdx][0];
			int dy = y + moving[dirIdx][1];
			if(dx < 0 || dx == N || dy < 0 || dy == N || snake[dx][dy]) break;
			
			//사과 유무
			if(board[dx][dy] == 1) {
				snake[dx][dy] = true;
				q.add(new Xy(dx,dy));
				board[dx][dy] = 0;
			} else {
				Xy tail = q.poll();
				snake[tail.x][tail.y] = false;
				snake[dx][dy] = true;
				q.add(new Xy(dx,dy));
			}
			
			
			//방향 전환
			if(LIdx < L && answer == time[LIdx]) {
				if(move[LIdx] == 'D') dirIdx = (dirIdx + 3) % 4;
				else dirIdx = (dirIdx + 1) % 4;
				LIdx++;
			}
			x = dx;
			y = dy;
		}
		System.out.println(answer);
		
	}
}
