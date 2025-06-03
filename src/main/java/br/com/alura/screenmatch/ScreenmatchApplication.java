package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		var conversor = new ConverteDados();

		var jsonSerie = consumoApi.obterDados("http://www.omdbapi.com/?t=mr+robot&apikey=8a4d7df8");
		DadosSerie dados = conversor.obterDados(jsonSerie, DadosSerie.class);
		System.out.println(dados);

		var jsonEpisodios = consumoApi.obterDados("http://www.omdbapi.com/?t=mr+robot&season=1&episode=3&apikey=8a4d7df8");
		DadosEpisodio dadosEpisodio = conversor.obterDados(jsonEpisodios, DadosEpisodio.class);
		System.out.println(dadosEpisodio);
	}
}
