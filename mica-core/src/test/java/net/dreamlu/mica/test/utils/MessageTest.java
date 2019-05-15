package net.dreamlu.mica.test.utils;

import net.dreamlu.mica.core.utils.StringUtil;
import org.apache.logging.log4j.message.FormattedMessage;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class MessageTest {

	public static void main(String[] args) {
		Map<String, Object> a = new HashMap<>();
		a.put("1", LocalDateTime.now());
		a.put("2", 1000000);
		a.put("3", "123123");
		a.put("4", new int[]{1,2,3,4});

		String message = "my name is {}, and i like {} {}!";

		// mica
		System.out.println(StringUtil.format(message, "L.cm", "Java", a));

		// slf4j
		FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, new Object[]{"L.cm", "Java", a}, null);
		System.out.println(formattingTuple.getMessage());

		// log4j2
		FormattedMessage formattedMessage = new FormattedMessage(message, "L.cm", "Java", a);
		System.out.println(formattedMessage.getFormattedMessage());
	}
}
