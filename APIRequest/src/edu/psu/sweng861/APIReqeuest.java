package edu.psu.sweng861;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.*;
import com.google.gson.reflect.*;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public class APIReqeuest {

	public static Map<String, Object> mappingJson(String str){
		Map<String, Object> map = new Gson().fromJson(
				str, new TypeToken<HashMap<String, Object>>(){}.getType()
				);
		return map;
	}

	public static void main(String[] args) {
		// City Name set here to change for whole project
		String LOCATION = "boston";
		System.out.println("Presenting details of city: " + LOCATION.toUpperCase());
		System.out.println();
		
		//API key for NewsAPI
				String API_KEY_NEWS = "9e2a43ac9a004c7aa6108140c564513f";
				String urlStringNews = "https://newsapi.org/v2/everything?q="+ LOCATION +"&apiKey=" + API_KEY_NEWS;

				try {
					StringBuilder result = new StringBuilder();
					URL url = new URL(urlStringNews);
					URLConnection conn = url.openConnection();
					BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String line;
					while((line = rd.readLine()) != null) {
						result.append(line);
					}
					rd.close();
					System.out.println("Unformatted News Data");
					System.out.println(result);
					System.out.println("Formatted News Data");
					System.out.println("Top 5 News Headlines of "+ LOCATION);


					Map<String, Object> resMapNews = mappingJson(result.toString());
					List<Map<String, Object>> articles = (List<Map<String, Object>>) resMapNews.get("articles");

					int count = 0;
		            for (Map<String, Object> article : articles) {
		            	String title = (String) article.get("title");
		                String description = (String) article.get("description").toString();
		                if (description != null && title != null) {
		                	System.out.println("===============================================");
		                	System.out.println("Title " + (count + 1) + ": " + title);
		                    System.out.println("Description " + ": " + description);
		                    count++;
		                }

		                if (count >= 9) {
		                    break; // Print the top 5 descriptions
		                }
		            }
		            
				}catch(IOException e) {
					System.out.println(e.getMessage());
				}
		
		//API key for openWeatherAPI
		String API_KEY_WEATHER = "831b41fd17ab24bcb7ceccb4b2964887";
		String urlStringWeather = "https://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY_WEATHER + "&units=imperial";

		try {
			StringBuilder result = new StringBuilder();
			URL url = new URL(urlStringWeather);
			URLConnection connection = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while((line = reader.readLine()) != null) {
				result.append(line);
			}
			reader.close();


			Map<String, Object> resWeather = mappingJson(result.toString());
			Map<String, Object> mainRes = mappingJson(resWeather.get("main").toString());

			System.out.println("Unformatted Weather Data");
			System.out.println(result);
			System.out.println("Formatted Weather Data");
			System.out.println("-----------------------------");
			System.out.println("Weather in " + LOCATION);
			System.out.println();
			System.out.println("Current Temperature: " + mainRes.get("temp"));
			System.out.println("Current humidity: " + mainRes.get("humidity"));
			System.out.println("Minimum Temperature: " + mainRes.get("temp_min"));
			System.out.println("Minimum Temperature: " + mainRes.get("temp_max"));
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}

		//Scraping foxsports website for boston celetics upcoming events schedule
		//Boston Celetics team event schedule web-scraping URL
		final String url = "https://www.foxsports.com/nba/boston-celtics-team-schedule";

		try {
			final Document document = Jsoup.connect(url).timeout(9000).get();
			Elements body = document.select ("tbody.row-data.lh-1pt43.fs-14");
			System.out.println("-----------------------------");
			System.out.println("Unformatted Sports Scraping Data");
			System.out.println(body);
			System.out.println("Formatted Sports Scraping Data");
			System.out.println("Boston Celetics Upcoming Schedule:");
			System.out.println();
			for(Element e: body.select("tr")){
				String dateLocation = e.select("td.cell-text.ff-h div").text();
				System.out.print(dateLocation + ": with ");
				String opponents = e.select("td.cell-entity.fs-18.lh-1pt67 div").text();
				System.out.print(opponents + " @");
				String timeChannel = e.select("td.cell-time.broadcast.ff-h div").text();
				System.out.println(timeChannel);
			}
			System.out.println();
			System.out.println("-----------------------------");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}


	}

}
