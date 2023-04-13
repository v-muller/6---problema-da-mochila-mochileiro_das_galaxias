import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        int[] pesos = {56, 59, 80, 64, 75, 17};
        int[] valores = {50, 50, 64, 46, 50, 05};
        System.out.println(knapsack(valores, pesos, 190));
        
        
        //System.out.println(distanciaDeEdicao("Casablanca", "Tortentoso"));
    }

    /**
     * Dado um conjunto de itens, seus repectivos pesos e, a capacidade máxima da mochila.
     * Obtem-se o valor máximo que pode alcançado preenchendo-se a mochila. Nesse caso, os
     * itens são objetos indivisiveis.
     * @param val - Valores de cada item.
     * @param wItem - Pesos de cada item
     * @param wKnapsack - Peso maximo da mochila
     * @return Valor maximo possivel
     */
    private static int knapsack(int val[], int wItem[], int wKnapsack){
        int cont = 0;
        int n = wItem.length; //Recupera a quantidade de itens.
        //Cria matriz que representa a tabela de pesos (pesos itens x peso da mochila)
        int [][] m = new int[n + 1][wKnapsack + 1];
        //Por default, todos os elementos da primeira linha e da primeira coluna, são zero.
        for (int item = 1; item <= n; item++) {
            cont++;
            //preenchendo os valores na tabela
            for (int knapsackWeight = 1; knapsackWeight <= wKnapsack; knapsackWeight++ ) {
                cont++;
                //Caso o item atual seja menor ou igual ao peso atual da mochila
                if (wItem[item - 1] <= knapsackWeight)
                    // retorna o max entre a soma do valor do item atual e o do item (pesoMochila - pesoItem)
                    // e o valor do item da linha anterior e da mesma coluna
                    m [item][knapsackWeight] = Math.max(val[item - 1] + m[item - 1][ knapsackWeight-wItem[item - 1] ],
                            m[item - 1][knapsackWeight]);
                else
                    //Caso nao caiba na mochila, repete o valor do item da linha anterior de mesma coluna
                    m [item][knapsackWeight] = m[item - 1][knapsackWeight];
            }
        }

        //exibindo a tabela
            /*for (int[] rows : m) {
            		for (int col : rows) {
                	System.out.format("%5d", col);
            		}
            	System.out.println();
        	}*/

        //Processo de decisao dos produtos
        ArrayList<Integer> selectedItems = new ArrayList<Integer>();
        int colWeightKnapsack = wKnapsack;
        for (int item = n; item >= 1; item--) {
            cont++;
            if (m [item][colWeightKnapsack] != m [item - 1][colWeightKnapsack]){
                selectedItems.add(item);
                colWeightKnapsack -= wItem[item - 1];
            }
        }

        System.out.println("Produtos escolhidos: "+selectedItems);

        //ultimo elemento da tabela sera o valor maximo possivel
        return cont;
    }

    public static int distanciaDeEdicao(String a, String b){
        int m = a.length() + 1;
        int n = b.length() + 1;
        int[][] matriz = new int[m ][n];
        matriz[0][0] = 0;
        int cont = 0;

        for (int i = 1; i < m; i++){
            matriz[i][0] = matriz[i-1][0] + 1;
            cont++;

        }
        for(int j = 1; j < n; j++){
            matriz[0][j] = matriz[0][j-1] + 1;
            cont++;
        }
        for (int i = 1; i < m; i++){
            cont++;
            for(int j = 1; j < n; j++){
                cont++;
                int custoExtra = 0;
                if(a.charAt(i - 1) == b.charAt(j -1)){
                    custoExtra = 0;
                }
                else{
                    custoExtra =1;
                }
                matriz[i][j] = minimo(matriz[i - 1][j] + 1, matriz[i][j-1] + 1, matriz[i-1][j -1] + custoExtra);
            }

        }
        return  cont;//matriz[m - 1][n -1];
    }

    public static int minimo (int a, int b, int c ){
        int min = 99999999;
        if(a < b && a < c){
            min = a;
        }
        else if(b < a && b < c){
            min = b;
        }
        else{
            min = c;
        }
        return min;
    }
}
