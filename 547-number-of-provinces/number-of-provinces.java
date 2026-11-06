class Solution {

    static void dfs(int node, int[][] isConnected, boolean[] visted){

        visted[node] = true;

        for(int j = 0;j<isConnected.length;j++){
            if(isConnected[node][j] == 1 && !visted[j]){
                dfs(j, isConnected,visted);
            }
        }

    }
    public int findCircleNum(int[][] isConnected) { 
        int n = isConnected.length;
        boolean[] visted = new boolean[n];

        int provinces = 0;

        for(int i=0;i<n;i++){
            if(!visted[i]){
                provinces++;
                dfs(i, isConnected, visted);
            }
        } 
        return provinces;
        
    }
}