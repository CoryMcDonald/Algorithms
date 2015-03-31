#include <cstdio>
#include <algorithm>
using namespace std;
 
int P[1001];
int W[1001];
int MW[101];
int dp[1001][31];
 
int solve(int N, int G) {
    int sum = 0;
    int maxw = *max_element(MW + 1, MW + 1 + G);
 
    for (int i = 0; i <= N; i++)
        dp[i][0] = 0;
    for (int w = 0; w <= maxw; w++)
        dp[0][w] = 0;
 
    for (int i = 1; i <= N; i++) {
        for (int w = 1; w <= maxw; w++) {
            if (W[i] > w)
                dp[i][w] = dp[i - 1][w];
            else
                dp[i][w] = max(dp[i - 1][w], P[i] + dp[i - 1][w - W[i]]);
        }
    }
 
    for (int p = 1; p <= G; p++)
        sum += dp[N][MW[p]];
 
    return sum;
}
 
int main() {
    int T, N, G;
    scanf("%d\n", &T);
    while (T--) {
        scanf("%d\n", &N);
        for (int i = 1; i <= N; i++)
            scanf("%d %d\n", &P[i], &W[i]);
        scanf("%d\n", &G);
        for (int i = 1; i <= G; i++)
            scanf("%d\n", &MW[i]);
        printf("%d\n", solve(N, G));
    }
    return 0;
}