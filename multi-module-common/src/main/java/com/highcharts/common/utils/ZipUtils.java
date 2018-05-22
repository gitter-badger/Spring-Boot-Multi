package com.highcharts.common.utils;

/**
 * <p>BMap/com.highcharts.common.utils</p>
 *
 * @author Created by BruceZheng
 * @date 2018-01-31 08:15
 **/
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.*;
import java.util.zip.ZipFile;

public class ZipUtils {
    public ZipUtils() {
    }

    public static void main(String[] args) {
        String path = "F:\\java\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\alingx\\upload\\synchro\\tmp_upload.zip";
        String dir = "F:\\java\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\alingx\\upload\\synchro\\tmp_upload";

        try {
            compressDirectory2Zip(dir, path);
            readZipFile(path, "update.xml");
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public static void compressDirectory2Zip2() {
    }

    public static String readZipFile2(String zipfile, String filename) {
        StringBuilder sb = new StringBuilder();

        try {
            ZipFile zipFile = new ZipFile(zipfile);
            System.out.println(zipFile.getEntry(filename));
            InputStreamReader isr = new InputStreamReader(zipFile.getInputStream(zipFile.getEntry(filename)), "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            String line;
            while((line = br.readLine()) != null) {
                sb.append(line).append("\r\n");
            }

            br.close();
            zipFile.close();
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return sb.toString();
    }

    public static String readZipFile(String zipfile, String filename) throws Exception {
        StringBuilder sb = new StringBuilder();
        org.apache.commons.compress.archivers.zip.ZipFile zf = new org.apache.commons.compress.archivers.zip.ZipFile(zipfile);
        if (zf.getEntry(filename) == null) {
            filename = "/" + filename;
        }

        InputStreamReader isr = new InputStreamReader(zf.getInputStream(zf.getEntry(filename)), "UTF-8");
        BufferedReader br = new BufferedReader(isr);

        String line;
        while((line = br.readLine()) != null) {
            sb.append(line).append("\r\n");
        }

        br.close();
        zf.close();
        return sb.toString();
    }

    public static void compressDirectory2Zip(String file, String zipFilePath) {
        try {
            a zip = new a(zipFilePath);
            zip.a();
            zip.a(file, "");
            zip.b();
        } catch (FileNotFoundException var3) {
            var3.printStackTrace();
        } catch (IOException var4) {
            var4.printStackTrace();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public static void compressFiles2Zip(File[] files, String zipFilePath) {
        if (files != null && files.length > 0 && isEndsWithZip(zipFilePath)) {
            ZipArchiveOutputStream zaos = null;

            try {
                File zipFile = new File(zipFilePath);
                zaos = new ZipArchiveOutputStream(zipFile);
                zaos.setUseZip64(Zip64Mode.AsNeeded);
                File[] var7 = files;
                int var6 = files.length;

                for(int var5 = 0; var5 < var6; ++var5) {
                    File file = var7[var5];
                    if (file != null) {
                        ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, file.getName());
                        zaos.putArchiveEntry(zipArchiveEntry);
                        FileInputStream is = null;

                        try {
                            is = new FileInputStream(file);
                            byte[] buffer = new byte[5120];
                            boolean var11 = true;

                            int len;
                            while((len = is.read(buffer)) != -1) {
                                zaos.write(buffer, 0, len);
                            }

                            zaos.closeArchiveEntry();
                        } catch (Exception var27) {
                            throw new RuntimeException(var27);
                        } finally {
                            if (is != null) {
                                is.close();
                            }

                        }
                    }
                }

                zaos.finish();
            } catch (Exception var29) {
                throw new RuntimeException(var29);
            } finally {
                try {
                    if (zaos != null) {
                        zaos.close();
                    }
                } catch (IOException var26) {
                    throw new RuntimeException(var26);
                }

            }
        }

    }

    public static void decompressZip(String zipFilePath, String saveFileDir) {
        try {
            a.b(zipFilePath, saveFileDir);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public static boolean isEndsWithZip(String fileName) {
        boolean flag = false;
        if (fileName != null && !"".equals(fileName.trim()) && (fileName.endsWith(".ZIP") || fileName.endsWith(".zip"))) {
            flag = true;
        }

        return flag;
    }
}
