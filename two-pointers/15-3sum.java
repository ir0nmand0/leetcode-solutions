import java.util.Arrays;
import java.util.List;

// LeetCode: перед вставкой переименуй класс в Solution
class ThreeSum {
    // Временная сложность: O(n²) — внешний цикл O(n) × Two Pointers O(n)
    // Пространственная сложность: O(n) — результат (сортировка in-place)
    public java.util.List<java.util.List<Integer>> threeSum(int[] nums) {
        // Сортируем — без этого Two Pointers не работает и дубликаты не отловить
        // Массив может содержать дубликаты элементов, а ответ требует уникальные тройки —
        // сортировка ставит одинаковые значения рядом, что позволяет пропускать их простой проверкой соседей
        java.util.Arrays.sort(nums);

        java.util.ArrayList<java.util.List<Integer>> threeSum = new java.util.ArrayList<>();

        // Фиксируем первый элемент тройки
        for (int i = 0; i < nums.length; i++) {
            // Пропуск дубликата i: то же значение → тот же поиск в более узком диапазоне → те же тройки
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Два указателя ищут пару в оставшейся части массива
            int left = i + 1;

            int right = nums.length - 1;

            while (left < right) {
                int result = nums[i] + nums[left] + nums[right];

                if (result > 0) {
                    --right;   // Сумма слишком большая — уменьшаем правый
                } else if (result < 0) {
                    ++left;    // Сумма слишком маленькая — увеличиваем левый
                } else {
                    // Нашли тройку — добавляем
                    threeSum.add(List.of(nums[i], nums[left], nums[right]));
                    // Сдвигаем оба указателя — текущая пара уже использована
                    --right;
                    ++left;
                    // Пропуск дубликатов left: то же значение left при том же i → та же тройка
                    while (left < right && nums[left] == nums[left - 1]){
                        ++left;
                    }
                    // Пропуск дубликатов right: то же значение right при том же i → та же тройка
                    while (left < right && nums[right] == nums[right + 1]){
                        --right;
                    }
                }
            }

        }

        return threeSum;
    }

    static void main(String[] args) {
        var sol = new ThreeSum();

        // Пример 1: [-1,0,1,2,-1,-4] → [[-1,-1,2],[-1,0,1]]
        System.out.println(sol.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));

        // Пример 2: [0,1,1] → []
        System.out.println(sol.threeSum(new int[]{0, 1, 1}));

        // Пример 3: [0,0,0] → [[0,0,0]]
        System.out.println(sol.threeSum(new int[]{0, 0, 0}));
    }
}
