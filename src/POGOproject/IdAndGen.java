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

public class IdAndGen 
{
	private static ArrayList<String> PokeInfo = new ArrayList<>(); //to send the Poke info.
	static Long id, gen;
	static String stringId, stringGen;
	
	public static void LookInfo() throws Exception 
	{
		String pokeName;
		
		String theURL = "https://pogoapi.net//api/v1/pokemon_generations.json";	
		URL url = new URL(theURL);
		///Reads information from URL    
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		//JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();
		//Read JSON file. All the data is stored in “myObject"
		JSONObject myObject = (JSONObject)jsonParser.parse(br); 
		
		System.out.println("What is the Pokemon name?");
		Scanner scan = new Scanner(System.in);
		String userInput = scan.nextLine();	
		
		boolean correctAnswer = false;
		boolean pokeInGen = false;
	
		for (int i=1; i < 10; i++)
		{
			if (pokeInGen == false) 
			{	
				JSONArray GenArray = (JSONArray) myObject.get("Generation " + i);	
				JSONObject x;
					
				for (int e = 0; e < GenArray.size(); e++)
				{
					if ( correctAnswer == false)
					{
						x = (JSONObject) GenArray.get(e);
						pokeName = (String) x.get("name");
							
						if (pokeName.equalsIgnoreCase(userInput))
						{
							id = (Long) x.get("id");	
							stringId = id.toString();
							PokeInfo.add("Pokemon ID in the pokedex is " + stringId);   
						
							gen = (Long) x.get("generation_number");
							stringGen = gen.toString();
							PokeInfo.add("Pokemon is in the Generation number " + stringGen);
							correctAnswer = true;
							pokeInGen = true;
						}
					} 
					else 
					{
						break;
					}
				}
			}
			else
			{
				break;
			}
		}
		
	// If the user enters the incorrect Pokemon name 
	// or the Pokemon has not been updated in data.
		if (correctAnswer == false)
		{
			PokeInfo.add("Cannot find the id of this Pokemon.");
			PokeInfo.add("Cannot find this pokemon in any generation.");
			System.out.println("Pokemon no exit,");
		}
	}
	
	public static ArrayList <String> getPokeInfo()
	{
		return PokeInfo;
	}			
}
