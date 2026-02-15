// LeetCode: перед вставкой переименуй класс в Solution
//
// ВАЖНО: «контейнер для воды» = прямоугольник. Вода тут ни при чём.
// Прямоугольник игнорирует всё что между двумя выбранными индексами —
// даже если между ними есть значения больше, они не мешают и не влияют.
// Реальная суть: найди пару индексов (i, j) с максимальной площадью min(h[i], h[j]) × (j - i).
// (Задача 42. Trapping Rain Water — вот там рельеф между палками реально важен.)
class ContainerWithMostWater {
    // Временная сложность: O(n) — один проход двумя указателями
    // Пространственная сложность: O(1) — только переменные
    public int maxArea(int[] height) {
        // Два указателя с краёв — стартуем с максимальной ширины
        int left = 0;
        int right = height.length - 1;

        // Храним лучший результат
        int maxArea = Integer.MIN_VALUE;

        // Сдвигаем указатели навстречу пока не встретятся
        while (left < right) {
            int valRight = height[right];
            int valLeft = height[left];

            // Высота прямоугольника = меньшее из двух значений (ограничено короткой стенкой)
            int minHeight;
            // Ширина = расстояние между индексами
            int width = right - left;

            // Двигаем короткую стенку — высокую двигать бесполезно,
            // высота прямоугольника ограничена короткой, а ширина и так уменьшится
            if (valLeft < valRight) {
                minHeight = valLeft;
                ++left;    // Левая короче — ищем повыше справа
            } else {
                minHeight = valRight;
                --right;   // Правая короче — ищем повыше слева
            }

            // Площадь прямоугольника = высота × ширина
            int area = minHeight * width;

            // Перезаписываем максимум если нашли больше
            if (area > maxArea) {
                maxArea = area;
            }

        }

        return maxArea;
    }

    static void main(String[] args) {
        var sol = new ContainerWithMostWater();

        // Пример 1: [1,8,6,2,5,4,8,3,7] → 49
        System.out.println(sol.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));

        // Пример 2: [1,1] → 1
        System.out.println(sol.maxArea(new int[]{1, 1}));
    }
}
