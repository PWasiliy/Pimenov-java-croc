package ru.croc.task1;

import java.util.regex.*; // ����������� ������� �������
import java.io.*;
import java.nio.file.Path;
import java.nio.file.FileSystems;

/**
* ����� Main
* ����� Main()
**/

class Main {
	/* ������� removeJavaComments() ��������� ������ ��� ������������ � ���� */
	static String removeJavaComments(String code) {
		Pattern pattern = Pattern.compile("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)");
		Matcher matcher = pattern.matcher(code);
		return matcher.replaceAll("");
	}

	public static void main(String[] args) throws IOException {
		// ������ ��� �� �������� �����
		Path path = FileSystems.getDefault().getPath(".", "Main.java");
		File file = path.toFile(); 
		FileReader reader = new FileReader(file);
        	char[] chars = new char[(int) file.length()];
        	reader.read(chars);
        	String code = new String(chars);

		System.out.println(String.format("��� ��������� �� �����: \"%s\"\n", path));
		System.out.println(code + "\n");
		System.out.println("�������� �������� ������������...\n");
		System.out.println(removeJavaComments(code));
	}
}