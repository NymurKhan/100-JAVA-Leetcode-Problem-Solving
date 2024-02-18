class Solution {
  private static final int modulo = (int) 1e9 + 7;

  public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
    Set<Integer> seen = new HashSet<>();
    int l = hFences.length + 2;
    int[] h = Arrays.copyOf(hFences, l);
    h[l - 2] = 1;
    h[l - 1] = m;
    for (int i = 0; i < l; i++) {
      for (int j = 0; j < l; j++) {
        seen.add(Math.abs(h[j] - h[i]));
      }
    }

    l = vFences.length + 2;
    int[] v = Arrays.copyOf(vFences, l);
    v[l - 2] = 1;
    v[l - 1] = n;

    int side = 0;

    for (int i = 0; i < l; i++) {
      for (int j = i + 1; j < l; j++) {
        int distance = Math.abs(v[j] - v[i]);
        if (seen.contains(distance)) {
          side = Math.max(side, distance);
        }
      }
    }

    if (side == 0) {
      return -1;
    }

    return (int) ((long) side * side % modulo);
  }
}