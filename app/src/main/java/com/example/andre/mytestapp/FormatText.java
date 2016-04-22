package com.example.andre.mytestapp;

import java.util.ArrayList;

/**
 * Created by Andre on 20.04.2016.
 */
public class FormatText {

    public static final String DELIMITER = ", ";

    /**
     * Метод реализует разделения массива слов посредством запятой и пробела.
     *
     * @param genres массивчик словечек
     * @return Получаем строку слова которые разделены пробелом, последнее слово без пробела.
     */
    static public String changeCommaSymbol(ArrayList<String> genres) {
        StringBuilder str = new StringBuilder();
        for (String style : genres) {
            str.append(style).append(DELIMITER);
        }
        String text = str.toString();
        if (text.length() > 2)
            return text.substring(0, str.length() - 2); // удаляем последнюю запятую.
        else return text;
    }

    /**
     * @param number Число обьектов
     * @param word   - обьект, слово(корень) у которого надо изменить окончания
     * @param param1 - окончания слова при больше 10 и меньше 15 и т.д
     * @param param2 - окончания слова при 1
     * @param param3 - окончания слова при < 5 >1
     * @return отформатированное слово в нужном числе
     */
     private static String getTerminated(int number, String word, String param1, String param2, String param3) {
        String terminated = "";
         int tNumber = number;
        number %= 100;
        if (number > 10 && number < 15) {
            terminated = param1;//"ен"; // ов
        } else {
            number %= 10;
            if (number == 1) terminated = param2; //"ня"; //""
            if (number > 1 || number == 0) terminated = param1; // "ен";//"ов
            if (number <= 4 && number > 1) terminated = param3; //"ни";//"а
        }
        return tNumber + " " + word + terminated;
    }

    public static String getCountTextSongs(int count) {
        return getTerminated(count, "пес", "ен", "ня", "ни");
    }

    public static String getCountTextAlbums(int albums) {
        return getTerminated(albums, "альбом", "ов", "", "а");
    }

    public static String firstUpperCase(String word) {
        if (word == null || word.isEmpty()) return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
