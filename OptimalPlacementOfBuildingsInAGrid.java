// Time Complexity : O(n!*H*W)
// Space Complexity : O(H*W)

/*
Given a grid with w as width, h as height.
Each cell of the grid represents a potential building lot and we will be adding "n" buildings inside this grid.
The goal is for the furthest of all lots to be as near as possible to a building.
Given an input n, which is the number of buildings to be placed in the lot, determine the building placement to
minimize the distance the most distant empty lot is from the building.
Movement is restricted to horizontal and vertical i.e. diagonal movement is not required.

For example, w=4, h=4 and n=3. An optimal grid placement sets any lot within two unit distance of the building.
The answer for this case is 2.

"0" indicates optimal building placement and in this case the maximal value of all shortest
distances to the closest building for each cell is "2".

    1 0 1 2

    2 1 2 1

    1 0 1 0

    2 1 2 1

 */

class Solution {
  public static class BuildingPlacement {
    int min;
    int H;
    int W;
    int n;
    int[][] grid;
    public BuildingPlacement(int H, int W, int n) {
      this.H = H;
      this.W = W;
      this.n = n;
      this.min = Integer.MAX_VALUE;
      this.grid = new int[this.H][this.W];
    }
    public int findMinDistance() {
      for(int i = 0; i < H; i++) {
        for(int j = 0; j < W; j++) {
          grid[i][j] = -1;
        }
      }
      backtrack(0,0,n);
      return min;
    }
    private void getDistance() {
      boolean[][] visited = new boolean[H][W];
      Queue<int[]> q = new LinkedList<>();
      int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
      for(int i = 0; i < H; i++) {
        for(int j = 0; j < W; j++) {
          if(grid[i][j] == 0) {
            q.add(new int[]{i,j});
            visited[i][j] = true;
          }
        }
      }
      int distance = 0;
      while(!q.isEmpty()) {
        int size = q.size();
        for(int i = 0; i < size; i++) {
          int[] current = q.poll();
          for(int[] dir: dirs) {
            int nr = current[0] + dir[0];
            int nc = current[1] + dir[1];
            if(nr >= 0 && nc >=0 && nr < H && nc < W && !visited[nr][nc]) {
              q.add(new int[]{nr,nc});
              visited[nr][nc] = true;
            }
          }
        }
        distance++;
      }
      min = Math.min(min, distance-1);
    }
    private void backtrack(int row, int col, int n) {
      // Base
      if(n == 0) {
        getDistance();
        return;
      }
      if(col == W) {
        col = 0;
        row++;
      }

      // Logic
      for(int i = row; i < H; i++) {
        for(int j = col; j < W; j++) {
          // Action
          grid[i][j] = 0;
          // Recurse
          backtrack(row, col+1, n-1);
          // Backtrack
          grid[i][j] = -1;
        }
        col = 0;
      }
    }
  }

  public static void Main(String[] args) {
    BuildingPlacement bp = new BuildingPlacement(4, 5, 2);
    System.out.println(bp.findMinDistance());
  }
}