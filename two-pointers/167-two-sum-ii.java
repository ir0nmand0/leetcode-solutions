// LeetCode: перед вставкой переименуй класс в Solution
class TwoSumII {
    // Временная сложность: O(n) — один проход двумя указателями
    // Пространственная сложность: O(1) — только два указателя, результат фиксированный (2 элемента)
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;

        // Два указателя: left — начало, right — конец (массив уже отсортирован)
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            // Считаем сумму двух крайних элементов
            int result = numbers[left] + numbers[right];

            if (result > target) {
                --right;   // Сумма слишком большая — уменьшаем правый
            } else if (result < target) {
                ++left;    // Сумма слишком маленькая — увеличиваем левый
            } else {
                // Нашли пару — возвращаем 1-indexed индексы (++left и ++right добавляют 1)
                return new int[]{++left, ++right};
            }
        }

        // Задача гарантирует решение — сюда не попадём, но если попали — это баг
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
