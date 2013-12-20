package br.com.wjaa.fotografo.model;

public class ImageInfo {

	private Integer width;
	private Integer height;
	private Double size;
	private Integer widthResolution;
	private Integer heightResolution;
	
	
	public Double getSize() {
		return size;
	}
	public void setSize(Double size) {
		this.size = size;
	}
	public Integer getWidthResolution() {
		return widthResolution;
	}
	public void setWidthResolution(Integer widthResolution) {
		this.widthResolution = widthResolution;
	}
	public Integer getHeightResolution() {
		return heightResolution;
	}
	public void setHeightResolution(Integer heightResolution) {
		this.heightResolution = heightResolution;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	
	@Override
	public String toString() {
		return "ImageInfo [width=" + width + ", height=" + height + ", size="
				+ size + ", widthResolution=" + widthResolution
				+ ", heightResolution=" + heightResolution + "]";
	}
	
	
}
