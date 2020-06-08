/**
 * Определить, является ли введенное пользователем число палиндромом, в случае положительного ответа 
 * предусмотреть проверку на принадлежность к классу палиндромов квадрата данного числа.
 */

package lab1;

import java.util.Scanner;	//Подключаем ввод
import java.util.InputMismatchException; //Когда Scanner получает не тот формат переменной, которую ожидал

public class task7 {
	
	/*Как будем решать задачу:
	 *  1) Инвертируем введённое число
	 *  2) Сравниваем начальное число с инвертированным
	 *  	2.1) Если они равны - это палиндром
	 *  		2.1.1) Проверяем квадрат данного числа
	 *  	2.2) Если не равны, то пишем "Не палиндром" и сворачиваемся
	 *  3) Обрабатываем исключения. Тут может быть 2 исключения:
	 *  	3.1 Пользователь вводит строку вместо числа 
	 *  		За это отвечает исключение InputMismatchException, подключаем его и обрабатываем
	 *  	3.2 Пользователь вводит число, размер которого выходит за рамки значений типа long
	 *  		За это также отвечает исключение InputMismatchException
	 *  4) ?????
	 *  5) PROFIT
	 */
	
	public static void main (String [] args) {
		
		//Введённое пользователем число, которое будем проверять на палиндром
		long value = 0; 
		//Подключаем ввод
		var in = new Scanner(System.in);	
		boolean restart = true;
			
		while(restart) {
			try {
			
				System.out.print ("\nВведите число: ");
				value = in.nextLong();	//Scanner ожидает получить значение типа long
			
				if (value != reverse(digitSum(value), value) || digitSum(value) == 1) System.out.println("\nЭто не палиндром.");	//Одна цифра также не является палиндромом 
			
				else {
					
					System.out.println("\nЭто палиндром. Приступаю к проверке на палиндром квадрата данного числа");
				
					long valueSquared = (long)Math.pow(value, 2); //Квадрат введённого пользователем числа, которое также будем проверять на палиндром
				
					if (longLimit(valueSquared)) System.out.println("Квадрат введённого вами числа выходит за границы long - проверка на палиндром невозможна");
					else {
						
						System.out.println("Квадрат введённого вами числа " + value + " = " + valueSquared );
						if (valueSquared == reverse(digitSum(valueSquared), valueSquared)) System.out.println("Квадрат данного числа является палиндромом");
						else System.out.println("Квадрат данного числа не является палиндромом");
				
					}
				}
		
		}	//Конец блока try
		
		catch (InputMismatchException ex) {		//Как программе нужно себя повести, если выблевалось исключение
			
			System.out.println("\nОшибка ввода (неверный формат ввода или слишком большое значение числа для типа long)");
		}
		
		finally {
			
			//Вызов функции продолжения программы
			restart = continueWhile(restart);
			
			@SuppressWarnings("unused")
			var buf = in.nextLine();		//Поглощение переноса строки
		}
			
	} //Конец while
		
		in.close();
	}
	
	/**
	 * @function digitSum cчитает количество цифр в введённом числе
	 * @param введённое пользователем число value, которое будем проверять на палиндром
	 * @return число digSum, которое содержит количество цифр в введённом числе
	 * */
	public static int digitSum(long value) {	
		
		int digSum;	
		
		for (digSum = 1; value >= 10; digSum++)
			value /= 10;
		
		return digSum;
	}
	
	/**
	 * @function reverse инвертирует введённое число
	 * @param количество цифр в введённом числе digSum, введённое пользователем число value, которое будем проверять на палиндром
	 * @return число valueReverse, которое является реверсом числа value
	 * */
	public static long reverse(int digSum, long value) {
		
		long valueReverse = 0;	
		
		for (; digSum > 0; digSum--) {
			long num = value % 10;
			value /= 10;
			valueReverse += (long)(num * Math.pow(10,digSum-1));
		}
		
		return valueReverse;
	}
	
	/**
	 * @function longLimit производит проверку выхода квадрата введённого пользователем числа за границы long
	 * @param переменная sguare, равная квадрату введённого пользователем числа value, которую также будем проверять на палиндром
	 * @return значение limFlag, где false - если квадрат данного числа не выходит за границы long, true - если выходит
	 * */
	public static boolean longLimit (long square) {		
		
		boolean limFlag = false; 
		double lim = (long)9223372036854775807.;
		
		if (square >= lim) 
			limFlag = true;
		
		return limFlag;
	}
	
	/**
	 * @function continueWhile производит проверку продолжения программы, исходя из ответа пользователя
	 * @param булевая переменная restart(по умолчанию true)
	 * @return значение restart, где true - продолжить выполнение программы, false - завершить
	 * */
	public static boolean continueWhile(boolean restart) {
		
		var sc = new Scanner(System.in);
		String choose;
	
		System.out.print ("\nПовторить? [Д/н]:_");
		choose = sc.next();
		
		if (choose.toLowerCase().equals("н")) {
			restart = false;
			System.out.print ("\nВыход");
			sc.close(); 	
		}
		
		else if (choose.toLowerCase().equals("д")) 
			;
		
		else {
			System.out.print ("\nОшибка ввода");
			restart = continueWhile(restart);
		}
	
		return restart;
	}
	
}