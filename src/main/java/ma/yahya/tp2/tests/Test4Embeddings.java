package ma.yahya.tp2.tests;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.bgesmallenv15.BgeSmallEnV15EmbeddingModel;
import dev.langchain4j.model.google.genai.GoogleGenAiEmbeddingModel;
import dev.langchain4j.model.google.genai.GoogleGenAiEmbeddingModel.TaskTypeEnum;
import dev.langchain4j.store.embedding.CosineSimilarity;

import java.time.Duration;

public class Test4Embeddings {
    public static void main(String[] args) {
        // Création du modèle d'embeddings local
        EmbeddingModel modeleEmbedding = new BgeSmallEnV15EmbeddingModel();

        // Création du modèle d'embeddings Google
        String cle = System.getenv("GEMINI_KEY");

        EmbeddingModel modeleGoogle = GoogleGenAiEmbeddingModel.builder()
                .apiKey(cle)
                .modelName("gemini-embedding-001")
                .taskType(TaskTypeEnum.SEMANTIC_SIMILARITY)
                .timeout(Duration.ofSeconds(30))
                .build();

        // Comparaison des six couples avec BGE
        System.out.println("=== BGE ===");

        comparer(modeleEmbedding,
                "A cat is sleeping on the sofa.",
                "A kitten is resting on the couch.");

        comparer(modeleEmbedding,
                "A dog is running in the park.",
                "A puppy is playing outdoors.");

        comparer(modeleEmbedding,
                "The train arrives at noon.",
                "The train reaches the station at midday.");

        comparer(modeleEmbedding,
                "The shop is open today.",
                "The shop is closed today.");

        comparer(modeleEmbedding,
                "A doctor is treating a patient.",
                "A rocket is flying to Mars.");

        comparer(modeleEmbedding,
                "She is baking bread.",
                "The football match ended in a draw.");

        // Comparaison des mêmes couples avec Google
        System.out.println("=== Google ===");

        comparer(modeleGoogle,
                "A cat is sleeping on the sofa.",
                "A kitten is resting on the couch.");

        comparer(modeleGoogle,
                "A dog is running in the park.",
                "A puppy is playing outdoors.");

        comparer(modeleGoogle,
                "The train arrives at noon.",
                "The train reaches the station at midday.");

        comparer(modeleGoogle,
                "The shop is open today.",
                "The shop is closed today.");

        comparer(modeleGoogle,
                "A doctor is treating a patient.",
                "A rocket is flying to Mars.");

        comparer(modeleGoogle,
                "She is baking bread.",
                "The football match ended in a draw.");
    }

    // Calcule et affiche la similarité entre deux phrases
    private static void comparer(
            EmbeddingModel modele,
            String phrase1,
            String phrase2) {

        Embedding embedding1 = modele.embed(phrase1).content();
        Embedding embedding2 = modele.embed(phrase2).content();

        double similarite = CosineSimilarity.between(embedding1, embedding2);

        System.out.println("Phrase 1 : " + phrase1);
        System.out.println("Phrase 2 : " + phrase2);
        System.out.println("Similarité : " + similarite);
        System.out.println();
    }
}