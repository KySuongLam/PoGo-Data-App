package POGOproject;

import java.util.ArrayList;
import java.util.Scanner;

/* 
 * This program is written with the aim of helping users to see the information 
 * of any Pokemon they want in real-time data. It could show the user 
 * the ID, generation, types, etc. of the Pokemon that the user entered.
 */

public class MyAPP 
{

	static Scanner input = new Scanner(System.in);
	static int need;
	private static ArrayList<String> PokeInfo = new ArrayList<>();
	
	public static void main(String[] args) throws Exception 
	{
		System.out.println("Welcome to My POGO information app");
		System.out.println();

		userNeed();
		getPokeInfo();
		
	}
	
	public static void userNeed() throws Exception
	{
		boolean valid = false;
		while (valid == false) 
		{
			//Ask users what info they looking for
			System.out.println("What information do you looking for?");
			System.out.println("1) Pokemon ID in the pokedex");
			System.out.println("2) Pokemon's Generation");
			System.out.println("3) Pokemon type of a specific Pokemon");
		
			System.out.println("Please enter 1, 2, or 3:");
		
		
			need = input.nextInt();
			System.out.println();
		
		
			if (need == 1 | need == 2)
			{
				IdAndGen.LookInfo();
				valid = true;
			}
			else if (need == 3)
			{
				PokeType.LookInfo();
				valid = true;
			}
			else 
			{
				System.out.println("Invalid input");
				System.out.println("Please choose again, thank you!");
				System.out.println("--------------------------------");
			}
		} 
	}
	
	
	public static void getPokeInfo() throws Exception
	{
		
		if (need == 1)
		{
			PokeInfo = IdAndGen.getPokeInfo();
			System.out.println(PokeInfo.get(0));
		}
		else if (need == 2)
		{
			PokeInfo = IdAndGen.getPokeInfo();
			System.out.println(PokeInfo.get(1));
		}
		else if (need == 3)
		{
			PokeInfo = PokeType.getPokeInfo();
			
			for (int i = 0; i < PokeInfo.size(); i++)
			{
				System.out.print(PokeInfo.get(i));
				
				if (0 < i && i < PokeInfo.size()-1 ) 
				{
	                System.out.print(", ");
	            }
				else if (i == PokeInfo.size()-1)
				{
					System.out.print(".");
				}
			}
		}
	}
}
