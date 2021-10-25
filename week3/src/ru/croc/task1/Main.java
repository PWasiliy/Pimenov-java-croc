package ru.croc.task1;

import java.util.regex.*; // подключение внешних классов
import java.io.*;
import java.nio.file.Path;
import java.nio.file.FileSystems;

/**
* класс Main
* метод Main()
**/

class Main {
	/* функция removeJavaComments() возвращет строку без комментариев в коде */
	static String removeJavaComments(String code) {
		Pattern pattern = Pattern.compile("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)");
		Matcher matcher = pattern.matcher(code);
		return matcher.replaceAll("");
	}

	public static void main(String[] args) throws IOException {
		// читаем код из текущего файла
		Path path = FileSystems.getDefault().getPath(".", "Main.java");
		File file = path.toFile(); 
		FileReader reader = new FileReader(file);
        	char[] chars = new char[(int) file.length()];
        	reader.read(chars);
        	String code = new String(chars);

		System.out.println(String.format("Код программы из файла: \"%s\"\n", path));
		System.out.println(code + "\n");
		System.out.println("Выполняю удаление комментариев...\n");
		System.out.println(removeJavaComments(code));
	}
}