import java.util.*;

public class remainingormminimize {
    public int numberOfPairs(ArrayList<ArrayList<Integer>> v) {
        int n = v.size();
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int x1 = v.get(i).get(0), y1 = v.get(i).get(1);

            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;

                int x2 = v.get(j).get(0), y2 = v.get(j).get(1);
                int f = 0;

                if (y1 >= y2 && x1 <= x2) {
                    for (int k = 0; k < n; k++) {
                        if (k == i || k == j)
                            continue;

                        int x3 = v.get(k).get(0), y3 = v.get(k).get(1);

                        if (x3 >= x1 && x3 <= x2 && y3 >= y2 && y3 <= y1) {
                            f = 1;
                            break;
                        }
                    }

                    if (f == 0)
                        ans++;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        // Instantiate the class and call the method if needed
    }
}
