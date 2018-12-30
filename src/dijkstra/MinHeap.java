package dijkstra;


class HeapNode {
    int v;
    int dist;

    public HeapNode(int v, int dist) {
        this.v = v;
        this.dist = dist;
    }
}

public class MinHeap {
    int size;
    int capacity;
    int[] pos;
    HeapNode[] nodes;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        size = 0;
        pos = new int[capacity];
        nodes = new HeapNode[capacity];
    }

    public void setSize(int V) {
        size = V;
    }

    public void addNewNode(int v, int dist) {
        nodes[v] = new HeapNode(v, dist);
        pos[v] = v;
    }

    private void swap(int indexOne, int indexTwo) {
        HeapNode temp = nodes[indexOne];
        nodes[indexOne] = nodes[indexTwo];
        nodes[indexTwo] = temp;
    }

    //if a given vertex 'v' is in a heap or not?
    public boolean isInHeap(int v) {
        if (pos[v] < size) return true;
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void heapify(int index) {
        int smallest, left, right;
        smallest = index;
        left = 2 * index + 1;
        right = 2 * index + 2;

        if ((left < size) && (nodes[left].dist < nodes[smallest].dist)) {
            smallest = left;
        }

        if ((right < size) && (nodes[right].dist < nodes[smallest].dist)) {
            smallest = right;
        }

        if (smallest != index) {
            HeapNode smallestNode = nodes[smallest];
            HeapNode indexNode = nodes[index];

            //Swap positions
            pos[smallestNode.v] = index;
            pos[indexNode.v] = smallest;

            //Swap nodes
            swap(smallest, index);

            heapify(smallest);
        }
    }

    public HeapNode extractMin() {
        if (isEmpty()) {
            return null;
        }

        //Store the root node
        HeapNode root = nodes[0];

        //Replace root node with the last node
        HeapNode lastNode = nodes[size-1];
        nodes[0] = lastNode;

        //Update position of last node
        pos[root.v] = size-1;
        pos[lastNode.v] = 0;

        //Reduce heap size and heapify root
        size--;
        heapify(0);

        return root;
    }

    public void decreaseKey(int v, int dist) {
        //Get the index of v in the heap array
        int i = pos[v];

        //Get the node and update its dist value
        nodes[i].dist = dist;

        //Travel up while the complete tree is not hepified
        //This is a O(Logn) loop
        while (i>0 && nodes[i].dist < nodes[(i-1)/2].dist) {
            //Swap this node with its parent
            pos[nodes[i].v] = (i-1)/2;
            pos[nodes[(i-1)/2].v] = i;
            swap(i, (i-1)/2);

            //move to parent index
            i = (i-1)/2;
        }
    }


    public void setSourceVertex(int src) {
        //nodes[src] = new HeapNode(src, dist);
        nodes[src].dist = 0;
        //pos[src] = src;
        decreaseKey(src, 0);
    }

}
