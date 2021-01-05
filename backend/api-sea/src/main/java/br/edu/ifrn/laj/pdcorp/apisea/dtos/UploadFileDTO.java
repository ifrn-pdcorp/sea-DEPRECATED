package br.edu.ifrn.laj.pdcorp.apisea.dtos;

public class UploadFileDTO {

	private String fileName;
	private String fileDownloadUrl;
	private String fileType;
	private long fileSize;

	public UploadFileDTO(String fileName, String fileDownloadUrl, String fileType, long fileSize) {
		this.fileName = fileName;
		this.fileDownloadUrl = fileDownloadUrl;
		this.fileType = fileType;
		this.fileSize = fileSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDownloadUrl() {
		return fileDownloadUrl;
	}

	public void setFileDownloadUrl(String fileDownloadUrl) {
		this.fileDownloadUrl = fileDownloadUrl;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

}
