package aplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		
	//Etapa 1: Leitura simples com File e Scanner		
		
		File file = new File("M:\\CURSOS\\GitHub Desktop\\MeusProjetos\\Como-ler-arquivo-texto-em-Java\\etapa1.txt");
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				System.out.println(scanner.nextLine());
			}
		}
		catch (IOException erro) {
			System.out.println(erro.getMessage());
		}
		finally {
			if (scanner != null) {
				scanner.close();
			}		
		}
		
	//Etapa 2: FileReader e BufferedReader com controle manual
		System.out.println();
		
		String pathEtapa2 = "M:\\CURSOS\\GitHub Desktop\\MeusProjetos\\Como-ler-arquivo-texto-em-Java\\etapa2.txt";
		
		
		FileReader fileReader = null;
		BufferedReader bufferedReaderEtapa2 = null; // gerencia a memória usada para leitura
		
		try {
			fileReader = new FileReader(pathEtapa2);
			bufferedReaderEtapa2 = new BufferedReader(fileReader);
			
			String line = bufferedReaderEtapa2.readLine();
			while (line != null) {
				System.out.println(line);
				line = bufferedReaderEtapa2.readLine();
			}
		}
		catch (IOException erro) {
			System.out.println("ERROR: " + erro.getMessage());
		}
		finally {
			try {
				bufferedReaderEtapa2.close();
				fileReader.close();
			}
			catch (IOException erro) {
				erro.printStackTrace();
			}
		
		}
		
	//Etapa 3: Usando bloco 'try with resources'
		System.out.println();
		
		String pathEtapa3 = "M:\\CURSOS\\GitHub Desktop\\MeusProjetos\\Como-ler-arquivo-texto-em-Java\\etapa3.txt";
		
		
		try (BufferedReader bufferedReaderEtapa3 = new BufferedReader(new FileReader(pathEtapa3))) {
		
			
			String line = bufferedReaderEtapa3.readLine();
			while (line != null) {
				System.out.println(line);
				line = bufferedReaderEtapa3.readLine();
			}
		}
		catch (IOException erro) {
			System.out.println("ERROR: " + erro.getMessage());
		}
		
		
	// Etapa 4 - Utilização em Exemplo Prático
		System.out.println();
		
		List<Product> list = new ArrayList<Product>();
		
		String pathEtapa4 = "M:\\CURSOS\\GitHub Desktop\\MeusProjetos\\Como-ler-arquivo-texto-em-Java\\in.txt";
		
		try (BufferedReader bufferedReaderEtapa4 = new BufferedReader(new FileReader(pathEtapa4))) {
		
			/* 
			 * Esse primeiro .readLine() irá ler a primeira linha do arquivo, que no caso é o cabeçalho
			 * como não precisamos do cabeçalho, queremos cadastrar apenas os produtos, será necessario pular essa linha.
			 */
			String line = bufferedReaderEtapa4.readLine();
			/*			 * 
			 *  O comando abaixo irá fazer ele ler a proxima linha antes de começar nosso loop
			*/ 
			line = bufferedReaderEtapa4.readLine();
			while (line != null) {	
				
				/*
				 * O comando .split "quebra" o string  onde é especificado
				 * No caso, onde houver virgula(","), separando assim nome, preço e quantidade
				 */
				String[] vector = line.split(",");
				String name = vector[0];
				Double price = Double.parseDouble(vector[1]);
				Integer quantity = Integer.parseInt(vector[2]);
				
				Product product = new Product(name, price, quantity);
				
				list.add(product);
				
				line = bufferedReaderEtapa4.readLine();
				
			}
			
			System.out.println("PRODUCTS: ");
			for (Product product : list) {
				System.out.println(product);
			}
		}
		catch (IOException erro) {
			System.out.println("ERROR: " + erro.getMessage());
		}
		

	}

}
