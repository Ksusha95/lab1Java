/**
 * Программа запрашивает 10 строк, отсортировать их по алфавиту (в строках могут содержаться числа, которые необходимо отсортировать в порядке возрастания).
 * */

package lab1;

import java.util.Scanner;	//Для работы с вводом
import java.util.Arrays;	//Для работы с массивами

public class task2 {

	public static void main(String [] args) {
			
			var strArray = new String[10];	//Создаём массив из 10 элементов
			Scanner sc = new Scanner(System.in);	//Для ввода строк в массив
			
			System.out.print("\nВведите 10 строк, которые нужно отсортировать:\n");	
			
			for (int i = 0; i < strArray.length; i++) {
				strArray[i] = sc.nextLine();	//Считываем 10 строк в массив strArr
			}
			
			sortArray(strArray);	//Передаём массив в функцию для сортировки
			
			sc.close();
			
		
		}
	
	
	static void sortArray(String[] sArr) {	// Сортировка массива + оформление результата
		
		System.out.println ("\nДан массив со строками:\n\n"
				+ Arrays.toString(sArr)); 	// Arrays.toString(strArray) - простой и быстрый способ вывести массив на печать
		
		Arrays.sort(sArr);	//В Java есть готовая функция сортировки - берём её и самим ничего писать не надо
		
		System.out.println ("\nОтсортированный массив:\n");
		//Тут печать массива сложнее, потому что я хочу каждую строчку вывести с новой строки
		for (String i : sArr)
			System.out.println(i);
		
	}
}
	