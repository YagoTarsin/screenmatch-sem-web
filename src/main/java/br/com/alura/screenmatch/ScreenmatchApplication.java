package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		var conversor = new ConverteDados();
		var temporadas = new ArrayList<>();

		var jsonSerie = consumoApi.obterDados("http://www.omdbapi.com/?t=gilmore+girls&apikey=8a4d7df8");
		var dadosSerie = conversor.obterDados(jsonSerie, DadosSerie.class);
		System.out.println(dadosSerie);

		var jsonEpisodios = consumoApi.obterDados("http://www.omdbapi.com/?t=gilmore+girls&season=1&episode=3&apikey=8a4d7df8");
		var dadosEpisodio = conversor.obterDados(jsonEpisodios, DadosEpisodio.class);
		System.out.println(dadosEpisodio);

		for (int i=1; i<=dadosSerie.TotalTemporadas();  i++) {
			var jsonTemporadas = consumoApi.obterDados("http://www.omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=8a4d7df8");
			var dadosTemporadas = conversor.obterDados(jsonTemporadas, DadosTemporada.class);
			temporadas.add(dadosTemporadas);
		}
		temporadas.forEach(System.out::println);
	}
}
