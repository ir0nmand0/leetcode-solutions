import java.util.*;

// LeetCode: перед вставкой переименуй класс в Solution
//
// 76. Minimum Window Substring (Hard)
//
// Даны две строки s и t.
// Найти минимальную подстроку в s которая содержит ВСЕ символы t
// включая дубликаты. Если такой нет — вернуть "".
//
// Важно:
// - Подстрока = символы идут подряд (не подпоследовательность!)
// - Мусор в окне разрешён — важно только присутствие всех символов t
// - Вернуть нужно подстроку, не длину (в отличие от задачи 209)
//
// Пример:
// s="ADOBECODEBANC", t="ABC" → "BANC"
// N в ответе — мусор, физически застрял между B и C.
// Подстрока непрерывна — перепрыгнуть через N нельзя.
//
// Это не анаграмма (567):
// - 567: окно фиксированное len(t), мусор запрещён, ищем есть ли хоть одна
// - 76:  окно гибкое, мусор разрешён, ищем минимальное из всех валидных
//
// Нельзя остановиться на первом валидном окне — правее может быть короче:
// "ADOBEC"(6) → "CODEBA"(6) → "BANC"(4) — финальный ответ правее.
// Поэтому алгоритм всегда идёт до конца строки.
//
// Подход: Sliding Window — гибкое окно + два частотных массива + have/need.
//
// ПОЧЕМУ ДВА МАССИВА, А НЕ ОДИН:
// freqT — эталон из t, не меняется. Отвечает: "сколько нужно".
// freqW — текущее окно, меняется. Отвечает: "сколько есть".
// Сравнение freqW[c] == freqT[c] — мост между ними.
//
// ПОЧЕМУ have/need, А НЕ Arrays.equals:
// Arrays.equals(freqW, freqT) на каждом шаге = O(128) с константой.
// have/need заменяют это на O(1) — одно сравнение двух чисел.
// have и need оба считают УНИКАЛЬНЫЕ символы — поэтому их можно сравнивать.
// need = сколько уникальных нужно закрыть (планка, константа).
// have = сколько уникальных уже закрыто (прогресс к планке).
//
// ПОЧЕМУ need СЧИТАЕТ УНИКАЛЬНЫЕ, А НЕ ДЛИНУ t:
// t="AABB" → need=2, не 4. Это не требование задачи — внутренний трюк.
// Дубликаты живут в freqT[c] как норма. have не вырастет пока норма
// не набрана: freqT['A']=2 → have++ только когда freqW['A']==2.
// need/have — счётчик закрытых задач. freqT/freqW — норма каждой задачи.
//
// КОГДА have ОБНОВЛЯЕТСЯ:
// have++ когда количество в окне совпало с количеством в эталоне:
// freqW[c] == freqT[c] → "набрали ровно столько сколько нужно".
// Три случая:
// freqW[c] < freqT[c]  → норма не закрыта  → пропуск
// freqW[c] == freqT[c] → только что закрыли → have++
// freqW[c] > freqT[c]  → излишек            → пропуск
//
// ПОЧЕМУ МУСОР НЕ ПИШЕМ В freqW:
// freqT[мусор] == 0 → условие freqT[c] > 0 не пройдёт никогда.
// Значит значение freqW[мусор] никогда не читается — незачем хранить.
// Оптимизация: if (freqT[c] > 0) → не пишем мусор в freqW вообще.
//
// ПОЧЕМУ ИЗЛИШЕК НЕЛЬЗЯ ОБРЕЗАТЬ (freqW[c] cap at freqT[c]):
// right едёт только вправо — если не записали символ на позиции i,
// вернуться нельзя. Когда left уберёт старый экземпляр, запасного нет.
// Пример: B[3] уйдёт влево → B[9] должен закрыть норму.
// Если B[9] не записан → "BANC" не найдём → баг.
//
// ПОЧЕМУ resLeft/resRight, А НЕ ПРОСТО minLen:
// left/right постоянно двигаются и теряют историю.
// resLeft/resRight — снимок лучшего момента, не теряется.
// В конце s.substring(resLeft, resRight + 1) — вырезаем подстроку.
//
// ПОЧЕМУ int[128]:
// Условие задачи: upper + lower case English letters.
// Символ == его ASCII индекс: 'A'=65, 'z'=122 — всё влезает в 128.
// Быстрее HashMap: нет хэширования, просто arr[c].
// int[26] не подходит — оба регистра одновременно.
//
// ИТОГО ДВЕ РАБОЧИХ ОПТИМИЗАЦИИ:
// 1. Мусор    — freqT[c]==0 → не пишем в freqW вообще           ✅
// 2. have/need — не сравниваем массивы целиком O(128) каждый шаг ✅
// 3. Излишек  — cap freqW at freqT                              ❌ ломает алгоритм
class MinimumWindowSubstring {

