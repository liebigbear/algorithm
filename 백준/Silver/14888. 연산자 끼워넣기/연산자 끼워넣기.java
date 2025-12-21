import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class so {
	int idx;
	int plus;
	int sub;
	int mul;
	int div;
	int sum;
	so(int idx, int plus, int sub, int mul, int div, int sum) {
		this.idx = idx;
		this.plus = plus;
		this.sub = sub;
		this.mul = mul;
		this.div = div;
		this.sum = sum;
	}
}

public class Main {
	static int[][] moving = {{-1,0},{1,0},{0,-1},{0,1}}; 
	public static void main(String[] args) throws Exception {
		/*
		 * N개 수로 이루어진 수열과 N-1의 연산자가 주어진다. 수열은 변경하지 못한다.
		 * 만들 수 있는 식의 결과가 최대인 것과 최소인 것을 구하여라.
		 * 연산자 우선순위는 무시하고 앞에서 부터 계산한다. 나눗셈은 정수 나눗셈의 몫만 취한다
		 */
		
		/**
		 * N이 최대 11개 완전탐색
		 * 식의 계산은 연산자 우
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int[] operator = new int[4];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < 4; i++) {
			
			operator[i] = Integer.parseInt(st.nextToken());
		}
		
		Queue<so> q = new LinkedList<>();
		q.add(new so(1, operator[0], operator[1], operator[2], operator[3], arr[0]));
		
		int max = Integer.MAX_VALUE * -1;
		int min = Integer.MAX_VALUE;
		
		while(!q.isEmpty()) {
			so cur = q.poll();
			if(cur.idx == N) {
				max = Math.max(max, cur.sum);
				min = Math.min(min, cur.sum);
				continue;
			}
			if(cur.plus > 0) {
				q.add(new so(cur.idx+1, cur.plus-1, cur.sub, cur.mul, cur.div, cur.sum + arr[cur.idx]));
			}
			if(cur.sub > 0) {
				q.add(new so(cur.idx+1, cur.plus, cur.sub-1, cur.mul, cur.div, cur.sum - arr[cur.idx]));
			}
			if(cur.mul > 0) {
				q.add(new so(cur.idx+1, cur.plus, cur.sub, cur.mul-1, cur.div, cur.sum * arr[cur.idx]));
			}
			if(cur.div > 0) {
				q.add(new so(cur.idx+1, cur.plus, cur.sub, cur.mul, cur.div-1, cur.sum / arr[cur.idx]));
			}
		}
		System.out.println(max);
		System.out.println(min);
	}
}
