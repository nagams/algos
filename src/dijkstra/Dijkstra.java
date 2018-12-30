package dijkstra;

public class Dijkstra {

    public static void main(String[] args) {
        // create the graph given in above fugure
        int V = 9;
        Graph graph = new Graph(V);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 7, 8);
        graph.addEdge(1, 2, 8);
        graph.addEdge(1, 7, 11);
        graph.addEdge(2, 3, 7);
        graph.addEdge(2, 8, 2);
        graph.addEdge(2, 5, 4);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 5, 14);
        graph.addEdge(4, 5, 10);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 7, 1);
        graph.addEdge(6, 8, 6);
        graph.addEdge(7, 8, 7);

//        graph.dijkstra(0);
        System.out.println(graph.dijkstra(0, 4));

    }

    //The main function that calculates distances of shortest paths from src to all
    //vertices. It is a O(ELogV) function
/*
    public void dijkstra(Graph graph, int src) {
        int V = graph.getV(); //Get the number of vertices in graph
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
            GraphNode pCrawl = graph.array[u].adjNodes;
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
        printArr(dist, V);

    }

    private void printArr(int[] dist, int n) {
        System.out.println("Vertex  Distance from Source");
        for (int i=0; i<n; i++) {
            System.out.println(i + "\t\t" + dist[i] );
        }
    }
*/
}
