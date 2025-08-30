import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	public static void main(String[] args) throws Exception {
		/*
		 * 주사위를 굴렸을 때 보드의 숫자가 0이면 주사위 바닥면->보드 숫자, 0이 아닌 경우 보드 숫자->주사위 바닥면이 되고 보드 숫자는 0이된다
		 * 주사위 처음 좌표와 이동 명령이 주어졌을 때 주사위 상단의 값을 구해라
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int sx = Integer.parseInt(st.nextToken());
		int sy = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cur = 1;
		
		st = new StringTokenizer(br.readLine());
		//동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4
		int[] convert = {3,4,2,5};
		
		int[][] moving = {{0,1},{0,-1},{-1,0},{1,0}};
		
		//현재 전개도
		int[] dice = new int[7];
		dice[1] = 1; //바닥(기준)
		dice[2] = 2; //위
		dice[3] = 3; //오른쪽
		dice[4] = 4; //왼쪽
		dice[5] = 5; //아래
		dice[6] = 6; //맞은편
		//주사위값
		int[] value = new int[7];
		
		int cx = sx;
		int cy = sy;
		for(int i = 0; i < v; i++) {
			int va = Integer.parseInt(st.nextToken()) - 1;
			
			int dx = cx + moving[va][0];
			int dy = cy + moving[va][1];
			if(dx < 0 || dx == N || dy < 0 || dy == M) continue;
			
			//전개도 수정
			dice = changeDice(dice, va);
			if(board[dx][dy] == 0) {
				board[dx][dy] = value[dice[1]];
			} else {
				value[dice[1]] = board[dx][dy];
				board[dx][dy] = 0;
			}
			System.out.println(value[dice[6]]);
			
			cx = dx;
			cy = dy;
		}
	}
	static int[] changeDice(int[] prevDice, int va) {
		int[] dice = new int[7];
		if(va == 0) { //동
			dice[1] = prevDice[3]; //바닥(기준)
			dice[2] = prevDice[2]; //위
			dice[3] = prevDice[6]; //오른쪽
			dice[4] = prevDice[1]; //왼쪽
			dice[5] = prevDice[5]; //아래
			dice[6] = prevDice[4]; //맞은편
		} else if(va == 1) { //서
			dice[1] = prevDice[4]; //바닥(기준)
			dice[2] = prevDice[2]; //위
			dice[3] = prevDice[1]; //오른쪽
			dice[4] = prevDice[6]; //왼쪽
			dice[5] = prevDice[5]; //아래
			dice[6] = prevDice[3]; //맞은편
		} else if(va == 3) { //남
			dice[1] = prevDice[5]; //바닥(기준)
			dice[2] = prevDice[1]; //위
			dice[3] = prevDice[3]; //오른쪽
			dice[4] = prevDice[4]; //왼쪽
			dice[5] = prevDice[6]; //아래
			dice[6] = prevDice[2]; //맞은편
		} else { //북
			dice[1] = prevDice[2]; //바닥(기준)
			dice[2] = prevDice[6]; //위
			dice[3] = prevDice[3]; //오른쪽
			dice[4] = prevDice[4]; //왼쪽
			dice[5] = prevDice[1]; //아래
			dice[6] = prevDice[5]; //맞은편
		}
		return dice;
	}
}
