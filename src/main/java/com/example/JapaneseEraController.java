package com.example;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.JapaneseDate;
import java.time.chrono.JapaneseEra;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class JapaneseEraController {

	@GetMapping
	public String index(@ModelAttribute("japaneseEraForm") JapaneseEraForm form, Model model) {
		if (model.containsAttribute("date")) {
			JapaneseDate date = (JapaneseDate) model.getAttribute("date");
			form.setDate(LocalDate.from(date));
		} else {
			form.setDate(LocalDate.now(ZoneId.of("Asia/Tokyo")));
		}
		return "index";
	}

	@PostMapping
	public String post(
			@Validated @ModelAttribute("japaneseEraForm") JapaneseEraForm form,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return "index";
		}

		JapaneseDate date;
		try {
			date = JapaneseDate.from(form.getDate());
		} catch (DateTimeException e) {
			bindingResult.reject("date", e.getMessage());
			return "index";
		}

		redirectAttributes.addFlashAttribute("date", date);

		return "redirect:/";
	}

	@ModelAttribute("japaneseEras")
	public List<String> japaneseEras() {
		return Arrays.stream(JapaneseEra.values())
				.map(a -> a.getDisplayName(TextStyle.FULL_STANDALONE, Locale.JAPAN))
				.collect(Collectors.toList());
	}

	@ModelAttribute("japaneseEraForm")
	public JapaneseEraForm form() {
		return new JapaneseEraForm();
	}
}
