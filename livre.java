
  // IntelliJ API Decompiler stub source generated from a class file
  // Implementation of methods is not available

package com.bibliotheque.entites;

public class Livre implements com.bibliotheque.patterns.observer.Sujet, java.io.Serializable, com.bibliotheque.patterns.decorator.LivreComponent {
    private boolean isDigital;
private String filePath;
private String fileFormat;
private long fileSize;
private int downloadCount;

// Getters/Setters
public boolean isDigital() { return isDigital; }
public void setDigital(boolean digital) { isDigital = digital; }

public String getFilePath() { return filePath; }
public void setFilePath(String filePath) { this.filePath = filePath; }

public String getFileFormat() { return fileFormat; }
public void setFileFormat(String fileFormat) { this.fileFormat = fileFormat; }

public long getFileSize() { return fileSize; }
public void setFileSize(long fileSize) { this.fileSize = fileSize; }

public int getDownloadCount() { return downloadCount; }
public void setDownloadCount(int downloadCount) { this.downloadCount = downloadCount; }
