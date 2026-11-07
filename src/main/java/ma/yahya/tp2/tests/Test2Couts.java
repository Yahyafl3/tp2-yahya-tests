package ma.yahya.tp2.tests;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import dev.langchain4j.model.output.TokenUsage;

public class Test2Couts {
    public static void main(String[] args) {
        String cle = System.getenv("GEMINI_KEY");

        // Création du modèle avec un builder
        ChatModel modele = GoogleGenAiChatModel.builder()
                .apiKey(cle)
                .modelName("gemini-3.5-flash")
                .temperature(0.7)
                .build();

        // Création de la question comme message utilisateur
        ChatMessage question =
                UserMessage.from("Quelle est la capitale du Maroc ?");

        // Envoi de la question et récupération de la réponse avec les statistiques de tokens
        ChatResponse reponse = modele.chat(question);

        // Affichage de la réponse du modèle
        System.out.println("Réponse : " + reponse.aiMessage().text());

        // Récupération du nombre de tokens utilisés
        TokenUsage usage = reponse.tokenUsage();

        int tokensEntree = usage.inputTokenCount();
        int tokensSortie = usage.outputTokenCount();

        System.out.println("Tokens en entrée : " + tokensEntree);
        System.out.println("Tokens en sortie : " + tokensSortie);

        // Tarifs gemini-3.5-flash-lite : 0,30 $ en entrée et 2,50 $ en sortie par million de tokens.
        double cout = (tokensEntree * 0.30 + tokensSortie * 2.50) / 1_000_000;

        // Calcul du nombre de requêtes similaires pour atteindre environ 1 dollar
        System.out.println("Coût estimé avec les tarifs Flash-Lite : " + cout + " $");

        if (cout > 0) {
            System.out.println("Nombre de requêtes similaires pour 1 $ : " + (1 / cout));
        }
    }
}