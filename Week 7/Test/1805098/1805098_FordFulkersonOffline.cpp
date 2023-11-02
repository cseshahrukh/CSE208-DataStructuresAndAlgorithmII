#include <bits/stdc++.h>
#define inf 1000000000
#define pii pair<int,int>
#define pb push_back
int maxWin=0;
using namespace std;

vector<int> wins;
vector<int> losses;
vector<vector<int>> games;

vector<int> remaining;
vector<string> names;
vector<pair<pii, int>> edges; //from, to, capacity

bool bfs(vector<vector<int>> residualGraph, int parent[], int source, int sink)
{
    int n=residualGraph.size();
    bool visited[n];

    for(int i=0; i<n; i++)
        visited[i]=false;

    bool flag=false; //path ache kina

    queue<int> q;
    q.push(source);
    visited[source]=true;

    while(!q.empty())
    {
        int now=q.front();
        q.pop();

        for(int to=0; to<n; to++)
        {
            int capa=residualGraph[now][to];
            if (!visited[to] and capa>0) //ekhon capacity positive kina
            {

                parent[to]=now;
                visited[to]=true;
                q.push(to);

                if (to==sink)
                {
                    flag=true;
                    return flag;
                }
            }
        }
    }
    return flag;
}

int fordFulkerson(int source, int sink, int n)
{
    //ami maximum koto match flow korte parbo oita return korbo
    int edgeCount=edges.size();
    vector<vector<int>> residualGraph(n, (vector<int>(n, 0))); //2d matrix

    int parent[n];
    int maxFlow=0;
    for(int i=0; i<edgeCount;i++)
    {
        int from=edges[i].first.first;
        int to=edges[i].first.second;
        int capa=edges[i].second;

        residualGraph[from][to]+=capa; //edge er list take adj matrix e convert kore fellam
    }

    while(bfs(residualGraph, parent, source, sink))
    {

        //bfs korar somoy parent array te path tao pacchi
        int nowFlow=inf; //karon path er min value tai to currentFlow hobe . pore min function use korbo

        int Node1=sink;

        while(Node1!=source)
        {
            int nodeParent=parent[Node1];
            nowFlow=min(nowFlow, residualGraph[nodeParent][Node1]);
            Node1=nodeParent;
        }

        maxFlow+=nowFlow;

        Node1=sink;
        while(Node1!=source)
        {
            int nodeParent=parent[Node1];
            residualGraph[nodeParent][Node1]-=nowFlow;
            Node1=nodeParent;
        }

    }

    return maxFlow;

}


void buildGraph(int index, int teams)
{
    int maximumFlow=0;
    int source=teams; //emni ekta value dhorlam graph create er jonno
    int sink=teams+1; //emni dhorlam arekta notun value
    int graphNode=teams+2; //eita pore barbe jokhon amader graph e node create hobe
    //sob node er unique value

    int currentMax=wins[index]+remaining[index];
    if (currentMax<=maxWin)
    {
        cout<<names[index]<<" is eliminated."<<endl;
        cout<<"They can win at most "<<wins[index]<<" + "<<remaining[index]<<" = "<<wins[index]+remaining[index]<<" games"<<endl;
        cout<<endl;
        return;
    }
    edges.clear();

    for(int i=0; i<teams; i++)
    {
        if (i==index) //maxWin hocche global ei ache
        {
            continue; //i hote hobe index bade onno team gula
            //eder score kom hole to valoi //hehe
        }

        for(int j=0; j<i; j++) //
        {
            //i er poreo nite thakle ekta match 2 bar count hoye jabe tai i porjonto
            if ((j==index) or (games[i][j]==0))
                {
                    continue;
                }

                maximumFlow+=games[i][j]; //surute 0 thakbe jokhon function tar kaj suru korlo

                edges.pb({{source, graphNode}, games[i][j]});
                edges.pb({{graphNode, i}, inf });
                edges.pb({{graphNode, j}, games[i][j]});

                graphNode++;

        }

        int highestPossible=currentMax-wins[i]; //i theke sink er value highest joto hote pare

        edges.pb({{i,sink}, highestPossible});
    }

    //graph banano sesh !!!!!!!

    int nowFlow=fordFulkerson(source, sink, graphNode);

    if (nowFlow<maximumFlow)
    {
        //now flow is fordFulkerson er max flow
        cout<<names[index]<<" is eliminated."<<endl;
        cout<<"They can win at most "<<wins[index]<<" + "<<remaining[index]<<" = "<<wins[index]+remaining[index]<<" games"<<endl;
        cout<<endl;

    }
}

int main()
{
    freopen("text.txt", "r", stdin);
    int teams;
    string s;
    int win, loss, against, rem;
    cin>>teams;

    games.resize(teams, (vector<int> (teams)));

    for(int i=0; i<teams; i++)
        for(int j=0; j<teams; j++)
            games[i][j]=0;

    for(int i=0; i<teams; i++)
    {
        cin>>s;
        names.pb(s);
        cin>>win;
        wins.pb(win);
        maxWin=max(maxWin, win);

        cin>>loss;
        losses.pb(loss);

        cin>>rem;
        remaining.pb(rem);

        for(int j=0; j<teams; j++)
            cin>>games[i][j];


    }

    for(int i=0; i<teams; i++)
        buildGraph(i, teams); //proti i first hobe kina oita check korchi

    cout<<endl;
    return 0;
}
