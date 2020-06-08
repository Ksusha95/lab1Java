/** Написать программу, которая по заданной дате (числу d и месяцу m) определяет число
 * дней, прошедших от начала года, если известно, что год - не високосный.
 * 
 * UPD: (На карантине скучно) Пользователь вводит год, и программа определяет, високосный ли он 
 * и действует, исходя из результата на високосность
 * 
 * Пример ввода: 01 03
 * (1 марта)
 */

package lab1;

import java.util.Scanner;
import java.util.InputMismatchException; //Исключение, когда Scanner получает переменную не того формата, какого ожидал

public class task4 {
	
	static String endline = "__".repeat(25); //Для красивости
	static String line = "--".repeat(25);		//Для красивости х2
	
	public static class Date {		
		
		private int day; 	
		private int month;
		private int year;
		
		public Date (int day, int month, int year) {  
			
			this.day = day;		
			this.month = month;
			this.year = year;
		}
		
		public int calcDays (int d1, int m1, boolean flag) {  //Вычисление дней
			
			d1 = d1 - 1; //Вычитаем из введённых дней 1-й день года
			
			/*
			30 дней в четырех месяцах года: в апреле, июне, сентябре и ноябре
			31 день в семи месяцах года: в январе, марте, мае, июле, августе, октябре и декабре
			*/
			
			for (int i = 1; i < m1; i++) {	
				
				if (i == 4 || i == 6 || i == 9 || i == 11) 
					d1 = d1 + 30;
				
				else if (i == 2 && flag) 
					d1 = d1 + 29;
				
				else if (i == 2 && !flag) 
					d1 = d1 + 28;
				
				else {
					d1 = d1 + 31;
				}
			}
			
			return d1;
		}
		
		//Вывод на печать даты
		void printDate () {  
			
			if (day < 10) {	
				if (month < 10)
					System.out.printf ("0%d.0%d.%d\n", day, month, year);
				else System.out.printf ("0%d.%d.%d\n", day, month, year);
			}
			else {
				if (month < 10)
					System.out.printf ("%d.0%d.%d\n", day, month, year);
				else
					System.out.printf ("%d.%d.%d\n", day, month, year);	
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in); //Подключаем ввод
		
		Date startDate;	//Начальная дата
		Date enterDate;	//Дата, которую введёт пользователь
		
		int year = 0;	
		
		try {
			System.out.print("Введите год:_ ");
			year = in.nextInt();
			boolean flag = leapYear(year);	//Проверка на високосность
		
			startDate = new Date (1, 1, year);  //Инициализация начальной даты
		
		
			System.out.println("\nНачальная дата: ");
			startDate.printDate();
			System.out.println(endline);
		
			System.out.print("Введите число и месяц:_ ");
			enterDate = new Date(in.nextInt(), in.nextInt(), year);	
			in.close(); 
		
			if (enterDate.day > 31 || enterDate.month > 12) {
				System.out.println("!!! Неверный формат введённой даты ");	
			}
		
			else {
				System.out.println(line + "\nВведённая дата: ");
				enterDate.printDate();
				System.out.printf(endline + "\nЧисло дней, прошедших от начала года: %d\n", enterDate.calcDays(enterDate.day, enterDate.month, flag));
			}
		}
		
		catch (InputMismatchException ex) {		
			System.out.println ("\nОшибка ввода");
		}
	}
	
	/*Алгоритм определения високосности года:
	 * 
	 * Год високосный, если он делится на четыре без остатка, но если он делится на 100 без остатка, это не високосный год.
	 * Однако, если он делится без остатка на 400, это високосный год.
	 * 
	 */
	
	static boolean leapYear (int year) {   //Проверка на високосность
		
		boolean flag = false; // true - если год високосный, false - если обычный
		
		if (year % 4 == 0 || (year % 100 != 0 && year % 400 == 0))
		    flag = true;
		
		if (flag) 
			System.out.printf(endline + "\nГод %d является високосным\n" + line, year);	
		else 
			System.out.printf(endline + "\nГод %d не является високосным\n" + line, year);
		
		return flag;
	}
	
}
