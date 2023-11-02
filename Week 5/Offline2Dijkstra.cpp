#include<bits/stdc++.h>
using namespace std;

#define INF 1000000007

//typedef pair<int, int> iPair;
#define iPair pair<int, int>


void dijkstra(vector<pair<int,int> > adj[], int nodes, int src, int dest)
{

    priority_queue< iPair, vector <iPair> , greater<iPair> > pq;

    //storing the distance in vector
    vector<int> dist(nodes, INF);
    vector<int> parent(nodes, -1); //each node has no parent till now


    pq.push(make_pair(0, src));
    dist[src] = 0;


    while (!pq.empty())
    {
        // The first vertex in pair is the minimum distance vertex
        int u = pq.top().second; //current node
        pq.pop();


        for (iPair weightPLUSdest : adj[u])
        {

            int v = weightPLUSdest.second;
            int weight = weightPLUSdest.first;


            if (dist[v] > dist[u] + weight)
            {
                // Update
                dist[v] = dist[u] + weight;
                pq.push(make_pair(dist[v], v));
                parent[v]=u;
            }
        }
    }



//    for (int i = 0; i < nodes; ++i)
//        printf("%d \t\t %d\n", i, dist[i]);

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

}


int main()
{

    freopen("text.txt", "r", stdin);
    int nodes, edges;
    cin>>nodes>>edges;
    vector<iPair > adj[nodes]; //weight, destination

    int from, to, weight;
    for(int i=0; i<edges; i++)
    {
        cin>>from>>to>>weight;
        adj[from].push_back(make_pair(weight, to));
    }

    int a, b; //source and destination
    cin>>a>>b;

    dijkstra(adj, nodes, a, b);

    return 0;
}
