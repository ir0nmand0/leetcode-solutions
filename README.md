# LeetCode Solutions

Подготовка к алгоритмическим собеседованиям. Решения сгруппированы по паттернам.

## Прогресс

| # | Задача | Сложность | Паттерн | Решение |
|---|--------|-----------|---------|---------|
| 977 | Squares of a Sorted Array | Easy | Two Pointers | [Java](two-pointers/977-squares-of-sorted-array.java) |
| 167 | Two Sum II - Input Array Is Sorted | Medium | Two Pointers | [Java](two-pointers/167-two-sum-ii.java) |
| 15 | 3Sum | Medium | Two Pointers | [Java](two-pointers/15-3sum.java) |
| 11 | Container With Most Water | Medium | Two Pointers | [Java](two-pointers/11-container-with-most-water.java) |
| 42 | Trapping Rain Water | Hard | Two Pointers | [Java](two-pointers/42-trapping-rain-water.java) |

## Паттерны

- [Two Pointers](two-pointers/) — 5/8
- [Sliding Window](sliding-window/) — 0/5
- [Binary Search](binary-search/) — 0/5
- [HashMap / HashSet](hashmap/) — 0/5
- [Stack](stack/) — 0/5
- [Trees / BFS / DFS](trees/) — 0/5
- [Dynamic Programming](dp/) — 0/5
- [Greedy](greedy/) — 0/5

## План задач

Каждый паттерн — 5+ задач от простого к сложному. Каждая следующая добавляет одно усложнение поверх предыдущей. Дополнительные задачи — на закрепление подвидов паттерна.

Источники подборки:
- [Neetcode Roadmap](https://neetcode.io/roadmap)
- [Blind 75](https://www.teamblind.com/post/New-Year-Gift---Curated-List-of-Top-75-LeetCode-Questions-to-Save-Your-Time-OaM1orEU)
- [Grokking the Coding Interview](https://www.designgurus.io/course/grokking-the-coding-interview)

### Two Pointers
| # | Задача | Сложность | Навык | Статус |
|---|--------|-----------|-------|--------|
| 977 | Squares of a Sorted Array | Easy | Базовый Two Pointers, заполнение массива | done |
| 167 | Two Sum II | Medium | Сравнение суммы с target | done |
| 15 | 3Sum | Medium | Two Pointers внутри цикла + дедупликация | done |
| 11 | Container With Most Water | Medium | Жадная логика — двигай короткую стенку | done |
| 42 | Trapping Rain Water | Hard | Накопление состояния (максимумы слева/справа) | done |
| 125 | Valid Palindrome | Easy | Встречные указатели, пропуск символов | |
| 283 | Move Zeroes | Easy | Быстрый/медленный указатель, перезапись на месте | |
| 26 | Remove Duplicates from Sorted Array | Easy | Быстрый/медленный, дедупликация | |

### Sliding Window
| # | Задача | Сложность | Навык | Статус |
|---|--------|-----------|-------|--------|
| 643 | Maximum Average Subarray I | Easy | Базовое окно фиксированного размера | |
| 3 | Longest Substring Without Repeating Characters | Medium | Гибкое окно + HashSet | |
| 424 | Longest Repeating Character Replacement | Medium | Гибкое окно + подсчёт символов | |
| 567 | Permutation in String | Medium | Окно + частотный массив | |
| 76 | Minimum Window Substring | Hard | Гибкое окно + два счётчика | |

### Binary Search
| # | Задача | Сложность | Навык | Статус |
|---|--------|-----------|-------|--------|
| 704 | Binary Search | Easy | Базовый бинарный поиск | |
| 35 | Search Insert Position | Easy | Поиск позиции вставки | |
| 33 | Search in Rotated Sorted Array | Medium | Поиск в повёрнутом массиве | |
| 153 | Find Minimum in Rotated Sorted Array | Medium | Поиск минимума в повёрнутом массиве | |
| 4 | Median of Two Sorted Arrays | Hard | Бинарный поиск по двум массивам | |

### HashMap / HashSet
| # | Задача | Сложность | Навык | Статус |
|---|--------|-----------|-------|--------|
| 1 | Two Sum | Easy | Базовый HashMap — complement lookup | |
| 242 | Valid Anagram | Easy | Частотный подсчёт символов | |
| 49 | Group Anagrams | Medium | HashMap + ключ из сортировки | |
| 128 | Longest Consecutive Sequence | Medium | HashSet + поиск начала цепочки | |
| 560 | Subarray Sum Equals K | Medium | Prefix sum + HashMap | |

### Stack
| # | Задача | Сложность | Навык | Статус |
|---|--------|-----------|-------|--------|
| 20 | Valid Parentheses | Easy | Базовый стек — проверка скобок | |
| 155 | Min Stack | Medium | Стек + отслеживание минимума | |
| 150 | Evaluate Reverse Polish Notation | Medium | Стек для вычислений | |
| 739 | Daily Temperatures | Medium | Monotonic stack | |
| 84 | Largest Rectangle in Histogram | Hard | Monotonic stack + площадь | |

### Trees / BFS / DFS
| # | Задача | Сложность | Навык | Статус |
|---|--------|-----------|-------|--------|
| 104 | Maximum Depth of Binary Tree | Easy | Базовый DFS | |
| 226 | Invert Binary Tree | Easy | DFS + модификация дерева | |
| 102 | Binary Tree Level Order Traversal | Medium | BFS по уровням | |
| 98 | Validate Binary Search Tree | Medium | DFS + границы значений | |
| 236 | Lowest Common Ancestor | Medium | DFS + поиск в двух поддеревьях | |

### Dynamic Programming
| # | Задача | Сложность | Навык | Статус |
|---|--------|-----------|-------|--------|
| 70 | Climbing Stairs | Easy | Базовый DP — Fibonacci | |
| 198 | House Robber | Medium | DP с выбором — брать или пропустить | |
| 322 | Coin Change | Medium | DP на минимум — unbounded knapsack | |
| 300 | Longest Increasing Subsequence | Medium | DP на подпоследовательности | |
| 1143 | Longest Common Subsequence | Medium | 2D DP — две строки | |

### Greedy
| # | Задача | Сложность | Навык | Статус |
|---|--------|-----------|-------|--------|
| 121 | Best Time to Buy and Sell Stock | Easy | Отслеживание минимума | |
| 55 | Jump Game | Medium | Жадный максимум дальности | |
| 45 | Jump Game II | Medium | Жадный подсчёт прыжков | |
| 435 | Non-overlapping Intervals | Medium | Сортировка + жадный выбор интервалов | |
| 134 | Gas Station | Medium | Круговой обход + сброс старта | |

## Стек

Java 25
