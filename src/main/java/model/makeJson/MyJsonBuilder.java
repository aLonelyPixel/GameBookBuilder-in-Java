package model.makeJson;

import java.util.Set;

import javax.json.Json;
import javax.json.JsonArrayBuilder;

import model.Choice;
import model.GameBookInterface;
import model.Paragraph;

public class MyJsonBuilder implements JsonBuilder{

	@Override
	public String toJson(GameBookInterface gameBook) {
		return Json.createObjectBuilder()
				.add("Title", gameBook.getBookTitle())
				.add("Paragraphs", paragraphsToJson(gameBook))
				.build().toString();
	}

	private JsonArrayBuilder paragraphsToJson(GameBookInterface gameBook) {
		JsonArrayBuilder json = Json.createArrayBuilder();
		
		for (Paragraph paragraph : gameBook.getParagraphs()) {
			json.add(Json.createObjectBuilder()
					.add("Index", paragraph.getIndex())
					.add("Text", paragraph.getParagraphText())
					.add("Choices", choicesToJson(paragraph.getChoices())));
		}
		return json;
	}

	private JsonArrayBuilder choicesToJson(Set<Choice> choices) {
		JsonArrayBuilder json = Json.createArrayBuilder();
		
		for (Choice choice : choices) {
			json.add(Json.createObjectBuilder()
					.add("Choice text", choice.getText())
					.add("Destination paragraph", choice.getDestParagraph()));
		}
		return json;
	}
	
}
