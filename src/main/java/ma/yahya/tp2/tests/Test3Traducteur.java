package ma.yahya.tp2.tests;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;

import java.util.Map;

public class Test3Traducteur {
    public static void main(String[] args) {
        // Récupération de la clé Gemini depuis la variable d'environnement
        String cle = System.getenv("GEMINI_KEY");

        // Création du modèle Gemini avec le builder
        ChatModel modele = GoogleGenAiChatModel.builder()
                .apiKey(cle)
                .modelName("gemini-3.5-flash")
                .temperature(0.7)
                .build();

        // Création d'un template avec une seule variable : {{it}}
        PromptTemplate template =
                PromptTemplate.from("Traduis en anglais : {{it}}");

        // Remplacement de {{it}} par la phrase à traduire
        Prompt prompt = template.apply("Bonjour, comment vas-tu ?");

        // Envoi du prompt au modèle et affichage de la traduction
        String reponse = modele.chat(prompt.toUserMessage()).aiMessage().text();
        System.out.println(reponse);

        // Création d'un template avec deux variables : {{langue}} et {{texte}}
        PromptTemplate template2 =
                PromptTemplate.from("Traduis en {{langue}} : {{texte}}");

        // Association de chaque variable à sa valeur
        Prompt prompt2 = template2.apply(Map.of(
                "langue", "anglais",
                "texte", "Je suis étudiant."
        ));

        // Envoi du second prompt au modèle et affichage de la traduction
        String reponse2 = modele.chat(prompt2.toUserMessage()).aiMessage().text();
        System.out.println(reponse2);
    }
}