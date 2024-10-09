package CodigoJavaParkings;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Estacionamento estacionamento = new Estacionamento("Estacionamento Central", 60);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao sistema de gerenciamento de estacionamento!");

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Registrar veículo e ocupar vaga");
            System.out.println("2. Liberar vaga e gerar fatura");
            System.out.println("3. Exibir vagas disponíveis");
            System.out.println("4. Salvar estado do estacionamento em arquivo");
            System.out.println("5. Carregar estado do estacionamento de arquivo");
            System.out.println("6. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {

                case 1: // Registrar veículo e ocupar vaga
                System.out.print("Digite a placa do veículo: ");
                String placa = scanner.nextLine();
                Veiculo veiculo = new Veiculo(placa);
            
                // Pergunta ao usuário sobre o tipo de vaga
                System.out.println("Qual o tipo de vaga desejada?");
                System.out.println("1. Idoso");
                System.out.println("2. PCD");
                System.out.println("3. VIP");
                System.out.println("4. Regular");
                int tipoVagaEscolhida = scanner.nextInt();
                scanner.nextLine(); 
            
                Vaga vagaDisponivel = null;
            
                // Filtra as vagas com base no tipo escolhido, por meio de uma pergunta de qual tipo de vaga ele é
                switch (tipoVagaEscolhida) {
                    case 1: // Idoso
                        vagaDisponivel = estacionamento.getVagas().stream()
                            .filter(v -> v instanceof VagaIdoso && !v.isOcupada())
                            .findFirst()
                            .orElse(null);
                        break;
                    case 2: // PCD
                        vagaDisponivel = estacionamento.getVagas().stream()
                            .filter(v -> v instanceof VagaPCD && !v.isOcupada())
                            .findFirst()
                            .orElse(null);
                        break;
                    case 3: // VIP
                        vagaDisponivel = estacionamento.getVagas().stream()
                            .filter(v -> v instanceof VagaVIP && !v.isOcupada())
                            .findFirst()
                            .orElse(null);
                        break;
                    case 4: // Regular
                        vagaDisponivel = estacionamento.getVagas().stream()
                            .filter(v -> v instanceof VagaRegular && !v.isOcupada())
                            .findFirst()
                            .orElse(null);
                        break;
                    default:
                        System.out.println("Tipo de vaga inválido.");
                        break;
                }
            
                if (vagaDisponivel != null) {
                    estacionamento.ocuparVaga(vagaDisponivel, veiculo);
                    System.out.println("Veículo registrado e vaga ocupada: " + vagaDisponivel.getIdentificador());
                } else {
                    System.out.println("Nenhuma vaga disponível para o tipo selecionado.");
                }
                break;
            

                    case 2: // Liberar vaga e gerar fatura
                    System.out.print("Digite o identificador da vaga a ser liberada: ");
                    String identificadorVaga = scanner.nextLine();
                
                    // maneira de encontrar vaga pelo o indentificador dela
                    Vaga vagaParaLiberar = estacionamento.getVagas().stream()
                            .filter(v -> v.getIdentificador().equals(identificadorVaga))
                            .findFirst()
                            .orElse(null);
                
                    if (vagaParaLiberar != null && vagaParaLiberar.isOcupada()) {
                        double tarifa = vagaParaLiberar.calcularTarifa(); 
                        
                        // Identifica o tipo da vaga
                        String tipoVaga = "";
                        if (vagaParaLiberar instanceof VagaIdoso) {
                            tipoVaga = "Idoso";
                            VagaIdoso vagaIdoso = new VagaIdoso(identificadorVaga);
                            tarifa = vagaIdoso.calcularTarifa(); // calcula tarifa exclusiva do IDOSO na classe extends VagaIdoso


                        } else if (vagaParaLiberar instanceof VagaPCD) {
                            tipoVaga = "PCD";
                            VagaPCD vagaPCD = new VagaPCD(identificadorVaga);
                            tarifa = vagaPCD.calcularTarifa(); // calcula tarifa exclusiva do PCDna classe extends VagaPCD


                        } else if (vagaParaLiberar instanceof VagaVIP) {
                            tipoVaga = "VIP";
                            VagaVIP vagaVIP = new VagaVIP(identificadorVaga);
                            tarifa = vagaVIP.calcularTarifa(); // calcula tarifa exclusiva do VIP na classe extends VagaVIP

                        } else if (vagaParaLiberar instanceof VagaRegular) {
                            tipoVaga = "Regular";
                            VagaRegular vagarRegular = new VagaRegular(identificadorVaga);
                            vagarRegular.calcularTarifa(); // padrao de tarifa
                        }
                
                        System.out.println("Fatura gerada para o veículo " + vagaParaLiberar.getVeiculo().getPlaca() + 
                                           " (Tipo de vaga: " + tipoVaga + "): R$ " + tarifa);
                        estacionamento.liberarVaga(vagaParaLiberar);
                    } else {
                        System.out.println("Vaga não encontrada ou já está desocupada.");
                    }
                    break;
                

                case 3: // mostra as vagas disponíveis
                    System.out.println("Vagas disponíveis:");
                    for (Vaga vaga : estacionamento.getVagas()) {
                        if (!vaga.isOcupada()) {
                            String tipoVaga = "";
                            if (vaga instanceof VagaIdoso) {
                                tipoVaga = "Idoso";
                            } else if (vaga instanceof VagaPCD) {
                                tipoVaga = "PCD";
                            } else if (vaga instanceof VagaVIP) {
                                tipoVaga = "VIP";
                            } else if (vaga instanceof VagaRegular) {
                                tipoVaga = "Regular";
                            }
                            
                            System.out.println("Vaga: " + vaga.getIdentificador() + 
                                               " - Tipo: " + tipoVaga 
                            );
                        }
                    }
                    break;

                case 4: // Salvar estado do estacionamento em arquivo
                    System.out.print("Digite o caminho do arquivo para salvar: ");
                    String caminhoSalvar = scanner.nextLine();
                    try {
                        estacionamento.escreverVagasParaArquivo(caminhoSalvar);
                        System.out.println("Estado do estacionamento salvo com sucesso.");
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar o estado do estacionamento: " + e.getMessage());
                    }
                    break;

                case 5: // Carregar estado do estacionamento de arquivo
                    System.out.print("Digite o caminho do arquivo para carregar: ");
                    String caminhoCarregar = scanner.nextLine();
                    try {
                        estacionamento.lerVagasDeArquivo(caminhoCarregar);
                        System.out.println("Estado do estacionamento carregado com sucesso.");
                    } catch (IOException e) {
                        System.out.println("Erro ao carregar o estado do estacionamento: " + e.getMessage());
                    }
                    break;

                case 6: // Sair
                    System.out.println("Saindo do sistema.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
