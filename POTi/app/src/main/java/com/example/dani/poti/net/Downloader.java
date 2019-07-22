package com.example.dani.poti.net;

import android.accounts.NetworkErrorException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Dani on 2017. 03. 22..
 */

public abstract class Downloader extends Thread {

    public interface DownloaderListener
    {
        void error(String message);
        void progressChanged(int prog);
        void finished();
    }

    protected String url;
    protected DownloaderListener downloaderListener;

    public Downloader(String url, DownloaderListener downloaderListener) {
        this.downloaderListener = downloaderListener;
        this.url=url;
    }

    protected abstract boolean processData(byte[] data, int count);
    protected abstract void finalize() throws IOException;

    @Override
    public void run() {
        InputStream input = null;
        HttpURLConnection connection = null;
        try {
            URL u=new URL(url);
            connection = (HttpURLConnection) u.openConnection();
            connection.connect();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new NetworkErrorException("Server has problems: "+url);
            }
            int fileLength = connection.getContentLength();
            input = connection.getInputStream();
            byte data[] = new byte[4096];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                if (this.isInterrupted()) {
                    input.close();
                    throw new IllegalThreadStateException("Thread closed outside");
                }
                boolean success=processData(data, count);
                if(!success){
                    throw new IOException("Unable to parse data");
                }
                total += count;
                if(downloaderListener !=null) {
                    if (fileLength > 0) {
                        downloaderListener.progressChanged((int) (total * 100 / fileLength));
                    } else {
                        downloaderListener.progressChanged(fileLength);
                    }
                }
            }
        } catch (Exception e) {
            downloaderListener.error(e.getMessage());
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
                finalize();
                if(downloaderListener!=null) {
                    downloaderListener.finished();
                }
            } catch (IOException ignored) {}
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
