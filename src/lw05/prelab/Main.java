import java.util.*;

public class Main {
    int size;
    String[] names;
    int[][] graph;
    int startNode;
    int destNode;

    public void addEdge(int u, int v, int weight) {
        graph[u][v] = weight;
        graph[v][u] = weight;
    }

    public void printPath(List<Integer> order) {
        for (int i = 0; i < order.size(); i++) {
            System.out.print(names[order.get(i)]);
            if (i < order.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    public List<Integer> bfs(int start) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new LinkedHashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            order.add(current);

            for (int i = 0; i < size; i++) {
                if (graph[current][i] > 0 && !visited.contains(i)) {
                    visited.add(i);
                    queue.add(i);
                }
            }
        }
        return order;
    }

    List<Integer> dfsorder = new ArrayList<>();
    Set<Integer> dfsVisited = new HashSet<>();

    public void dfs(int current) {
        dfsVisited.add(current);
        dfsorder.add(current);

        for (int i = 0; i < size; i++) {
            if (graph[current][i] > 0 && !dfsVisited.contains(i)) {
                dfs(i);
            }
        }
    }

    public int findTheNextNode(int[] dist, boolean[] visited) {
        int minDist = Integer.MAX_VALUE;
        int minNode = -1;

        for (int i = 0; i < size; i++) {
            if (!visited[i] && dist[i] < minDist) {
                minDist = dist[i];
                minNode = i;
            }
        }
        return minNode;
    }

    public void printRoute(int[] prev, int dest) {
        List<Integer> path = new ArrayList<>();
        int curr = dest;
        while (curr != -1) {
            path.add(curr);
            curr = prev[curr];
        }
        Collections.reverse(path);
        for (int i = 0; i < path.size(); i++) {
            System.out.print(names[path.get(i)]);
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    public void dijkstraAlg(int start, int dest) {
        int[] dist = new int[size];
        boolean[] visited = new boolean[size];
        int[] prev = new int[size];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[start] = 0;

        for (int i = 0; i < size - 1; i++) {
            int u = findTheNextNode(dist, visited);
            if (u == -1) break;
            visited[u] = true;

            for (int v = 0; v < size; v++) {
                if (!visited[v] && graph[u][v] > 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    prev[v] = u;
                }
            }
        }

        System.out.println("=== Shortest Time from " + names[start] + " ===");
        for (int i = 0; i < size; i++) {
            System.out.println(names[i] + " : " + dist[i] + " min");
        }

        System.out.println("=== Shortest Route from " + names[start] + " to " + names[dest] + " ===");
        printRoute(prev, dest);
        System.out.println("Total time: " + dist[dest] + " min");
    }

    public static void main(String[] args) {
        Main app = new Main();
        
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("campus.txt"));

        app.size = scanner.nextInt();
        app.names = new String[app.size];
        for (int i = 0; i < app.size; i++) {
            app.names[i] = scanner.next();
        }

        app.graph = new int[app.size][app.size];
        int e = scanner.nextInt();
        for (int i = 0; i < e; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            app.addEdge(u, v, w);
        }

        app.startNode = scanner.nextInt();
        app.destNode = scanner.nextInt();
        scanner.close();

        System.out.println("=== BFS from " + app.names[app.startNode] + " ===");
        List<Integer> bfsOrder = app.bfs(app.startNode);
        app.printPath(bfsOrder);

        System.out.println("=== DFS from " + app.names[app.startNode] + " ===");
        app.dfs(app.startNode);
        app.printPath(app.dfsorder);

        app.dijkstraAlg(app.startNode, app.destNode);
    }
}