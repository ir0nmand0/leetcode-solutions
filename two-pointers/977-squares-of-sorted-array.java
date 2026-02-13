class Solution {
    // Временная сложность: O(n) — один проход по массиву
    // Пространственная сложность: O(n) — массив для результата
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;

        // Массив для результата — заполняем с конца (от большего к меньшему)
        int[] result = new int[n];

        // Два указателя: left — начало массива, right — конец
        int left = 0;
        int right = n - 1;

        // Позиция для записи в результат — стартуем с конца
        int pos = n - 1;

        // Идём пока указатели не встретятся
        while (left <= right) {
            // Считаем квадраты обоих краёв один раз
            int leftSq = nums[left] * nums[left];
            int rightSq = nums[right] * nums[right];

            // Сравниваем — больший квадрат кладём в текущую позицию
            if (leftSq > rightSq) {
                result[pos] = leftSq;
                left++;   // Сдвигаем левый указатель вправо
            } else {
                result[pos] = rightSq;
                right--;  // Сдвигаем правый указатель влево
            }

            // Переходим к следующей позиции (ближе к началу)
            pos--;
        }

        return result;
    }

    static void main(String[] args) {
        var sol = new Solution();
        System.out.println(java.util.Arrays.toString(
            sol.sortedSquares(new int[]{-4, -1, 0, 3, 10})
        )); // [0, 1, 9, 16, 100]
    }
}
