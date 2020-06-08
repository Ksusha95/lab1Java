/**
 * Пользователь загадывает число от 1 до 100. (включительно) Программа должна угадать (за минимальное
 * число шагов), какое число было загадано, и распознать ситуацию, когда пользователь
 * «обманывает» программу.
 * 
 * Программа в бесконечном цикле, когда надоест угадывать - введите "в" и она закончится  
 * 
 * @author Ksussha
 *   
 */

package lab1;	//Название пакета меняешь на своё

import java.util.Scanner;

public class task1 {	//Название класса меняешь на своё
	
	static String line = "--".repeat(35);	  //Для красоты
	static String choose;		//Выбор да/нет/... в программе и функциях, поэтому глобальная
	static Scanner in = new Scanner(System.in); //Подключаем ввод
			
	
	public static void main(String[] args) { 
		
		String endline = "__".repeat(35); //Для красоты x2
		//Программа в бесконечном цикле, пока restart = true. При restart = false программа завершается
		boolean restart = true;	
		
		while(restart) { 	
		
				System.out.print(endline + "\n" + line + "\nВы загадали число от 1 до 100? [Д/в]:_ ");
				choose = in.nextLine();	
			
				switch (choose.toLowerCase()) {	//Чтобы поддерживался ввод и строчной, и прописной букв
					case "д":
						System.out.println ("Отлично! Я начинаю угадывать\n" + line);
						//Функция угадывания числа
						guessNumb();
					break;
		
					case "в":
						System.out.println (endline + "\nВыход из программы");
						restart = false;
					break;
				
					default:
						System.out.println ("\n ! Требуется ввести Д или в");
					break;
				}
			}
		}
	
	/**
	 * @function guessNumb угадывает число с помощью алгоритма двоичного поиска
	 * Функция ничего не возвращает, так как все расчёты проводятся в ней же
	 * */
	public static void guessNumb() {	
		
		int x = 50, uplim = 100, lowlim = 0;
		
		boolean guessingSuccess = false;
		
		while (uplim - lowlim  > 1) {
			System.out.printf("\nВаше число > %d? [Д/н/р]_: ", x);
			choose = in.nextLine();
			
			switch (choose.toLowerCase()) {
				case "д":
					lowlim = x;
					x = binSearch(uplim, lowlim);
				break;
				
				case "н":
					uplim = x;
					x = binSearch(uplim, lowlim);
				break;
			
				case "р":
					System.out.printf("\n*** Было загадано число %d! ***\n", x);
					guessingSuccess = true;
				return;
			
				default:
					System.out.printf("\n! Неверный формат ответа \n\n");
				break;
			} 
		}
		
		if (guessingSuccess == false) 
			checkDeception(uplim);	//Проверка на обман
		
	}
	
	/**
	 * @function binSearch реализует алгоритм двоичного поиска загаданного числа
	 * @param область значений [lowlim, uplim], в которой предположительно находится загаданное пользователем число
	 * @return вычисление нового предела, С или ДО которого может находиться загаданное число 
	 * */
	public static int binSearch (int uplim, int lowlim) {	
		
		int x = (uplim + lowlim)/2;
		System.out.printf("Ваше число находится на промежутке от %d до %d\n" + line, lowlim, uplim);
		
		return x;
	}
	
	/**
	 * @function checkDeception производит проверку на обман (когда из всего диапазона вариантов осталось единственное число)
	 * @param единственное оставшееся из всего промежутка возможных чисел, которое мог загадать пользователь, число finishVal
	 * Функция ничего не возвращает, проверка на обман происходит в ней же
	 * */
	public static void checkDeception (int finishVal) {
		
		System.out.printf("\nВаше число %d? [Д/н]:_ ", finishVal);
		choose = in.nextLine();
	
		if(choose.toLowerCase().equals("д")) 
			System.out.printf("\n*** Было загадано число %d! ***\n", finishVal);

		else if(choose.toLowerCase().equals("н")) 
			System.out.printf("Ошибка! В выбранном диапазоне больше нет доступных чисел\n");
		
		else {
			System.out.printf("\n! Неверный формат ответа \n\n");
			checkDeception(finishVal);
		}
	}
		
}
