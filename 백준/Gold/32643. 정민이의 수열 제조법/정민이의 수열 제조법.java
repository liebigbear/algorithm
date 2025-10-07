import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;



public class Main {
	public static void main(String[] args) throws Exception {
		/*
		 * 1~N까지 한 개씩 들어있는 수열을 만드는 것을 목표로 다음 작업을 반복한다
		 * 수열에 있는 한 정수를 제곱한 수를 수열에 추가
		 * 수열에 있는 두 정수를 곱한 수를 수열에 추가
		 * 초기 정수 중 a <= 초기 정수 <= b 를 만족하는 개수를 출력해라
		 */
		
		/**
		 * 최소 개수의 양수
		 * 제곱과 두수의 곱
		 * 에라토스테네스의 체
		 * 1,2,3,5,7,11,13
		 * 소수 구하는 문제
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		//int N = Integer.parseInt(br.readLine());
		boolean[] isPrime = new boolean[N+1];
		for(int i = 2; i <= Math.sqrt(N); i++) {
			if(isPrime[i]) continue;
			for(int j = i + i; j <= N; j += i) {
				isPrime[j] = true;
			}
		}
		int[] primeCnt = new int[N+1];
		for(int i = 1; i <= N; i++) {
			int prime = 0;
			if(!isPrime[i]) prime = 1;
			primeCnt[i] = primeCnt[i-1] + prime;
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(primeCnt[b] - primeCnt[a-1]).append('\n');
		}
		System.out.println(sb);
	}
}
