package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<String> placasCarros = new ArrayList<>();

		double faturamentoTotal = 0;
		double faturamentoDoDia = 0.0;

		System.out.println("Iniciar programa - 1\nFechar programa - 2");
		int escolha = sc.nextInt();
		sc.nextLine();

		while (escolha == 1) {
			System.out.println(
					"\n1 - Registrar Entrada de Veículo \n2 - Reegistrar saída de Veículo (pagamento) \n3 - Exibir Veículos no Pátio \n4 - Encerrar Expediente");
			int escolhaOpcao = sc.nextInt();
			sc.nextLine();

			String placas = null;

			switch (escolhaOpcao) {

			case 1:
				System.out.println("digite a placa do carro");
				placas = sc.nextLine();
				placasCarros.add(placas);
				break;

			case 2:
				System.out.println("Digite a placa do carro que esta saindo: ");
				placas = sc.nextLine();
				System.out.println("Quanto tempo o carro ficou?");
				int horario = sc.nextInt();
				sc.nextLine();

				boolean existe = placasCarros.contains(placas);

				faturamentoTotal = horario * 10;

				while (horario > 23 || horario < 0) {
					System.out.println("Valor inválido \nDigite novamento o horário");
					System.out.println("Quanto tempo o carro ficou?");
					horario = sc.nextInt();
					sc.nextLine();
				}

				if (existe) {
					System.out.println("=============================");
					System.out.println("Carro liberado!!");
					placasCarros.remove(placas);
					faturamentoDoDia += faturamentoTotal;

					System.out.printf("Valor a ser pago: %.2f ", faturamentoTotal);
					System.out.println("\n=============================");

				} else {
					System.out.println("❌ Carro não encontrado no pátio!");
				}

				break;

			case 3:
				System.out.println("Quantidade de carros no pátio: " + placasCarros.size());
				for (int i = 0; i < placasCarros.size(); i++) {
					System.out.println((i + 1) + " - Carro da placa: " + placasCarros.get(i));
				}

				break;

			case 4:

				System.out.println("=====================================");
				System.out.println("     FECHAMENTO DE CAIXA DO DIA      ");
				System.out.println("=====================================");
				System.out.printf("Faturamento bruto total: R$ %.2f reais\n", faturamentoDoDia);
				System.out.println("=====================================");

				escolha = 2;

				break;

			default:
				System.out.println("ERROR");

			}

			while (escolha == 2) {
				System.out.println("Programa fechado com sucesso");
				System.out.println("============================");
				System.out.println("Deseja iniciar o programa - 1");
				escolha = sc.nextInt();
				sc.nextLine();

			}

		}

		sc.close();
	}

}
