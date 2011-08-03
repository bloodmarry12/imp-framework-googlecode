package com.huawei.imp.framework.jee.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huawei.imp.framework.common.web.controller.BaseControllerSupport;

/**
 * Description:
 * 
 * @author ahli Jun 2, 2009
 */
@Controller
public class RandNumberImageController implements BaseControllerSupport
{
	@RequestMapping("/randNumberImage.do")
	public void showImage(HttpServletRequest request,
			HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException
	{
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Content-Type", "image/jpeg");

		HttpSession session = request.getSession();
		int width = 50, height = 25;
		System.setProperty("java.awt.headless", "true");
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();

		Random random = new Random();

		g.setColor(new Color(192, 174, 231));
		g.fillRect(0, 0, width, height);

		g.setColor(Color.white);

		g.setFont(new Font("Times New Roman", Font.BOLD, 17));

		g.setColor(getRandColor(60, 120));

		for (int i = 0; i < 10; i++)
		{
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		StringBuilder sRand = new StringBuilder();
		for (int i = 0; i < 4; i++)
		{
			sRand.append(random.nextInt(10));
		}
		g.drawString(sRand.toString(), 5, 18);

		// session.setAttribute( ColorRingKeys.VALIDATE , sRand);
		session.setAttribute(SESSION_RANDNUMBER, sRand.toString());

		g.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}

	private Color getRandColor(int fc, int bc)
	{

		// ��Χ��������ɫ
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
