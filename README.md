# GB Java Junior
## Урок 1. Лямбды и Stream API
### Задание № 1:
Напишите программу, которая использует Stream API 
для обработки списка чисел. 
Программа должна вывести на экран среднее значение 
всех четных чисел в списке.
* [Решение](src/main/java/ru/gb/ergakov/lesson1/homework1/Homework1Task1.java)

```java
public class Homework1Task1 {

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        numbers.stream()
                .filter(n -> n % 2 == 0)
                .mapToDouble(Integer::intValue)
                .average()
                .ifPresent(System.out::println);
    }
}
```

### Задание № 2 (Дополнительное):
Переработать метод балансировки корзины товаров 
cardBalancing() с использованием Stream API
