import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;


class CurrentBoard {
	int[][] board;
	int depth;
	CurrentBoard(int[][] board, int depth) {
		this.board = board;
		this.depth = depth;
	}
}

public class Main {
	static int[][] board;
	static int[][] moving = {{-1,0},{0,-1},{1,0},{0,1}};
	static int N, M;
	public static void main(String[] args) throws Exception {
		/*
		 * 보드 크기 N이 주어질 때 최대 5번 기울여서 얻을 수 있는 가장 큰 블록을 구해라
		 * 기울인다 = 한 방향으로 끝까지 이동
		 * 한 번의 이동으로 합쳐진 블록은 또 합쳐질 수 없다.
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = new int[3];
		//N = Integer.parseInt(br.readLine());
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int maxIdx = -1;
			int maxV = -1;
			for(int i = 0; i < 3; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				if(maxV < arr[i]) {
					maxV = arr[i];
					maxIdx = i;
				}
			}
			if(arr[0] == 0 || arr[1] == 0 || arr[2] == 0) break;
			int cnt = 0;
			for(int i = 0; i < 2; i++) {
				for(int j = i+1; j < 3; j++) {
					if(arr[i] == arr[j]) cnt++;
				}
			}
			if(arr[maxIdx] >= arr[(maxIdx+1)%3] + arr[(maxIdx+2)%3]) System.out.println("Invalid");
			else {
				if(cnt == 3) System.out.println("Equilateral");
				if(cnt == 1) System.out.println("Isosceles");
				if(cnt == 0) System.out.println("Scalene");
			}	
		}
		
		
		
	}
}
