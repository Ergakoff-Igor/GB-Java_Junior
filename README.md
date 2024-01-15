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

## Урок 3. Сериализация
### Задание 1:
1. Разработайте класс Student с полями 
   * String name, 
   * int age, 
   * transient double GPA (средний балл).
2. Обеспечьте поддержку сериализации для этого класса.
```java
public class Student implements Serializable {
   private String name;
   private int age;
   transient double GPA;

   public String getName() {
      return name;
   }

   public int getAge() {
      return age;
   }

   public Student(String name, int age, double GPA) {
      this.name = name;
      this.age = age;
      this.GPA = GPA;
   }

   public Student() {
   }

   @Override
   public String toString() {
      return "Student{" +
              "name='" + name + '\'' +
              ", age=" + age +
              ", GPA=" + GPA +
              '}';
   }
}
```
3. Создайте объект класса Student и инициализируйте его данными.
4. Сериализуйте этот объект в файл.
```java
public class Program {
    public static final String FILE_BIN = "students.bin";
    public static void main(String[] args) {

       Student student = new Student("Igor", 32, 6.5);
       System.out.println("Создан экземпляр -> " + student);

       try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_BIN))) {
          oos.writeObject(student);
          System.out.println("Сериализация выполнена успешно!");
       } catch (IOException e) {
          throw new RuntimeException(e);
       }
    }
}
```
5. Десериализуйте объект обратно в программу из файла.
```java
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_BIN))) {
            student = (Student) ois.readObject();
            System.out.println("Десериализация выполнена успешно!");
            System.out.println(student);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
```
6. Выведите все поля объекта, включая GPA.

Вывод в терминал:
```shell
Создан экземпляр -> Student{name='Igor', age=31, GPA=6.5}
Сериализация выполнена успешно!
Десериализация выполнена успешно!
Student{name='Igor', age=31, GPA=0.0}
```
7. Ответьте на вопрос, почему значение GPA не было сохранено/восстановлено.

Значение поля GPA не было сохранено потому, что использовался модификатор "transient"
```java
transient double GPA;
```

### Задание 2 (Дополнительное):
Выполнить задачу 1 используя другие типы сериализаторов 
(в xml и json документы).