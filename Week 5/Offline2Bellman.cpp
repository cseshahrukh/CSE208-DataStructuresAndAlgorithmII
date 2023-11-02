// A C++ program for Bellman-Ford's single source
// shortest path algorithm.
#include <bits/stdc++.h>
#define pii pair<int,int>
#define INF 1000000007
using namespace std;
// a structure to represent a weighted edge in graph
struct Edge
{
    int src, dest, weight;
} ;


void BellmanFord(Edge edgeList[], int nodes, int edges , int src, int dest)
{
    vector<int> dist(nodes, INF);
    vector<int> parent(nodes, -1); //each node has no parent till now

    dist[src]=0;




    for (int i = 1; i <= nodes-1; i++)
    {
        for (int j = 0; j < edges ; j++)
        {
            int u = edgeList[j].src;
            int v = edgeList[j].dest;
            int weight = edgeList[j].weight;
            if (dist[u] != INF && dist[u] + weight < dist[v])
            {
                dist[v] = dist[u] + weight;
                parent[v]=u;
            }
        }
    }

    bool hasCycle=false;
    //  check for negative-weight cycles.
    for (int i = 0; i < edges; i++)
    {
        int u = edgeList[i].src;
        int v = edgeList[i].dest;
        int weight = edgeList[i].weight;
        if (dist[u] != INF && dist[u] + weight < dist[v])
        {
            printf("The graph contains a negative cycle");
            return ;
        }

    }

    cout<<"The graph does not contain a negative cycle"<<endl;
    cout<<"Shortest path cost: "<<dist[dest]<<endl;

    vector<int> result;

    while(parent[dest]!=-1)
    {
        result.push_back(dest);
        dest=parent[dest];
    }
    result.push_back(dest); //now dest == src

    reverse(result.begin(), result.end());

    cout<<result[0];
    for(int i=1; i<result.size(); i++)
    {
        cout<<" -> "<<result[i];
    }
    cout<<endl;

    return;
}

// Driver program to test above functions
int main()
{
    freopen("text.txt", "r", stdin);
    int nodes, edges, from, to, weight, source, dest;

    cin>>nodes>>edges;
    Edge edgeList[edges];

    //vector<pii> adj[nodes];

    for(int i=0; i<edges; i++)
    {
        cin>>from>>to>>weight;
        //adj[from].push_back({weight, to});
        edgeList[i].src=from, edgeList[i].dest=to, edgeList[i].weight=weight;
    }
    cin>>source>>dest;

    BellmanFord(edgeList, nodes, edges ,  source, dest);

    return 0;
}
