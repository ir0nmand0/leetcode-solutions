// LeetCode: перед вставкой переименуй класс в Solution
//
// 643. Maximum Average Subarray I (Easy)
//
// Дан массив и число k. Найди подмассив длиной k с максимальным средним.
// Верни это среднее значение.
//
// Подход: Sliding Window — скользящее окно фиксированного размера k.
// Вместо пересчёта суммы каждый раз — двигаем окно:
// добавляем новый правый элемент, убираем старый левый.
class MaxAverageSubarray {
    // Временная сложность: O(n)
// Пространственная сложность: O(1)
    public double findMaxAverage(int[] nums, int k) {
        int windowSum = 0;

        // 1) Считаем сумму первого окна [0 .. k-1]
        for (int i = 0; i < k; ++i) {
            windowSum += nums[i];
        }

        // maxSum — это максимум среди всех окон,
        // начинаем с первого окна
        int maxSum = windowSum;

        // 2) Двигаем окно вправо по одному элементу
        for (int i = k; i < nums.length; ++i) {
            // ВАЖНАЯ ИСТИНА sliding window:
            // windowSum = это ТЕКУЩАЯ сумма окна, а не максимум.
            // Поэтому текущую сумму окна ОБНОВЛЯЕМ ВСЕГДА,
            // даже если новое окно хуже предыдущего.
            //
            // Сдвиг окна:
            // + добавляем новый элемент справа (nums[i])
            // - убираем элемент слева, который вышел из окна (nums[i - k])
            windowSum += nums[i] - nums[i - k];

            // А вот максимум обновляем УСЛОВНО:
            // maxSum меняется только если текущее окно стало лучше.
            if (windowSum > maxSum) {
                maxSum = windowSum;
            }

            // Коротко:
            // windowSum -> "что сейчас в окне" (меняется каждый шаг)
            // maxSum    -> "лучшее из увиденного" (меняется не всегда)
        }

        // Среднее = максимальная сумма окна / размер окна
        return (double) maxSum / k;
    }

    public static void main(String[] args) {
        var sol = new MaxAverageSubarray();

        // Пример 1: [1,12,-5,-6,50,3], k=4 → 12.75
        System.out.println(sol.findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));

        // Пример 2: [5], k=1 → 5.0
        System.out.println(sol.findMaxAverage(new int[]{5}, 1));
    }
}
