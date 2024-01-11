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
* [Решение](src/main/java/ru/gb/ergakov/lesson1/seminar1/task2/Cart.java)

## Урок 2. Reflection API
### Задание № 1:
1. Создайте абстрактный класс "Animal" с полями "name" и "age".
2. Реализуйте два класса-наследника от "Animal" (например, "Dog" и "Cat") 
с уникальными полями и методами.
3. Создайте массив объектов типа "Animal" и с использованием Reflection API
4. выполните следующие действия:
   * Выведите на экран информацию о каждом объекте.
   * Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует.
* [Решение](src/main/java/ru/gb/ergakov/lesson2/Homework2/Program.java)

### Дополнительная задача:
Доработайте метод генерации запроса на удаление объекта из таблицы БД 
(DELETE FROM <Table> WHERE ID = '<id>')
В классе QueryBuilder который мы разработали на семинаре.
* [Решение](src/main/java/ru/gb/ergakov/lesson2/Seminar2/task2/QueryBuilder.java)

```java
//Class Program
   System.out.println("Homework:");
   String deleteQuery = queryBuilder.buildDeleteQuery(Employee.class, pk);
   System.out.println("Delete Query: " + deleteQuery);

//Class QueryBuilder
   /**
    * Построить запрос на удаление объекта из бд
    * @param clazz
    * @param primaryKey
    * @return
    */
   public String buildDeleteQuery(Class<?> clazz, UUID primaryKey){
           StringBuilder query = new StringBuilder("DELETE FROM ");
   
           if (clazz.isAnnotationPresent(Table.class)) {
               Table tableAnnotation = clazz.getAnnotation(Table.class);
               query.append(tableAnnotation.name()).append(" WHERE ");
               
               Field[] fields = clazz.getDeclaredFields();
               for (Field field : fields) {
                 if (field.isAnnotationPresent(Column.class)) {
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    if (columnAnnotation.primaryKey()) {
                       query
                             .append(columnAnnotation.name())
                             .append(" = '").append(primaryKey).append("'");
                       break;
                       }
                    }
                 }
               }
           else {
               return "";
           }
           return query.toString();
}
```
Вывод в терминал:
```shell
Homework:
Delete Query: DELETE FROM users WHERE id = '6a264242-3e5b-463c-bbdb-d6068b8d3e88'
```