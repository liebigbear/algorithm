import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Node {
	int to;
	int weight;
	Node(int to, int weight) {
		this.to = to;
		this.weight = weight;
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
		/*
		 * N개 노드 N-1 간선 K가중치가 주어진다
		 * k가 주어질 때 k이상인 노드는 몇 개인지 구하여라
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		ArrayList<Node>[] graph = new ArrayList[N+1];
		for(int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[s].add(new Node(e,v));
			graph[e].add(new Node(s,v));
		}
		
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int ans = solve(graph, k, v);
			System.out.println(ans);
		}
	}
	static int solve(ArrayList<Node>[] graph, int k, int v) {
		boolean[] visited = new boolean[graph.length];
		visited[v] = true;
		LinkedList<Integer> q = new LinkedList<>();
		q.add(v);
		while(!q.isEmpty()) {
			int s = q.poll();
			for(Node n : graph[s]) {
				if(visited[n.to] || n.weight < k) continue;
				q.add(n.to);
				visited[n.to] = true;
			}
		}
		int ans = 0;
		for(int i = 0; i < visited.length; i++) {
			if(visited[i]) ans++;
		}
		return ans-1;
	}
}
