package br.com.wjaa.fotografo.service;

import br.com.wjaa.fotografo.model.ImageInfo;

/**
 * 
 * @author Wagner Jeronimo
 *
 */
public interface ImageMagickService {

	/**
	 * Informacoes da imagem.
	 * @param path
	 * @return
	 */
	public ImageInfo getImageInfo(String path);
	
}
