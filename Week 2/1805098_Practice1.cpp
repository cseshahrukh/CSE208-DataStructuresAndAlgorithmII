#include <bits/stdc++.h>

using namespace std;

#define N 1000
#define ll long long
#define pb push_back

int vis[N+1];
vector<int> adj[N+1];
vector<int> tr[N+1];
vector<int> order; //outtime er increasing order e thakbe
vector<int> SCC; //ekta peye gele clear korbo next ta pawar jonno


void file_i_o()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
#ifndef ONLINE_JUDGE
    freopen("text.txt", "r", stdin);
#endif // ONLINE_JUDGE
}


void dfs(int node)
{
    vis[node]=1;
    for(int child: adj[node])
        if (vis[child]==0)
            dfs(child);

    order.push_back(node);

}

void dfsanother(int node)
{
    vis[node]=1;
    for(int child: tr[node])
    {
        if(vis[child]==0)
            dfsanother(child);
    }
    SCC.push_back(node);
}


int main()
{
    file_i_o();
    ll n, m, a, b;
    cin>>n>>m;
    for(int i=0; i<m; i++)
    {
        cin>>a>>b;
        adj[a].push_back(b);
        tr[b].pb(a);
    }

    for(int i=1; i<=n; i++)
        if (vis[i]==0)
            dfs(i);

    for(int i=1; i<=n; i++)
        vis[i]=0;

    for(int i=1; i<=n; i++)
    {
        if (vis[order[n-i]]==0)
        {
            SCC.clear();
            dfsanother(order[n-i]);

            if (SCC.size()!=1)
            {
                sort(SCC.begin(), SCC.end()); //emni korlam as sample output e sorted rakha
                for(int node: SCC)
                    cout<<node<<" ";
                cout<<endl;
            }
        }
    }
}
