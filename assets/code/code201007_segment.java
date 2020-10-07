public class code201007_segment {
	static long[] tree;
	static int[] input = { 3, 9, 4, 2, 7, 5, 10, 1 };

	public static void main(String[] args) {
		int N = input.length;
		int h = (int) Math.ceil(Math.log(N) / Math.log(2)); // log2(N)
		int size = (int) Math.pow(2, h + 1);

		tree = new long[size];
		System.out.println(size);
		init(1, 0, N - 1);
		System.out.println("3 ~ 6의 구간합은 : " + sum(1, 0, N - 1, 3, 6));
		printTree();
		System.out.println("5번째 원소의 값 5를 3로 변환, diff = -2");
		update(1, 0, N - 1, 5, -2);
		printTree();

	}

	// 초기화
	private static long init(int node, int start, int end) {
		if (start == end) // 리프노드(단일원소값인 경우)
			return tree[node] = input[start];

		int mid = (start + end) / 2;
		return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
	}

	// 구간 합 구하기
	private static long sum(int node, int start, int end, int left, int right) {
		if (left > end || right < start) // 첫번째 경우
			return 0;
		if (left <= start && end <= right) // 두번째 경우
			return tree[node];

		int mid = (start + end) / 2; // 그 외
		return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
	}

	// 원소 변경하기
	private static void update(int node, int start, int end, int index, int diff) {
		if (index < start || index > end)
			return;
		tree[node] += diff;
		if (start != end) {
			int mid = (start + end) / 2;
			update(node * 2, start, mid, index, diff);
			update(node * 2 + 1, mid + 1, end, index, diff);
		}
	}

	// ===================================================
	// 출력용
	private static void printTree() {
		System.out.println("========================");
		print(1, " /");
		System.out.println("========================");
	}

	private static void print(int idx, String sub) {
		System.out.println(sub + tree[idx]);
		if (idx * 2 < tree.length)
			print(idx * 2, "  " + sub);
		if (idx * 2 + 1 < tree.length)
			print(idx * 2 + 1, "  " + sub);
	}
}
