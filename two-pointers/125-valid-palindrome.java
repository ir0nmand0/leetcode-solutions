// LeetCode: перед вставкой переименуй класс в Solution
//
// 125. Valid Palindrome (Easy)
//
// Дана строка. Убери всё кроме букв и цифр, приведи к нижнему регистру.
// Если читается одинаково в обе стороны — палиндром.
//
// Подход: Two Pointers — встречные указатели с пропуском не-алфавитных символов.
class ValidPalindrome {
    // Временная сложность: O(n) — один проход двумя указателями
    // Пространственная сложность: O(1) — только переменные, новую строку не создаём
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        // идём с двух сторон навстречу
        // left < right, а не <= : если строка нечётная, средний символ сам с собой совпадает,
        // проверять не нужно (например 'c' в "amanaplanacanalpanama")
        while (left < right) {
            // пропускаем всё что не буква/цифра справа (пробелы, запятые, двоеточия и т.д.)
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                --right;
            }

            // пропускаем всё что не буква/цифра слева
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                ++left;
            }

            // приводим оба символа к нижнему регистру для сравнения
            char charLeft = Character.toLowerCase(s.charAt(left));
            char charRight = Character.toLowerCase(s.charAt(right));

            // если символы не совпали — точно не палиндром, выходим сразу
            if (charLeft != charRight) {
                return false;
            }

            // совпали — сдвигаем оба указателя навстречу
            --right;
            ++left;
        }

        // все пары совпали — палиндром
        return true;
    }

    public static void main(String[] args) {
        var sol = new ValidPalindrome();

        // Пример 1: "A man, a plan, a canal: Panama" → true
        System.out.println(sol.isPalindrome("A man, a plan, a canal: Panama"));

        // Пример 2: "race a car" → false
        System.out.println(sol.isPalindrome("race a car"));

        // Пример 3: " " → true
        System.out.println(sol.isPalindrome(" "));
    }
}
