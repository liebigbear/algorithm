import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static int[][] board;
	static int[][] moving = {{-1,0},{0,-1},{1,0},{0,1}};
	static int N, M;
	public static void main(String[] args) throws Exception {
		/*
		 * N개 시험장이 있고 i번 시험장에 Ai 명이 있다
		 * 총감독관은 B명까지 감시, 감독관은 C명까지 감시
		 * 총감독관은 1명만 있어야하고 부감독관은 여러명
		 * 
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		long ans = 0;
		N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ");
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		ans = N;
		for(int i = 0; i < N; i++) {
			arr[i] -= B;
		}
		for(int i = 0; i < N; i++) {
			if(arr[i] > 0) {
				if(arr[i] % C == 0) ans += arr[i] / C;
				else ans += arr[i] / C + 1;
			}
		}
		System.out.println(ans);
	}
}
