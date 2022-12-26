package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Produtos> list = new ArrayList<>();
		
	   //    C:\\Users\\jeffe\OneDrive\\Área de Trabalho\\Diretorio\\source.csv
		
		System.out.println("Digite o caminho do arquivo");
        String path = sc.nextLine();
        
		File sourceFile = new File(path);
		
		try (BufferedReader file = new BufferedReader(new FileReader(path))) {

			String line = file.readLine();
			while (line != null) {
				String tratamento = line;
				String[] vect = tratamento.split(",");
				line = file.readLine(); // comando para passar para a proxima linha
				list.add(new Produtos(vect[0], Double.valueOf(vect[1]), Integer.valueOf(vect[2])));
			}

		}

		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		boolean sucess = new File(sourceFile.getParent() + "\\out").mkdir();
		
		System.out.println("O diretorio foi criado ? " + sucess);
		
		String newfile = sourceFile.getParent() + "/out/summary.csv";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(newfile))) {
			for (Produtos x : list) {
				bw.write(x.getNome() + ", Valor total: " + x.valortotal());
				bw.newLine();
			}
		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}

}
