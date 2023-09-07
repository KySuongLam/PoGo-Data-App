package POGOproject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/* 
 * This program is written with the aim of helping users to see the information 
 * of any Pokemon they want in real-time data. It could show the user 
 * the ID, generation, types, etc. of the Pokemon that the user entered.
 */

public class PokeType 
{
	private static ArrayList<String> PokeInfo = new ArrayList<>(); //to send the Poke info.
	//static String pokeType;
	
	
	public static void LookInfo() throws Exception
	{
		String pokeName;
		
		String theURL = "https://pogoapi.net/api/v1/pokemon_types.json";	
		URL url = new URL(theURL);
		///Reads information from URL    
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		//JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();
		//Read JSON file. All the data is stored in “myObject"
		JSONArray myArray = (JSONArray)jsonParser.parse(br); 
		
		System.out.println("What is the Pokemon name?");
		Scanner scan = new Scanner(System.in);
		String userInput = scan.nextLine();	
		
		boolean correctAnswer = false;
		
		for (int i = 0; i < myArray.size(); i++)
		{
			JSONObject x = (JSONObject) myArray.get(i);
			pokeName = (String) x.get("pokemon_name");
			if (pokeName.equalsIgnoreCase(userInput)) 
			{
				PokeInfo.add("The Pokemon type(s): ");
				PokeInfo.addAll((ArrayList<String>)x.get("type"));
				correctAnswer = true;
				break;
			}
		}
		
		if (correctAnswer == false)
		{
			PokeInfo.add("Invad Pokemon name " + "\nOR Pokemon's data has not been updated");

		}
	}
	
	public static ArrayList <String> getPokeInfo()
	{
		return PokeInfo;
	}	
}
