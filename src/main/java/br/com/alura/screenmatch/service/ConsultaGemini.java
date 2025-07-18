package br.com.alura.screenmatch.service;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;

public class ConsultaGemini {

    public static String obterTraducao(String texto) {

        ChatLanguageModel gemini = GoogleAiGeminiChatModel.builder()
                .apiKey(System.getenv("API_KEY_GEMINI"))
                .modelName("gemini-1.5-flash")
                .build();

        String response = gemini.generate("Traduza para PT-BR o texto: " + texto);
        return response;
    }
}