package com.example;

import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public class ViewHelper {

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("Gy年M月d日", Locale.JAPAN);

	public String format(JapaneseDate japaneseDate) {
		return japaneseDate.format(formatter);
	}
}
