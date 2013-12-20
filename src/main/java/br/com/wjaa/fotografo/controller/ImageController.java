package br.com.wjaa.fotografo.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import br.com.wjaa.fotografo.model.ImageInfo;
import br.com.wjaa.fotografo.service.ImageMagickService;

/**
 * 
 * @author root
 *
 */
@Controller
public class ImageController {

	@Autowired
	private ImageMagickService imageMagickService;
	
	
	@RequestMapping(value = "/info/{pathImg}", method = RequestMethod.GET)
	public @ResponseBody ImageInfo getImageInfo(@PathVariable("pathImg") String pathImg) throws IOException{
		BASE64Decoder b = new BASE64Decoder();
		pathImg = new String(b.decodeBuffer(pathImg));
		
		return imageMagickService.getImageInfo(pathImg);
	}
	
	public static void main(String[] args) {
		BASE64Encoder en = new BASE64Encoder();
		System.out.println(en.encode("/home/wagner/Documents/logo.png".getBytes()));
	}
}