    // ========================================================================
    // Решение v1 — HashMap + счётчик formed (первый рабочий вариант)
    // ========================================================================
    // Время:  O(|s| + |t|) — каждый символ добавляется и удаляется максимум 1 раз
    // Память: O(|s| + |t|) — HashMap может хранить все уникальные символы
    //
    // Проблема: HashMap медленнее int[] из-за хэширования и boxing/unboxing.
    // Зато читается ближе к формулировке задачи — хорошо для первого подхода.
    //
    // public String minWindow(String s, String t) {
    //     if (s.isEmpty() || t.isEmpty() || t.length() > s.length()) return "";
    //
    //     Map<Character, Integer> freqT = new HashMap<>();
    //     for (char c : t.toCharArray()) freqT.merge(c, 1, Integer::sum);
    //
    //     int need = freqT.size(); // уникальных символов в t
    //     int have = 0;
    //
    //     Map<Character, Integer> freqW = new HashMap<>();
    //     int left = 0;
    //     int minLen = Integer.MAX_VALUE;
    //     int resLeft = 0, resRight = 0;
    //
    //     for (int right = 0; right < s.length(); right++) {
    //         char c = s.charAt(right);
    //         freqW.merge(c, 1, Integer::sum);
    //
    //         // have++ только в момент совпадения нормы
    //         if (freqT.containsKey(c) && freqW.get(c).equals(freqT.get(c))) have++;
    //
    //         while (have == need) {
    //             if (right - left + 1 < minLen) {
    //                 minLen = right - left + 1;
    //                 resLeft = left; resRight = right;
    //             }
    //             char lc = s.charAt(left);
    //             freqW.merge(lc, -1, Integer::sum);
    //             if (freqT.containsKey(lc) && freqW.get(lc) < freqT.get(lc)) have--;
    //             left++;
    //         }
    //     }
    //
    //     return minLen == Integer.MAX_VALUE ? "" : s.substring(resLeft, resRight + 1);
    // }

    // ========================================================================
    // Решение v2 — int[128] + have/need (оптимальное решение)
    // ========================================================================
    // Время:  O(|s| + |t|) — каждый символ добавляется и удаляется максимум 1 раз
    // Память: O(1)         — массивы фиксированного размера 128, не зависят от входа
    //
    // Оптимизация над v1:
    // - int[128] вместо HashMap: нет хэширования, символ == ASCII индекс
    // - один проход для построения freqT и подсчёта need
    // - мусор не пишем в freqW (freqT[c] == 0 → пропуск)
    public String minWindow(String s, String t) {

        if (s.isEmpty() || t.isEmpty() || t.length() > s.length()) return "";

        int[] freqT = new int[128];
        int[] freqW = new int[128];

        // Один проход: заполняем эталон и считаем need.
        // freqT[c]==1 — момент уникальности символа, дубли пропускаем.
        // need считает уникальные символы, не длину t:
        // t="AABB" → need=2, не 4. Норма каждого хранится в freqT[c].
        int need = 0;
        for (char c : t.toCharArray()) {
            freqT[c]++;
            if (freqT[c] == 1) need++;
        }

        // have тоже считает уникальные — символы у которых норма закрыта.
        // have и need считают одно и то же (уникальные) — поэтому
        // сравнение have==need имеет смысл.
        int have  = 0;
        int left  = 0;

        // left/right постоянно двигаются и теряют историю —
        // resLeft/resRight хранят снимок лучшего найденного окна.
        // Нужны именно они — задача просит подстроку, не длину (не как в 209).
        int minLen  = Integer.MAX_VALUE;
        int resLeft = 0, resRight = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // Мусор — freqT[c]==0 → не пишем в freqW вообще.
            // Излишек пишем — right едёт только вправо, запасной экземпляр
            // нужен когда left уберёт старый. Иначе норму не закроем.
            if (freqT[c] > 0) {
                freqW[c]++;
                // have++ только в момент когда количество совпало с нормой:
                // freqW[c] < freqT[c]  → норма не закрыта  → пропуск
                // freqW[c] == freqT[c] → только что закрыли → have++
                // freqW[c] > freqT[c]  → излишек            → пропуск
                if (freqW[c] == freqT[c]) have++;
            }

            // Нельзя остановиться на первом валидном окне —
            // правее может быть короче.
            // Пример: "ADOBEC"(6) → "CODEBA"(6) → "BANC"(4) финальный ответ правее.
            // Сжимаем слева пока окно валидно, каждый раз обновляем минимум.
            while (have == need) {

                // Обновляем снимок если текущее окно лучше
                if (right - left + 1 < minLen) {
                    minLen   = right - left + 1;
                    resLeft  = left;
                    resRight = right;
                }

                char lc = s.charAt(left);

                // Мусор слева — не трогаем freqW.
                // Нужный символ потерял норму → have-- → while завершится → едем вправо.
                if (freqT[lc] > 0) {
                    freqW[lc]--;
                    if (freqW[lc] < freqT[lc]) have--;
                }

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(resLeft, resRight + 1);
    }

    public static void main(String[] args) {
        var sol = new MinimumWindowSubstring();

        // Базовый кейс → "BANC"
        // N — мусор, застрял между B и C физически
        System.out.println(sol.minWindow("ADOBECODEBANC", "ABC"));

        // Один символ → "a"
        System.out.println(sol.minWindow("a", "a"));

        // Не хватает дублей → ""
        // В s только одна 'a', в t нужно две
        System.out.println(sol.minWindow("a", "aa"));

        // Дубли в t → "AABB"
        // freqT: A=2, B=2, need=2 (не 4!)
        // A встречается 3 раза — излишек хранится, норма держится при сжатии
        System.out.println(sol.minWindow("AAABBC", "AABB"));

        // s == t → "ABC"
        System.out.println(sol.minWindow("ABC", "ABC"));

        // Мусор в середине → "BANC"
        // Первое валидное "ADOBEC"(6), финальное "BANC"(4)
        System.out.println(sol.minWindow("ADOBECODEBANC", "ABC"));
    }
}
