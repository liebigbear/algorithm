import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Lotus {
	int x;
	int y;
	double r;
	Lotus(int x, int y, double r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}
}

public class Main {
	static int[][] moving = {{-1,0},{1,0},{0,-1},{0,1}}; 
	public static void main(String[] args) throws Exception {
		/*
		 * 0,0좌표가 중심인 반지름R의 연못에 N개의 연꽃잎과 M마리의 금오리가 연못에 있다.
		 * 상호는 연못밖과 연꽃위에서 이동가능하다. 상호의 처음위치는 연못밖이다.
		 * 상호는 길이 L의 뜰채를 이용하여 거리가 L이하인 금오리를 포획할 수 있다. 최대 몇마리의 금오리를 포획가능한가
		 */
		
		/**
		 * 이동할수 있는 연꽃을 구한다.
		 * 연못과 
		 * 연못밖에서 포획가능한 금오리를 구한다
		 * 오리중심과 연못중심의 거리 + L >= R 금오리 포획가능
		 * 연못에서 포획가능한 금오리를 구한다
		 * 연꽃중심과 오리중심의 거리 + L >= 연꽃반지름 금오리 포획가능
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		long R = Long.parseLong(st.nextToken());
		long L = Long.parseLong(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Lotus[] lotus = new Lotus[N];
		//연꽃 위치 저장
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int xi = Integer.parseInt(st.nextToken());
			int yi = Integer.parseInt(st.nextToken());
			int ri = Integer.parseInt(st.nextToken());
			lotus[i] = new Lotus(xi, yi, ri);
		}
		//이동가능한 연꽃
		boolean[] isPosLutus = new boolean[N];
		Queue<Integer> q = new LinkedList<>();
		//연못밖에서 이동가능한 연꽃먼저 구한다.
		for(int i = 0; i < N; i++) {
			Lotus lus = lotus[i];
			long d = getDistance((long)lotus[i].x, 0L, (long)lotus[i].y, 0L);
			long diff = (long) (R - lotus[i].r);
			//연못밖에서 이동가능
			if(diff <= 0) {
				isPosLutus[i] = true;
				q.add(i);
			} else {
				long diff2 = diff * diff;
				if(d >= diff2) {
					isPosLutus[i] = true;
					q.add(i);
				}
			}
		}
		
		while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 0; i < N; i++) {
                if (isPosLutus[i]) continue;

                long rsum = (long) (lotus[cur].r + lotus[i].r);
                if (getDistance(lotus[cur].x, lotus[i].x, lotus[cur].y, lotus[i].y)
                        <= rsum * rsum) {
                	isPosLutus[i] = true;
                    q.add(i);
                }
            }
        }
		
		long ans = 0;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int duckX = Integer.parseInt(st.nextToken());
			int duckY = Integer.parseInt(st.nextToken());
			
			//연못밖에서 포획
			long len = R - L;
			long getOut = getDistance((long)duckX, 0, (long)duckY, 0);
			if(len <= 0) {
				ans++;
			} else {
				long len2 = len * len;
				if(getOut >= len2) {
					ans++;
				}
				else {
					//연꽃에서 포획
					for(int j = 0; j < N; j++) {
						if(!isPosLutus[j]) continue;
						long getLotus = getDistance((long)duckX, (long)lotus[j].x, (long)duckY, (long)lotus[j].y);
						if(getLotus <= (L + lotus[j].r) * (L + lotus[j].r)) {
							ans++;
							break;
						}
					}
				}
			}	
		}
		System.out.println(ans);
	}
	//두점 사이의 거리를 구한다
	static long getDistance(long x1, long x2, long y1, long y2) {
		long dx = x1 - x2;
		long dy = y1 - y2;
		return dx*dx + dy*dy;
	}
}
