package com.base.controller.common;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value="/yanzheng")
public class RandomYan {
	private static final Logger loger = LoggerFactory
			.getLogger(RandomYan.class);
	public static final char[] CHARS = { '2', '3', '4', '5', '6', '7', '8',
			'9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
			'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	public static Random random = new Random();

	public static String getRandomString() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			buffer.append(CHARS[random.nextInt(CHARS.length)]);
		}
		return buffer.toString();
	}

	public static Color getRandomColor() {
		return new Color(random.nextInt(255), random.nextInt(255),
				random.nextInt(255));
	}

	public static Color getReverseColor(Color c) {
		return new Color(255 - c.getRed(), 255 - c.getGreen(),
				255 - c.getBlue());
	}

	@RequestMapping("/yanzhengma")
	public void yanzhengma(HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		response.setContentType("image/jpeg");
		String randomString = getRandomString();
		request.getSession(true).setAttribute("randomString", randomString);
		loger.info(new StringBuilder().append("验证码:").append(randomString)
				.toString());
		int width = 100;
		int height = 30;

		Color color = getRandomColor();
		Color reverse = getReverseColor(color);

		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		//g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		g.setColor(reverse);
		g.drawString(randomString, 10, 22);
		for (int i = 0, n = random.nextInt(100); i < n; i++) {
			g.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);
		}
		// 转成JPEG格式
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "JPEG", out);  
		out.flush();
	}

}
