// C++ implementation of the
// Polynomial Rolling Hash Function

#include <bits/stdc++.h>
using namespace std;

// Function to calculate
// the hash of a string
string stringGenerator()
{

    string res="";
    for(int i=0; i<7; i++)
    {
        res+='a'+rand()%26;
    }
    return res;
}
int hashFunction(string str, int m)
{
    int len = 7;

    long long power = 1;
    long long hashValue = 0;

    // Loop to calculate the hash value
    // by iterating over the elements of string
    for (int i = 0; i < str.length(); i++)
    {
        hashValue= (hashValue+ (str[i] - 'a' + 1) * power)% m;
        power=(power * len) % m;
    }

    //return positive remainder only
    return (hashValue%m + m) % m;
}

/// Driver Code
int main()
{
    cout<<"Give size of table"<<endl;
    int m;
    cin>>m ;
    // Given strings
    string str1 = "geeksforgeeks";
    string str2 = "geeks";

    cout << "Hash of '" << str1 << "' = "
         << polynomialRollingHash(str1, m);
    cout<<"Has of '" <<str2 <<' '<<polynomialRollingHash(str2, m);
    cout<<stringGenerator()<<endl;
    cout<<stringGenerator()<<endl;
    cout << endl;
}
