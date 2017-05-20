package com.web.view;

/**
 * Created by stalk on 01.05.2017.
 */
public class ConsoleOutput {

    public void consoleprint(int[][] battlefield) {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(battlefield[i][j] + "\t");
            }
            System.out.println();
        }
    }

}
