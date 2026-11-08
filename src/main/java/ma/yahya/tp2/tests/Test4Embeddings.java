package ma.yahya.tp2.tests;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.bgesmallenv15.BgeSmallEnV15EmbeddingModel;
import dev.langchain4j.store.embedding.CosineSimilarity;

public class Test4Embeddings {
    public static void main(String[] args) {
        // Création du modèle d'embeddings local
        EmbeddingModel modeleEmbedding = new BgeSmallEnV15EmbeddingModel();

        // Comparaison de six couples de phrases
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
    }

    // Calcule et affiche la similarité entre deux phrases
    private static void comparer(
            EmbeddingModel modeleEmbedding,
            String phrase1,
            String phrase2) {

        Embedding embedding1 = modeleEmbedding.embed(phrase1).content();
        Embedding embedding2 = modeleEmbedding.embed(phrase2).content();

        double similarite = CosineSimilarity.between(embedding1, embedding2);

        System.out.println("Phrase 1 : " + phrase1);
        System.out.println("Phrase 2 : " + phrase2);
        System.out.println("Similarité : " + similarite);
        System.out.println();
    }
}