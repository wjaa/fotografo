package br.com.wjaa.fotografo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.wjaa.fotografo.model.ImageInfo;

/**
 * 
 * @author root
 *
 */
@Controller
public class ImageController {

	@RequestMapping(value = "info/{pathImg}", method = RequestMethod.GET)
	public ImageInfo getImageInfo(String pathImg){
		ImageInfo info = new ImageInfo();
		info.setHeight(100);
		info.setWidth(100);
		
		return info;
	}
	
}
