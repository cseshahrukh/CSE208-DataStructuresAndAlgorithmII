#include <bits/stdc++.h>
#define inf 1000000007
#define gap "    "
using namespace std;
int node, edge;

double dist [101][101];

void floyd ()
{

    for (int k = 1; k <= node; ++k)
    {
        for (int i = 1; i <= node; ++i)
        {
            for (int j = 1; j <= node; ++j)
            {
                if (dist[i][k] < inf && dist[k][j] < inf)
                    dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
            }
        }
    }
}
int main()
{
    freopen("text.txt", "r", stdin);

    cin>>node>>edge;


    for(int i=1; i<=node; i++)
        for(int j=1; j<=node; j++)
        {
            if (i==j)
                dist[i][j]=0;
            else
                dist[i][j]=inf;
        }

    int a, b;
    double w;

    for(int i=0; i<edge; i++)
    {
        cin>>a>>b>>w;
        dist[a][b]=w;
    }

    floyd();

    for(int i=1; i<=node; i++)
    {
        for(int j=1; j<=node; j++)
        {
            if (dist[i][j]==inf)
                cout<<"INF  ";
            else cout<<dist[i][j]<<gap;
        }
        cout<<endl;
    }

    return 0;
}
