// LeetCode: перед вставкой переименуй класс в Solution
class TwoSumII {
    // Временная сложность: O(n)
    // Пространственная сложность: O(1)
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int result = numbers[left] + numbers[right];

            if (result > target) {
                --right;
            } else if (result < target) {
                ++left;
            } else {
                return new int[]{++left, ++right};
            }
        }

        throw new IllegalArgumentException("No solution found");
    }

    static void main(String[] args) {
        var sol = new TwoSumII();

        // Пример 1: [2,7,11,15], target=9 → [1,2]
        System.out.println(java.util.Arrays.toString(
            sol.twoSum(new int[]{2, 7, 11, 15}, 9)
        ));

        // Пример 2: [2,3,4], target=6 → [1,3]
        System.out.println(java.util.Arrays.toString(
            sol.twoSum(new int[]{2, 3, 4}, 6)
        ));

        // Пример 3: [-1,0], target=-1 → [1,2]
        System.out.println(java.util.Arrays.toString(
            sol.twoSum(new int[]{-1, 0}, -1)
        ));
    }
}
