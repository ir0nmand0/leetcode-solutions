import java.util.*;

// LeetCode: перед вставкой переименуй класс в Solution
//
// 3. Longest Substring Without Repeating Characters (Medium)
//
// Дана строка s.
// Нужно найти длину самого длинного подряд идущего фрагмента строки,
// в котором нет повторяющихся символов.
//
// Важно:
// - Подстрока = символы идут подряд (не подпоследовательность!)
// - Вернуть нужно только длину
//
// Подход: Sliding Window — гибкое окно + HashSet.
// Окно [left..i] всегда содержит только уникальные символы.
// Если новый символ уже в set — сжимаем окно слева,
// удаляя символы из set, пока дубликат не вылетит.
//
// ВАЖНО: условие while — chars.contains(tmp), НЕ chars.contains(s.charAt(left)).
// Мы ищем конкретный дубликат, а не "любой символ в set".
// Иначе окно схлопнется до [i, i] каждый раз (удалит всё подряд).
class LongestSubstringNoRepeat {
    // Временная сложность: O(n) — каждый символ добавляется и удаляется из set максимум 1 раз
    // Пространственная сложность: O(min(n, размер алфавита)) — set хранит только уникальные символы окна
    public int lengthOfLongestSubstring(String s) {
        // Set хранит символы ТЕКУЩЕГО окна [left..i]
        Set<Character> chars = new HashSet<>();
        int left = 0;

        // max = 0: для пустой строки вернём 0 без специальной проверки
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            char tmp = s.charAt(i);

            // Если tmp уже в окне — сжимаем слева, пока дубликат не уйдёт.
            // Удаляем именно символы слева (s.charAt(left)), но крутим цикл
            // пока в set есть ДУБЛИКАТ (tmp). Так сохраняем все символы
            // между новым left и i, которые не конфликтуют.
            //
            // Пример "dvdf", i=2, tmp='d':
            //   chars={d,v} → remove 'd' → chars={v}, left=1 → 'd' не в set → стоп
            //   Окно стало [1..2] = "vd" — 'v' сохранилась!
            while (chars.contains(tmp)) {
                chars.remove(s.charAt(left));
                ++left;
            }

            // Добавляем текущий символ в окно
            chars.add(tmp);

            // Длина текущего окна [left..i] = i - left + 1
            int currentWindows = i - left + 1;

            if (currentWindows > max) {
                max = currentWindows;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        var sol = new LongestSubstringNoRepeat();

        // Ожидается 3
        System.out.println(sol.lengthOfLongestSubstring("abcabcbb"));

        // Ожидается 1
        System.out.println(sol.lengthOfLongestSubstring("bbbbb"));

        // Ожидается 3
        System.out.println(sol.lengthOfLongestSubstring("pwwkew"));

        // Проверочный кейс
        System.out.println(sol.lengthOfLongestSubstring("abba"));
    }
}
