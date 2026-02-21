// LeetCode: перед вставкой переименуй класс в Solution
//
// 283. Move Zeroes (Easy)
//
// Дан массив. Передвинь все нули в конец, сохраняя порядок остальных элементов.
// На месте (in-place), без создания нового массива.
//
// Подход: Two Pointers — быстрый/медленный (оба идут в одну сторону).
// Это другой подвид: не встречные указатели, а "погоня".
// Быстрый (i) бежит по массиву, медленный (insertPos) ждёт куда записать.
//
// Два прохода:
//   1) собираем все ненулевые в начало (порядок сохраняется)
//   2) заливаем хвост нулями
class MoveZeroes {
    // Временная сложность: O(n) — два прохода, но оба линейные
    // Пространственная сложность: O(1) — только переменные, новый массив не создаём
    public void moveZeroes(int[] nums) {
        // insertPos — позиция куда записать следующий ненулевой элемент
        int insertPos = 0;

        // первый проход: быстрый (i) бежит по всему массиву
        // если нашёл ненулевой — записываем на позицию insertPos и сдвигаем insertPos
        // нули просто пропускаем — они потом заполнятся
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                nums[insertPos++] = nums[i];
            }
        }

        // второй проход: всё от insertPos до конца — нули
        // тут оказались "дырки" от перемещённых элементов
        for (int i = insertPos; i < nums.length; ++i) {
            nums[i] = 0;
        }
    }

    public static void main(String[] args) {
        var sol = new MoveZeroes();

        // Пример 1: [0,1,0,3,12] → [1,3,12,0,0]
        int[] nums1 = {0, 1, 0, 3, 12};
        sol.moveZeroes(nums1);
        System.out.println(java.util.Arrays.toString(nums1));

        // Пример 2: [0] → [0]
        int[] nums2 = {0};
        sol.moveZeroes(nums2);
        System.out.println(java.util.Arrays.toString(nums2));
    }
}
