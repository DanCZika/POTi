package com.example.dani.poti.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Dani on 2017. 03. 22..
 */

public class StringDownloader extends Downloader {

    public interface StringDownloaderListener {
        void stringLoaded(String string);
    }

    StringDownloaderListener stringDownloaderListener;
    ByteArrayOutputStream output=null;

    public StringDownloader(String url, DownloaderListener downloaderListener, StringDownloaderListener stringDownloaderListener) {
        super(url, downloaderListener);
        this.stringDownloaderListener = stringDownloaderListener;
        this.output = new ByteArrayOutputStream();
    }

    @Override
    protected void finalize() throws IOException {
        byte[] downloaded=output.toByteArray();
        String string=new String(downloaded);
        stringDownloaderListener.stringLoaded(string);
    }

    @Override
    protected boolean processData(byte[] data, int count) {
        try {
            output.write(data, 0, count);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }


}
