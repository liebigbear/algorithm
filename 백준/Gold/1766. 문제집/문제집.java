import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
	static int[][] moving = {{-1,0},{1,0},{0,-1},{0,1}}; 
	public static void main(String[] args) throws Exception {
		/*
		 * 1부터 N개 문제가 있다. 1번 문제가 가장 쉽고 N문제가 가장 어렵다.
		 * 1번 문제를 풀고나면 4번문제가 쉽게 풀리는 '먼저 푸는 것이 좋은 문제가 있다.'
		 * N개 문제는 모두 풀어야한다.
		 * 먼저 푸는 것이 좋은 문제가 있으면 반드시 '먼저 푸는 것이 좋은 문제'를 풀어야한다.
		 * 쉬운문제부터 풀어야한다.
		 */
		
		/**
		 * 큐와 그래프
		 * 1번 탐색 -> 먼저 푸는 것이 좋은 문제 -> ...
		 * 먼저 푸는 문제가 2개인 경우 쉬운문제부터
		 * 
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] solveCnt = new int[N+1];
		
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
		    list.add(new ArrayList<>());
		}

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			solveCnt[b]++;
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i = 1; i <= N; i++) {
			if(solveCnt[i] == 0) pq.add(i);
		}
		while(!pq.isEmpty()) {
			int cur = pq.poll();
			for(int i : list.get(cur)) {
				solveCnt[i]--;
				if(solveCnt[i] == 0) pq.add(i);
			}
			sb.append(cur).append(' ');
		}
		System.out.println(sb);
	}
}
