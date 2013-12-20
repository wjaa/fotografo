package br.com.wjaa.fotografo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import br.com.wjaa.fotografo.model.ImageInfo;

/**
 * 
 * @author Wagner Araujo
 *
 */
@Service
public class ImageMagickServiceImpl implements ImageMagickService {

	public ImageInfo getImageInfo(String path) {
		String result = this.getMetadaData(path);
		return this.parseResult(result);
	}

	
	private ImageInfo parseResult(String result) {
		
		ImageInfo imageInfo = new ImageInfo();
		Pattern pGeometry = Pattern.compile("Geometry: [0-9]+x[0-9]+");
		Pattern pResoluction = Pattern.compile("Resolution: [0-9]+x[0-9]+");
		Pattern pSize = Pattern.compile("Filesize: [0-9]+.[0-9]+[\\w]+");
		
		Matcher mGeometry = pGeometry.matcher(result);
		while (mGeometry.find()){
			String geometry = mGeometry.group();
			String size[] = geometry.replace("Geometry: ", "").split("x");
			imageInfo.setWidth(Integer.valueOf(size[0]));
			imageInfo.setHeight(Integer.valueOf(size[1]));
		}
		
		Matcher mResoluction = pResoluction.matcher(result);
		while (mResoluction.find()){
			String resolution = mResoluction.group();
			String size[] = resolution.replace("Resolution: ", "").split("x");
			imageInfo.setWidthResolution(Integer.valueOf(size[0]));
			imageInfo.setHeightResolution(Integer.valueOf(size[1]));
		}
		
		
		Matcher mSize = pSize.matcher(result);
		while (mSize.find()){
			String gSize = mSize.group();
			gSize = gSize.replace("Filesize: ", "");
			Pattern pNSize = Pattern.compile("[0-9]+.[0-9]+");
			Matcher mNSize = pNSize.matcher(result);
			
			while (mNSize.find()){
				String valueStr = mNSize.group();
				String medida = gSize.replace(valueStr, "");
				
				if ("KB".equalsIgnoreCase(medida)){
					imageInfo.setSize(Double.valueOf(valueStr)*1024);
				}else if ("MB".equalsIgnoreCase(medida)){
					imageInfo.setSize(Double.valueOf(valueStr)*Math.pow(1024, 2));
				}else if ("B".contains(medida)){
					imageInfo.setSize(Double.valueOf(valueStr));
				}
			}
			 
		}
		return imageInfo;
	}


	private String getMetadaData(String path) {
		StringBuilder result = new StringBuilder();
		Runtime rt = Runtime.getRuntime();
		try {
			Process pr = rt.exec("identify -verbose " + path);
			
			BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			 
            String line=null;

            while((line=input.readLine()) != null) {
            	result.append(line);
            }
			int exitVal = pr.waitFor();
            System.out.println("Exited with error code "+exitVal);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(new ImageMagickServiceImpl().getImageInfo("/home/wagner/IMG_21082013_130217.png"));
	}
}
