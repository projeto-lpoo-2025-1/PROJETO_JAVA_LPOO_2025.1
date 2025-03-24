package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.InputStream;
import java.io.IOException;
import java.awt.FontFormatException;
import javax.swing.JPanel;

import characters.*;


public class CharacterSelection extends JPanel implements KeyListener {

    GamePanel gp;
    Font Font02, Font03;
    int selectedIndex=0;
    boolean confirmSelection = false; // Indica se está na tela de confirmação

    String[] characters = {"Rastreador", "Médico", "Mecânico", "Sobrevivente nato"};
    String[] descriptions = {
            "Um ex-investigador que perdeu tudo após expor uma conspiração. Agora, usa sua inteligência e habilidades para encontrar respostas e sobreviver.",
            "Um cirurgião dedicado que se viu forçado a trocar o bisturi por armas. Ainda busca salvar vidas, mas sabe que nem todos merecem sua compaixão.",
            "Criada em uma oficina, Lara improvisa e repara tudo para se manter viva. Pragmática e destemida, transforma sucata em ferramentas de sobrevivência.",
            "Uma elfa ágil e silenciosa, mestre em rastreamento e sobrevivência. Sua conexão com a natureza a mantém sempre um passo à frente do perigo."
    };



    private Player player;

    public CharacterSelection(GamePanel gp) {
        this.gp = gp;
        this.setFocusable(true);
        this.addKeyListener(this);

        //Carregar a fonte personalizada
        try {
            InputStream is1 = getClass().getResourceAsStream("/font/font02.otf");
            Font02 = Font.createFont(Font.TRUETYPE_FONT, is1).deriveFont(24f);

            InputStream is2 = getClass().getResourceAsStream("/font/font03.ttf");
            Font03 = Font.createFont(Font.TRUETYPE_FONT, is2).deriveFont(24f);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setColor(Color.WHITE);
        g2.setFont(Font03);

        if (!confirmSelection) {
            String title = "Escolha seu personagem:";
            int titleWidth = g2.getFontMetrics().stringWidth(title);
            int titleX = (gp.screenWidth - titleWidth) / 2;
            g2.drawString(title, titleX, 190);

            for (int i = 0; i < characters.length; i++) {
                String texto = (i == selectedIndex) ? "> " + characters[i] : "  " + characters[i];
                int textWidth = g2.getFontMetrics().stringWidth(texto);
                int textX = (gp.screenWidth - textWidth) / 2;

                g2.setColor(i == selectedIndex ? Color.YELLOW : Color.WHITE);
                g2.drawString(texto, textX, 260 + (i * 40));
            }


        } else{
            //Tela de confirmação
            g2.setColor(Color.YELLOW);
            g2.setFont(Font03.deriveFont(18f));
            g2.drawString("Você escolheu: " + characters[selectedIndex], 50, 100);

            g2.setColor(Color.WHITE);

            String descricao = descriptions[selectedIndex];
            int x=50;
            int y = 150; // Posição inicial

            //Definir a largura máxima para a quebra de linha
            int larguraMaxima = 700;
            String[] palavras = descricao.split(" ");
            StringBuilder linhaAtual = new StringBuilder();

            for (String palavra : palavras) {
                String testeLinha = linhaAtual + " " + palavra;

                // Se a linha for muito longa, desenha a linha e começa uma nova
                if (g2.getFontMetrics().stringWidth(testeLinha) > larguraMaxima) {
                    g2.drawString(linhaAtual.toString().trim(), x, y);
                    y +=30;
                    linhaAtual = new StringBuilder(palavra); // Inicia uma nova linha
                } else {
                    linhaAtual.append(" ").append(palavra); // Adiciona a palavra à linha
                }
            }

            //Desenha a última linha
            if (linhaAtual.length() > 0) {
                g2.drawString(linhaAtual.toString().trim(), x, y);
            }

            //Adiciona um espaço vazio (pula uma linha) antes das vantagens/desvantagens
            y += 60;

            //Vantagens e Desvantagens
            String[] vantagens = {
                    "Vantagens: Encontra comida e água com mais facilidade.",
                    "Vantagens: Pode tratar ferimentos sem necessidade de itens raros.",
                    "Vantagens: Conserta ferramentas e cria novas armas.",
                    "Vantagens: Menos impactada por fome e sede."
            };

            String[] desvantagens = {
                    "Desvantagens: Lento em combate corpo a corpo.",
                    "Desvantagens: Baixa resistência física.",
                    "Desvantagens: Baixa resistência física.",
                    "Desvantagens: Baixa resistência física."
            };

            String vantagensText = vantagens[selectedIndex];
            String desvantagensText = desvantagens[selectedIndex];

            //Quebrar as linhas de vantagens
            palavras = vantagensText.split(" ");
            linhaAtual = new StringBuilder();
            for (String palavra : palavras) {
                String testeLinha = linhaAtual + " " + palavra;

                // Se a linha for muito longa, desenha a linha e começa uma nova
                if (g2.getFontMetrics().stringWidth(testeLinha) > larguraMaxima) {
                    g2.drawString(linhaAtual.toString().trim(), x, y);
                    y += 25; // Pula uma linha para a próxima
                    linhaAtual = new StringBuilder(palavra); // Inicia uma nova linha
                } else {
                    linhaAtual.append(" ").append(palavra); // Adiciona a palavra à linha
                }
            }
            //Desenha a última linha de vantagens
            if (linhaAtual.length() > 0) {
                g2.drawString(linhaAtual.toString().trim(), x, y);
            }

            y += 40; // Pular uma linha entre vantagens e desvantagens

            //Quebrar as linhas de desvantagens
            palavras = desvantagensText.split(" ");
            linhaAtual = new StringBuilder();
            for (String palavra : palavras) {
                String testeLinha = linhaAtual + " " + palavra;

                //Se a linha for muito longa, desenha a linha e começa uma nova
                if (g2.getFontMetrics().stringWidth(testeLinha) > larguraMaxima) {
                    g2.drawString(linhaAtual.toString().trim(), x, y);
                    y += 25; // Pula uma linha para a próxima
                    linhaAtual = new StringBuilder(palavra); // Inicia uma nova linha
                } else {
                    linhaAtual.append(" ").append(palavra); // Adiciona a palavra à linha
                }
            }
            //Desenha a última linha de desvantagens
            if (linhaAtual.length() > 0) {
                g2.drawString(linhaAtual.toString().trim(), x, y);
            }

            //Exibe a mensagem para pressionar ESC ou ENTER
            y += 80; // Espaço extra antes da mensagem de controle
            g2.setFont(Font02.deriveFont(24f)); // Definir fonte para a mensagem de controle
            g2.setColor(Color.WHITE);
            g2.drawString("Pressione ESC para voltar ou ENTER para confirmar.", 50, y);
        }

        g2.dispose();
    }
    @Override
    public void keyPressed(KeyEvent e) {

        if(!confirmSelection){

            if (e.getKeyCode() == KeyEvent.VK_UP) {
                selectedIndex = (selectedIndex - 1 + characters.length) % characters.length; // Navegar para cima
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                selectedIndex = (selectedIndex + 1) % characters.length; // Navegar para baixo
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                //Seleciona o personagem ao pressionar ENTER
                confirmSelection=true;
            }

        } else{      //Tela de confirmação
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                gp.selectedCharacter = characters[selectedIndex].toLowerCase().replace(" ", "");
                gp.startGame(); //Inicia o jogo
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                confirmSelection = false; //Voltar para a seleção normal
            }
        }
        repaint(); //Atualizar tela
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}

