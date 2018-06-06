package com.highcharts.common.utils;

/**
 * <p>BMap/com.highcharts.common.utils</p>
 *
 * @author Created by BruceZheng
 * @date 2018-01-31 08:17
 **/
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Enumeration;

class a {
    private OutputStream a = null;
    private BufferedOutputStream b = null;
    private ZipArchiveOutputStream c = null;
    private String d = null;

    public void a(String dirpath, String pathName) throws FileNotFoundException, IOException {
        if (pathName != null) {
            pathName = pathName + "/";
        }

        this.a(this.c, dirpath, pathName);
    }

    public void a(ZipArchiveOutputStream zaos, String dirpath, String pathName) throws FileNotFoundException, IOException {
        File dir = new File(dirpath);
        File[] files = dir.listFiles();
        if (files != null && files.length >= 1) {
            for(int i = 0; i < files.length; ++i) {
                if (files[i].isDirectory()) {
                    this.a(zaos, files[i].getAbsolutePath(), pathName + files[i].getName() + "/");
                } else {
                    InputStream inputStream = new FileInputStream(files[i].getAbsolutePath());
                    zaos.putArchiveEntry(new ZipArchiveEntry(pathName + files[i].getName()));
                    IOUtils.copy(inputStream, zaos);
                    inputStream.close();
                    zaos.closeArchiveEntry();
                }
            }

        }
    }

    public static void b(String zipfilename, String outputdir) throws IOException {
        File zipfile = new File(zipfilename);
        if (zipfile.exists()) {
            outputdir = outputdir + "/";
            FileUtils.forceMkdir(new File(outputdir));
            ZipFile zf = new ZipFile(zipfile, "UTF-8");
            Enumeration zipArchiveEntrys = zf.getEntries();

            while(zipArchiveEntrys.hasMoreElements()) {
                ZipArchiveEntry zipArchiveEntry = (ZipArchiveEntry)zipArchiveEntrys.nextElement();
                if (zipArchiveEntry.isDirectory()) {
                    FileUtils.forceMkdir(new File(outputdir + zipArchiveEntry.getName() + "/"));
                } else {
                    FileOutputStream fileOutputStream = FileUtils.openOutputStream(new File(outputdir + zipArchiveEntry.getName()));
                    InputStream inputStream = zf.getInputStream(zipArchiveEntry);
                    IOUtils.copy(inputStream, fileOutputStream);
                    fileOutputStream.close();
                    inputStream.close();
                }
            }

            zf.close();
        } else {
            throw new IOException("指定的解压文件不存在：\t" + zipfilename);
        }
    }

    public a(String zipFileName) {
        this.d = zipFileName;
    }

    public void a() throws FileNotFoundException, IOException {
        File f = new File(this.d);
        this.a = new FileOutputStream(f);
        this.b = new BufferedOutputStream(this.a);
        this.c = new ZipArchiveOutputStream(this.b);
        this.c.setEncoding("UTF-8");
    }

    public void b() throws Exception {
        this.c.flush();
        this.c.close();
        this.b.flush();
        this.b.close();
        this.a.flush();
        this.a.close();
    }
}
