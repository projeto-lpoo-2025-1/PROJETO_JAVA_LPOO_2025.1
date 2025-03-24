package main;

import java.awt.*;
import javax.swing.JPanel;
import characters.*;
import ambients.AmbientManager;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    //public String playerName;

    //Configurações do mundo

    public final int maxWorldCol=50;
    public final int maxWorldRow=50;
    public final int worldWidth=tileSize*maxWorldCol;
    public final int worldHeight=tileSize*maxWorldRow;

    int FPS=60;

    AmbientManager tileM=new AmbientManager(this);
    KeyHandler keyH= new KeyHandler();
    Thread gameThread;

    public CollisionChecker cChecker= new CollisionChecker(this);
    public Player player= new Player(this, keyH);

    CharacterSelection characterSelectionScreen;
    public String selectedCharacter;//Personagem padrão
    boolean gameStarted = false; //Indica se o jogo começou

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        characterSelectionScreen = new CharacterSelection(this);
        add(characterSelectionScreen); //Adiciona a tela de seleção de personagem
        addKeyListener(characterSelectionScreen); //Escuta os comandos para a seleção
    }

    public void setPlayer(Player player) {
        this.player = player; //Define o jogador no GamePanel
    }

    public void startGame() {


        //Criar o personagem baseado na escolha do jogador
        if (selectedCharacter.equals("médico")) {
            player = new Doctor(this, keyH);
        } else if (selectedCharacter.equals("mecânico")) {
            player = new Mechanic(this, keyH);
        } else if (selectedCharacter.equals("rastreador")) {
            player = new Detective(this, keyH);
        } else {
            player = new Survivor(this, keyH);
        }

        addKeyListener(keyH);
        requestFocusInWindow();
        gameStarted = true;
        startGameThread();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {

        double drawInterval=1000000000.0/FPS;
        double delta=0;
        long lastTime= System.nanoTime();
        long currentTime;
        long timer=0;
        int drawCount=0;

        while(gameThread != null) {

            currentTime= System.nanoTime();

            delta+=(currentTime - lastTime)/drawInterval;
            timer+=(currentTime-lastTime);

            lastTime=currentTime;

            if (delta >= 1) { // Só atualiza o jogo quando delta ≥ 1
                update();
                repaint();
                delta--; // Reduz delta para evitar múltiplas atualizações extras
                drawCount++;
            }

            if (timer>=1000000000) {

                System.out.println("FPS:" + drawCount);
                drawCount=0;
                timer=0;
            }

            try {
                Thread.sleep(1); // Pequena pausa para evitar uso excessivo da CPU
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void update() {
        if (gameStarted) {
            player.update();
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2= (Graphics2D)g;

        tileM.draw(g2); //Desenhar mapa antes do personagem

        //player.draw(g2);

        if (gameStarted) {
            player.draw(g2);
        } else {
            characterSelectionScreen.paintComponent(g); // Desenha a seleção corretamente
        }

        g2.dispose();
    }

}