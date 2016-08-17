
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
        //coord = multMatrix(getMatrixTranslation(x,y), coord);
        for(int i = 0; i < coord[0].length; i++) {
            coord[0][i] += x;
            coord[1][i] += y;
        }
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
        //double[][] T1 = getMatrixTranslation(-ref.getX(), -ref.getY());
        
        //Passo 2: escala
        //double[][] S = getMatrixScale(x, y);
        
        //Passo 3: transladar de volta à referencia
        //double [][] T2 = getMatrixTranslation(ref.getX(), ref.getY());
        
        //Final: coord = T2*S*T1*coord
        /*coord = multMatrix(T2,
                    multMatrix(S,
                        multMatrix(T1, coord)
                    )
                );*/
        
        double[][] S = {
            {x, 0, (-ref.getX() * x) + ref.getX()},
            {0, y, (-ref.getY() * y) + ref.getY()},
            {0, 0, 1}
        };
        
        coord = multMatrix(S, coord);
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
    
    static public void printMatrix(double[][] mat) {
        for(double[] linha : mat) {
            System.out.println(Arrays.toString(linha));
        }
    }
    
    public double[][] transJanelaViewport(int height) 
    {   
        double[][] tjv = {
            {1, 0, 0},
            {0, -1, (double)height},
            {0,0,1}
        };
        
        double[][] res = multMatrix(tjv, coord);
        
        return res;
    }
    
    public void zoomExtend(int x, int y, int width, int height)
    {
        double Umin, Umax, Vmin, Vmax, Umax_old, Vmax_old;
        Umin = x; Umax = Umax_old = x + width - 1;
        Vmin = y; Vmax = Vmax_old = y + height - 1;
        
        double Xmin, Xmax, Ymin, Ymax;
        double[][] jvis = getJanelaVisualizacao();
        Xmin = jvis[0][0];
        Xmax = jvis[0][1];
        Ymin = jvis[1][0];
        Ymax = jvis[1][1];
        
        //aspect ratio
        double RatioJanela = (Xmax - Xmin) / (Ymax - Ymin);
        double RatioView = (Umax - Umin) / (Vmax - Vmin);
        
        if(RatioJanela > RatioView) {
            Vmax = ((Umax - Umin) / RatioJanela) + Vmin;
        } else if(RatioJanela < RatioView) {
            Umax = (RatioJanela * (Vmax - Vmin)) + Umin;
        }
        
        double Sx = ((Umax - Umin) / (Xmax - Xmin));
        double Sy = ((Vmax - Vmin) / (Ymax - Ymin));
        
        //T(Umin,Vmin) . S(Sx, Sy) . T(-Xmin, -Ymin)
        /*double[][] tst = getMatrixTranslation(-Xmin, -Ymin);
        tst = multMatrix(getMatrixScale(Sx, Sy), tst);
        tst = multMatrix(getMatrixTranslation(Umin, Vmin), tst);*/
        double[][] tst = {
            {Sx, 0, Umin - Sx*Xmin},
            {0, Sy, Vmin - Sy*Ymin},
            {0, 0, 1}
        };
        
        //Centralizar
        if(Umax != Umax_old) {
            tst = multMatrix(
                    getMatrixTranslation((Umax_old - Umax)/2, 0), tst);
        }
        if(Vmax != Vmax_old) {
            tst = multMatrix(
                    getMatrixTranslation(0, (Vmax_old - Vmax)/2), tst);
        }
        
        coord = multMatrix(tst, coord);
        
    }
}
