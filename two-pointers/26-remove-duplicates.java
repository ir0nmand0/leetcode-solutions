// LeetCode: перед вставкой переименуй класс в Solution
//
// 26. Remove Duplicates from Sorted Array (Easy)
//
// Дан отсортированный массив. Удали дубликаты на месте (in-place).
// Верни количество уникальных элементов.
// Первые k элементов массива должны содержать уникальные значения.
// Что останется после k — неважно.
//
// Подход: Two Pointers — быстрый/медленный (оба идут в одну сторону).
// Похоже на 283 Move Zeroes, но вместо "пропускай нули" — "пропускай дубликаты".
// Массив отсортирован — значит дубликаты всегда рядом.
class RemoveDuplicates {
    // Временная сложность: O(n) — один проход быстрым указателем
    // Пространственная сложность: O(1) — только переменные
    public int removeDuplicates(int[] nums) {
        // slow — позиция последнего записанного уникального элемента
        int slow = 0;

        // fast бежит по массиву начиная со второго элемента (первый всегда уникален)
        for (int fast = 1; fast < nums.length; ++fast) {
            // массив отсортирован — дубликаты идут подряд
            // если nums[fast] отличается от nums[slow] — это новый уникальный элемент
            if (nums[slow] != nums[fast]) {
                // записываем на следующую позицию после slow
                nums[++slow] = nums[fast];
            }
            // если равны — дубликат, просто пропускаем (fast уйдёт дальше)
        }

        // slow = индекс последнего уникального, +1 = количество уникальных
        return ++slow;
    }

    public static void main(String[] args) {
        var sol = new RemoveDuplicates();

        // Пример 1: [1,1,2] → k=2, nums=[1,2,...]
        int[] nums1 = {1, 1, 2};
        int k1 = sol.removeDuplicates(nums1);
        System.out.println("k=" + k1 + " " + java.util.Arrays.toString(nums1));

        // Пример 2: [0,0,1,1,1,2,2,3,3,4] → k=5, nums=[0,1,2,3,4,...]
        int[] nums2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int k2 = sol.removeDuplicates(nums2);
        System.out.println("k=" + k2 + " " + java.util.Arrays.toString(nums2));
    }
}
