class SegmentTree {
    int[] tree;
    int n;

    public SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];
        build(arr, 0, 0, n - 1);
    }

    void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;

            build(arr, 2 * node + 1, start, mid);
            build(arr, 2 * node + 2, mid + 1, end);

            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l)
            return 0;

        if (l <= start && end <= r)
            return tree[node];

        int mid = (start + end) / 2;

        return query(2 * node + 1, start, mid, l, r)
                + query(2 * node + 2, mid + 1, end, l, r);
    }

    public int rangeSum(int left, int right) {
        return query(0, 0, n - 1, left, right);
    }

    void update(int node, int start, int end, int idx, int value) {
        if (start == end) {
            tree[node] = value;
        } else {
            int mid = (start + end) / 2;

            if (idx <= mid)
                update(2 * node + 1, start, mid, idx, value);
            else
                update(2 * node + 2, mid + 1, end, idx, value);

            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    public void updateValue(int index, int value) {
        update(0, 0, n - 1, index, value);
    }
}

public class SegmentTreeCaseStudy {

    public static void main(String[] args) {

        int[] sales = {120, 150, 100, 180, 200, 170, 130, 160};

        System.out.println("Daily Sales Data:");
        for (int s : sales) {
            System.out.print(s + " ");
        }

        SegmentTree st = new SegmentTree(sales);

        System.out.println("\n\nTotal Sales from Day 2 to Day 6:");
        System.out.println(st.rangeSum(1, 5));

        System.out.println("\nUpdating Day 4 Sales from 180 to 220...");
        st.updateValue(3, 220);

        System.out.println("\nTotal Sales from Day 2 to Day 6 after Update:");
        System.out.println(st.rangeSum(1, 5));

        System.out.println("\nCase Study Completed Successfully.");
    }
}