/**Для заданного натурального числа определить, образуют ли его цифры арифметическую
 * прогрессию. Предполагается, что в числе не менее трёх цифр.
 */

/*Немного теории:
 * Натуральные числа - не отрицательные и не дробные (целые положительные + нуль)
 * 
 * Арифметическая прогрессия - это последовательность чисел, в которой каждый член получается из предыдущего 
 * путем прибавления к нему одного и того же числа d, называемого разностью этой арифметической прогрессии.
 * */

package lab1;

import java.util.Scanner;
import java.util.InputMismatchException;

public class task8 {
	
	private final static String line = "__".repeat(38);
	
	public static void main(String[] args) {
		
		try {
		
			var in = new Scanner(System.in);
			long numNature = 0;
		
			System.out.print ("\n Введите число. Оно должно:" 
								+" \n  * быть натуральным (целое и положительное)"
								+ "\n  * содержать не менее трёх цифр"
								+ "\n\nВаш ввод: "); 
		
			numNature = in.nextLong();
			in.close();
		
			if (numeralSum(numNature) < 3 || numNature < 0) 
				System.out.println (line + "\n\n Нарушены условия ввода числа\n" + line + "\n\n[Выход из программы]");
			else
				arithmeticProgressCheck(numeralSum(numNature), numToArray(numeralSum(numNature), numNature));
		}
		
		catch (InputMismatchException ex) {
			
			System.out.println(line + "\n\n Ошибка ввода. Это может быть вызвано тем, что: "
							 		+ "\n  * введённое вами число выходит за границы long"
							 		+ "\n  * вместо ожидаемого типа long был введён другой тип (String или double)\n" + line
							 		+ "\n\n [Выход из программы]");
		}
	}
	
	
	/**
	 * @function numeralSum cчитает количество цифр в введённом числе
	 * @param введённое пользователем натуральное число
	 * @return число numSum, которое содержит количество цифр в введённом числе
	 * */
	public static int numeralSum(long value) {	
		
		int numSum;	
		
		for (numSum = 1; value >= 10; numSum++)
			value /= 10;
		
		return numSum;
	}
	
	/**
	 * @function numToArray загружает введённое пользователем число в массив. 
	 * Модификатор доступа private, так как является вспомогательной функцией для arithmeticProgressCheck 
	 * @param numSum - количество цифр в введённом числе, value - введённое пользователем число, которое будем проверять на прогрессию
	 * @return numArr - введённое пользователем натуральное число, помещённое в массив
	 * */
	private static long[] numToArray (int numSum, long value) {
		
		var numArr = new long [numSum];
		
		for (int i = numSum -1; i >= 0; i--) {
			
			numArr[i] = value % 10;
			value /= 10;
		}
		
		return numArr;
	}
	
	/**
	 * @function arithmeticProgressCheck проверяет массив, полученный в numToArray, на арифметическую прогрессию 
	 * @param numSum - количество цифр в введённом числе, numArr - массив, полученный в numToArray
	 * */
	public static void arithmeticProgressCheck (int numSum, long[] numArr) {
		
		int i = 0;
		long d = numArr[i] - numArr[i + 1];	//Разность арифметической прогрессии
		boolean checkFlag = false;
		
		for (i = 1; i <= numSum - 2; i++) {
			
			if (numArr[i] - numArr[i + 1] == d) checkFlag = true;
			else {
				checkFlag = false;
				break;
			}
		}
		
		if (checkFlag) 
			System.out.println("\nВведённое число является арифметической прогрессией" 
								+"\n Разность арифметической прогрессии d = " + -d 
								+ "\n\n [Выход из программы]");
		
		else System.out.println("\nВведённое число не является арифметической прогрессией"
								+ "\n\n [Выход из программы]");
	}
	
}