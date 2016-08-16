
package trabalhocg;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;


/**
 * Classe Polígono
 * Representa um polígono de N vértices
 * @author pedro
 */
public class Poligono {
    
    /**
     * Coordenadas homogeneas do polígono
     */
    double[][] coord;
    int vertices;
    
    /**
     * Cria um Poligono de coordenadas homogeneas a partir de um array de Ponto2D
     * Pontos 2d são em relação a um sistema especifico
     * assume-se que o sistema base é o WCS, com 0,0 sendo a origem
     * @param pontos 
     */
    public Poligono(Ponto2D[] pontos) {
        coord = new double[3][pontos.length];
        for(int i = 0; i < pontos.length; i++) {
            coord[0][i] = pontos[i].coord[0];
            coord[1][i] = pontos[i].coord[1];
            coord[2][i] = 1;
        }
        
        vertices = pontos.length;
    }
    
    /**
     * Cria poligono a partir de uma List de Ponto2D
     * @param pontos 
     */
    private Poligono( List<Ponto2D> pontos) {
        this( pontos.toArray(new Ponto2D[0]) ); 
    }
    
    /**
     * Multiplica duas matrizes
     * manda um erro se elas forem incompatíveis
     * @param A
     * @param B
     * @return 
     */
    private static double[][] multMatrix(double[][] A, double[][] B)
    {
        int aRows = A.length;
        int aCols = A[0].length;
        int bRows = B.length;
        int bCols = B[0].length;
        
        if(aCols != bRows) {
            throw new ArithmeticException("Colunas de A(" + aCols + ") devem ser iguais às linhas de B(" + bRows +")");
        }
        
        //Inicializar retorno
        double[][] C = new double[aRows][bCols];
        for(int i = 0; i < aRows; i++) {
            for(int j = 0; j < bCols; j++) {
                C[i][j] = 0.0;
            }
        }
        
        //Realizar a multiplicação
        for(int i = 0; i < aRows; i++) {
            for(int j = 0; j < bCols; j++) {
                for(int k = 0; k < aCols; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        
        return C;
    }
    
    /**
     * Translada em +x, +y
     * @param x
     * @param y
     * @return 
     */
    private static double[][] getMatrixTranslation(double x, double y) {
        return new double[][] {
            {1, 0, x},
            {0, 1, y},
            {0, 0, 1}
        };
    }
    
    /**
     * Aumenta a escala em x, y
     * @param x
     * @param y
     * @return 
     */
    private static double[][] getMatrixScale(double x, double y) {
        return new double[][] {
            {x, 0, 0},
            {0, y, 0},
            {0, 0, 1}
        };
    }
    
    /**
     * Rotationa em radians Radianos
     * @param radians
     * @return 
     */
    private static double[][] getMatrixRotation(double radians) {
        return new double[][] {
            {Math.cos(radians), -Math.sin(radians), 0},
            {Math.sin(radians), Math.cos(radians),  0},
            {0,                 0,                  1}
        };
    }
    
    private static final double[][] MATRIX_MIRROR_X = new double [][] {
            {1, 0, 0},
            {0, -1, 0},
            {0, 0, 1}
        };
    private static final double[][] MATRIX_MIRROR_Y = new double [][] {
            {-1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };
    
    
    /**
     * Translada o objeto em +x +y 
     * @param x
     * @param y
     */
    public void translate(double x, double y) {
        coord = multMatrix(getMatrixTranslation(x,y), coord);
    }
    
    /**
     * Realiza uma transformação de escala,
     * levando o objeto à referencia e retornando-o depois
     * @param ref: ponto 2d de referencia(ex. um canto do poligono)
     * @param x
     * @param y 
     */
    public void scale(Ponto2D ref, double x, double y) {
        
        //Passo 1: transladar até origem
        double[][] T1 = getMatrixTranslation(-ref.getX(), -ref.getY());
        
        //Passo 2: escala
        double[][] S = getMatrixScale(x, y);
        
        //Passo 3: transladar de volta à referencia
        double [][] T2 = getMatrixTranslation(ref.getX(), ref.getY());
        
        //Final: coord = T2*S*T1*coord
        coord = multMatrix(T2,
                    multMatrix(S,
                        multMatrix(T1, coord)
                    )
                );
    }
    
    /**
     * Realiza uma rotacao em relação a uma referencia
     * @param ref: referencia da rotaçao(ex. um canto do polígono)
     * @param radians: Valor em RADIANOS da rotação
     */
    public void rotate(Ponto2D ref, double radians) {
        
        //Passo 1: transladar até origem
        double[][] T1 = getMatrixTranslation(-ref.getX(), -ref.getY());
        
        //Passo 2: rotacao
        double[][] R = getMatrixRotation(radians);
        
        //Passo 3: transladar de volta à referencia
        double [][] T2 = getMatrixTranslation(ref.getX(), ref.getY());
        
        //Final: coord = T2*R*T1*coord
        coord = multMatrix(T2,
                    multMatrix(R,
                        multMatrix(T1, coord)
                    )
                );
    }
    
    /**
     * Realiza uma rotação por um ÂNGULO
     * @param ref
     * @param degrees 
     */
    public void rotateDeg(Ponto2D ref, double degrees) {
        rotate(ref, Math.toRadians(degrees));
    }
    
    /**
     * Retorna os pontos x(min, max) e y(min, max) em relacao a x, y respectivamente
     * tal que englobam todo o polígono
     * @return double[2][2] = {{x_min, x_max},
     *                         {y_min,y_max}}
     */
    public double[][] getLimits() {
        
        //inicialmente, o min e max sao iguais ao primeiro ponto do polígono
        double x_min = coord[0][0];
        double x_max = coord[0][0];
        double y_min = coord[1][0];
        double y_max = coord[1][0];
        
        
        for(int i = 1; i < vertices; i++) {
            //coord[i] é um ponto do objeto
            x_min = Double.min(coord[0][i], x_min);
            x_max = Double.max(coord[0][i], x_max);
            
            y_min = Double.min(coord[1][i], y_min);
            y_max = Double.max(coord[1][i], y_max);
        }
        return new double[][] {
            {x_min, x_max},
            {y_min, y_max}
        };
    }
    
    public double[][] getJanelaVisualizacao() {
        return getJanelaVisualizacao(0.2);
    }

    /**
     *
     * @param padding entre 0 e 1,
     * @return
     */
    public double[][] getJanelaVisualizacao(double padding) {
        double[][] lim = getLimits();
        
        
        double pad_x = (padding) * Math.abs(lim[0][1] - lim[0][0]);
        double pad_y = (padding) * Math.abs(lim[1][1] - lim[1][0]);
        
        //X_min
        lim[0][0] = lim[0][0] - pad_x;
        //X_max
        lim[0][1] = lim[0][1] + pad_x;
        
        //Y_min
        lim[1][0] = lim[1][0] - pad_y;
        //Y_max
        lim[1][1] = lim[1][1] + pad_y;
        
        
        return lim;
    }
    
    /**
     * Retorna o sistema de coordenadas do dispositivo em int[][] a partir de g
     * Lembrando que o PDCS tem o Y negativo
     * @param g
     * @return 
     */
    public int[][] getPDCS(Graphics g)
    {
        Rectangle disp = g.getClipBounds();
        
        int U_min = disp.x;
        int U_max = disp.x + disp.width-1;
        int V_min = (disp.y);
        int V_max = (disp.y + disp.height-1);
        
        return new int[][] {
            {U_min,U_max},
            {V_min,V_max}
        };
    }
    
    static public void printMatrix(double[][] mat) {
        for(double[] linha : mat) {
            System.out.println(Arrays.toString(linha));
        }
    }
    
    /**
     * Realiza uma transformacao janela-viewport sem contar o aspect ratio
     * @param g
     * @return 
     */
    public double[][] transfJanelaViewport(Graphics g) {
        double[][] janela = getJanelaVisualizacao();
        int[][] PDCS = getPDCS(g);
        
        double U_min = PDCS[0][0];
        double U_max = PDCS[0][1];
        double U_max_old = U_max;
        double V_min = PDCS[1][0];
        double V_max = PDCS[1][1];
        double V_max_old = V_max;
        double X_min = janela[0][0];
        double X_max = janela[0][1];
        double Y_min = janela[1][0];
        double Y_max = janela[1][1];
        
        double Rw = (X_max - X_min) / (Y_max - Y_min);
        double Rv = (U_max - U_min) / (V_max - V_min);
        
        if(Rw > Rv) {
            V_max = ((U_max - U_min) / Rw) + V_min;
        } else if(Rw < Rv) {
            U_max = Rw * (V_max - V_min) + U_min;
        }
        
        
        
        double Sx = ((U_max - U_min) / (X_max - X_min));
        double Sy = ((V_max - V_min) / (Y_max - Y_min));
        
        
        /*
        //Operações realizadas:
        double Tjv[][] = getMatrixTranslation(-X_min, -Y_min);
        Tjv = multMatrix(getMatrixScale(Sx, Sy), Tjv);
        Tjv = multMatrix(getMatrixTranslation(U_min, V_min), Tjv);
        
        //translação para mudança de sistema de coordenadas
        Tjv = multMatrix(getMatrixTranslation(0, -(V_max + V_min)), Tjv );
        */
        
        
        double[][] Tjv = {
            {Sx, 0, -Sx * X_min + U_min},
            {0, Sy, -Sy * Y_min - V_max},
            {0, 0,  1}
        };
        double[][] res = multMatrix(Tjv, coord);
        
        if(V_max_old != V_max) {
            res = multMatrix(getMatrixTranslation(0, (V_max_old - V_max)/2), res);
        } else if(U_max_old != U_max) {
            res = multMatrix(getMatrixTranslation((U_max_old - U_max)/2, 0), res);
            
        }
        
        return res;
    }
    
    
    /**
     * Desenha o polígono sobre os gráficos g
     * Converte das coordenadas internas(contínuas) para coordenadas da viewport(discretas)
     * @param g 
     */
    public void draw(/*Graphics g*/) {
        
        
    }
    
    
    public static void main(String[] args) {
        //teste
        Ponto2D[] pts = {
            new Ponto2D(0, 0),
            new Ponto2D(0, 1),
            new Ponto2D(1, 0),
            new Ponto2D(1, 1)
        };
        
        Poligono p = new Poligono(pts);
        
        p.draw();
    }
    
    
}
