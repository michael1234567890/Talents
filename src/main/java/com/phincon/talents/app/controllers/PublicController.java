package com.phincon.talents.app.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.app.utils.GlobalValue;

@Controller
@RequestMapping("/public")
public class PublicController {

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String publicController() {
		return "Hello User!";
	}

	@RequestMapping(value = "/attachment/image", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getImageAttachment() throws IOException {

		String pathupload = GlobalValue.PATH_UPLOAD;
		String path = "logo/acc.png";
		String fullpath = pathupload + path;
		System.out.println("Fullpath " + fullpath);
		InputStream in = getClass().getResourceAsStream(fullpath);
		return IOUtils.toByteArray(in);
	}

	@RequestMapping(value = "/getImage", method = RequestMethod.GET)
	public void showImage(@RequestParam(value="path",required=true) String path,HttpServletResponse response) throws Exception {

		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		String pathupload = GlobalValue.PATH_UPLOAD;
		String fullpath = pathupload + path;
		try {
			BufferedImage image = ImageIO.read(new File(fullpath));
			ImageIO.write(image, "png", jpegOutputStream);
		} catch (IllegalArgumentException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}

		byte[] imgByte = jpegOutputStream.toByteArray();

		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/png");
		ServletOutputStream responseOutputStream = response.getOutputStream();
		responseOutputStream.write(imgByte);
		responseOutputStream.flush();
		responseOutputStream.close();
	}

}
