/* В игре в пьяницу карточная колода раздается поровну двум игрокам. Далее они
вскрывают по одной верхней карте, и тот, чья карта старше, забирает себе обе вскрытые
карты, которые кладутся под низ его колоды. Тот, кто остается без карт – проигрывает.
Для простоты будем считать, что все карты различны по номиналу, а также, что самая
младшая карта побеждает самую старшую карту ("шестерка берет туза").
Игрок, который забирает себе карты, сначала кладет под низ своей колоды карту первого
игрока, затем карту второго игрока (то есть карта второго игрока оказывается внизу
колоды).
Напишите программу, которая моделирует игру в пьяницу и определяет, кто выигрывает.
В игре участвует 10 карт, имеющих значения от 0 до 9, большая карта побеждает
меньшую, карта со значением 0 побеждает карту 9.
Формат входных данных
Программа получает на вход две строки: первая строка содержит 5 карт первого игрока,
вторая – 5 карт второго игрока. Карты перечислены сверху вниз, то есть каждая строка
начинается с той карты, которая будет открыта первой.
Формат выходных данных
Программа должна определить, кто выигрывает при данной раздаче, и вывести
слово first или second или любое другое, после чего вывести количество ходов, 
сделанных до выигрыша.
Если на протяжении 1001 3 4 7 9
2 4 6 8 00000 ходов игра не заканчивается, программа должна вывести
словоbotva.
*/

import java.util.LinkedList;
import java.util.Scanner;

public class Task4 {
    // Условие, определяющее, чья карта сильнее
    public static boolean beats(int first, int second) {
        return (first > second && first != 9 && second != 0 || first == 0 && second == 9);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Игроки представлены в виде связанных списков
        LinkedList<Integer> first = new LinkedList<>();
        LinkedList<Integer> second = new LinkedList<>();
        System.out.println("Введите 5 карт первого игрока:");
        for (int i = 0; i < 5; i++)
            first.addLast(sc.nextInt());
        System.out.println("Введите 5 карт второго игрока:");
        for (int i = 0; i < 5; i++)
            second.addLast(sc.nextInt());
        int count = 0;
        while (!first.isEmpty() && !second.isEmpty()) {
            if (beats(first.get(0), second.get(0))) {
                second.addLast(first.get(0));
                second.addLast(second.get(0));
                first.remove(0);
                second.remove(0);
            } else {
                first.addLast(first.get(0));
                first.addLast(second.get(0));
                first.remove(0);
                second.remove(0);
            }
            if (count++ == 1000000) {
                System.out.println("botva");
                return;
            }
        }
        System.out.println("Результат игры:");
        System.out.println((first.isEmpty() ? "first" : "second") + " " + count);
    }
}
