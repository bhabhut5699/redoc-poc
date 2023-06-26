package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.spell.LevenshteinDistance;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;

@EnableWebMvc
@EnableSwagger2
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

//public static String suggestCorrectWord(String input, String[] validWords) throws IOException {
//	Directory directory = new RAMDirectory();
//	SpellChecker spellChecker = new SpellChecker(directory);
//
//	// Index the valid words
//	spellChecker.indexDictionary(new org.apache.lucene.search.spell.PlainTextDictionary(new java.io.StringReader(String.join("\n", validWords))), new IndexWriterConfig(), true);
//
//	// Set the Levenshtein distance for suggestions
//	LevenshteinDistance distance = new LevenshteinDistance();
//
//	// Set the maximum number of suggestions
//	int maxSuggestions = 1;
//
//	// Get the closest suggestion
//	String[] suggestions = spellChecker.suggestSimilar(input, maxSuggestions);
//
//	// Return the first suggestion (closest match)
//	return suggestions.length > 0 ? suggestions[0] : null;
//}
//
//	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
//		String input = "bonnus";
//		String[] validWords = {"bonds", "bonds", "correct", "spelling", "example", "bonus", "bonus" };
//
//		try {
//			String corrected = suggestCorrectWord(input, validWords);
//			System.out.println("Original: " + input);
//			System.out.println("Corrected: " + corrected);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}




}

