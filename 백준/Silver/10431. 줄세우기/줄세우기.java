import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;



public class Main {
	public static void main(String[] args) throws Exception {
		/*
		 * 키 순서대로 줄을 세운다
		 * 자기 앞에 자신보다 키 큰 사람이 있다면 줄 가장 앞에 있는 사람 앞에 선다 이 때 공간마련을 위해 한명씩 뒤로 물러선다
		 * 줄서기가 끝났을 때 학생들이 총 몇번 뒤로 물러서는지 구해라
		 */
		
		/**
		 * 앞쪽부터 탐색하며 이전사람(A)보다 자신(B)이 작다면 자리변경
		 * B의 줄 순번 - A의 줄 순번 만큼 더한다
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][20];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int T = Integer.parseInt(st.nextToken());
			for(int j = 0; j < 20; j++) {
				arr[T-1][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++) {
			sb.append((i+1)+" "+sort(arr[i])).append('\n');
			
		}
		System.out.println(sb);
	}
	static int sort(int[] arr) {
		int ans = 0;
		for(int j = 1; j < 20; j++) {
			//자기보다 키가 큰 사람 중 가장 앞에 있는 사람
			for(int k = 0; k < j; k++) {
				if(arr[k] > arr[j]) {
					int v = arr[k];
					arr[k] = arr[j];
					for(int s = k+1; s <= j; s++) {
						int temp = arr[s];
						arr[s] = v;
						v = temp;
						ans++;
					}
				}
			}
		}
		return ans;
	}
}
