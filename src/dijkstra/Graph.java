package dijkstra;

class GraphNode {
    int dest;
    int weight;
    GraphNode next;

    public GraphNode(int dest, int weight, GraphNode next) {
        this.dest = dest;
        this.weight = weight;
        this.next = next;
    }
}

class Vertex {
    String name;
    GraphNode adjNodes;
    Vertex(String name, GraphNode adjNodes) {
        this.name = name;
        this.adjNodes = adjNodes;
    }
}


public class Graph {
    private int V; //Size of the array(number of vertices in graph)
    private Vertex[] array;

    public Graph(int v) {
        V = v;

        array = new Vertex[V];
        //Initialize each vertex as empty by making head as null
        for (int i = 0; i < V; ++i) {
            array[i] = new Vertex("", null);
        }
    }

    public void addEdge(int src, int dest, int weight) {
        GraphNode newNode = new GraphNode(dest, weight, null);
        newNode.next = array[src].adjNodes;
        array[src].adjNodes = newNode;

        //Since graph is undirected, add an edge from dest to src also
        newNode = new GraphNode(src, weight, null);
        newNode.next = array[dest].adjNodes;
        array[dest].adjNodes = newNode;
    }

    public int getV() {
        return V;
    }

    //The main function that calculates distances of shortest paths from src to all
    //vertices. It is a O(ELogV) function
    public void dijkstra(int src) {
        //int V = this.V; //Get the number of vertices in graph
        int[] dist = new int[V]; //dist values used to pick min weight edge in cut

        //minHeap represents set E
        MinHeap minHeap = new MinHeap(V);

        //Initialize min heap with all vertices. dist value of all vertices
        for (int v = 0; v < V; ++v) {
            dist[v] = Integer.MAX_VALUE;
            minHeap.addNewNode(v, dist[v]);
        }

        //Make dist value of src vertex as 0 so that it is extracted first
        dist[src] = 0;
        minHeap.setSourceVertex(src);

        //Initially size of min heap is equal to V
        minHeap.setSize(V);

        //In the following loop, min heap contains all nodes
        //whose shortest distance is not yet finalized
        while ( !minHeap.isEmpty() ) {
            //Extract the vertex with minimum distance value
            HeapNode minHeapNode = minHeap.extractMin();
            int u = minHeapNode.v; //Store the extracted vertex number

            //Traverse through all adjacent vertices of u (the extracted vertex)
            //and update their distance values
            GraphNode pCrawl = array[u].adjNodes;
            while (pCrawl != null) {
                int v = pCrawl.dest;

                //if shortest distances to v is not finalized yet, and distance to v
                //through u is less than its previously calculated distance
                if (minHeap.isInHeap(v) && dist[u] != Integer.MAX_VALUE &&
                        pCrawl.weight + dist[u] < dist[v]) {
                    dist[v] = dist[u] + pCrawl.weight;

                    //update distance value in min heap also
                    minHeap.decreaseKey(v, dist[v]);
                }
                pCrawl = pCrawl.next;
            }
        }

        //print the calculated shortest distances
        //printArr(dist, V);

    }

    //The main function that calculates distances of shortest path from src to destination
    //vertex. It is a O(ELogV) function
    public String dijkstra(int src, int dest) {
        int[] dist = new int[V]; //dist values used to pick min weight edge in cut
        int[] parent = new int[V];

        //minHeap represents set E
        MinHeap minHeap = new MinHeap(V);

        //Initialize min heap, distances and parents
        for (int v = 0; v < V; ++v) {
            dist[v] = Integer.MAX_VALUE;
            minHeap.addNewNode(v, dist[v]);
            parent[v] = -1;
        }

        //Make dist value of src vertex as 0 so that it is extracted first
        dist[src] = 0;
        minHeap.setSourceVertex(src);

        //Initially size of min heap is equal to V
        minHeap.setSize(V);

        //In the following loop, min heap contains all nodes
        //whose shortest distance is not yet finalized
        boolean pathNotFound = true;
        while ( !minHeap.isEmpty() && pathNotFound ) {
            //Extract the vertex with minimum distance value
            HeapNode minHeapNode = minHeap.extractMin();
            int u = minHeapNode.v; //Store the extracted vertex number

            if (u != dest) {

                //Traverse through all adjacent vertices of u (the extracted vertex)
                //and update their distance values
                GraphNode pCrawl = array[u].adjNodes;
                while (pCrawl != null) {
                    int v = pCrawl.dest;

                    //if shortest distances to v is not finalized yet, and distance to v
                    //through u is less than its previously calculated distance
                    if (minHeap.isInHeap(v) && dist[u] != Integer.MAX_VALUE &&
                            pCrawl.weight + dist[u] < dist[v]) {
                        dist[v] = dist[u] + pCrawl.weight;
                        parent[v] = u;

                        //update distance value in min heap also
                        minHeap.decreaseKey(v, dist[v]);
                    }
                    pCrawl = pCrawl.next;
                }
            } else pathNotFound = false;
        }

        String sp = shortestPath(parent, "", dest) + " : " + dist[dest];

        return sp;


    }

    private String shortestPath(int[] parent, String pathStr, int endV) {

        if (endV == -1) return pathStr;

        pathStr = shortestPath(parent, pathStr, parent[endV]);
        pathStr = pathStr.concat(String.valueOf(endV)).concat(" ");
        return pathStr;

    }

}
