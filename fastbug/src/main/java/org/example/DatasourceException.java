package org.example;

import javax.activation.DataSource;
import javax.activation.URLDataSource;

import java.net.URL;

public class DatasourceException extends Exception {

    public DatasourceException() {
    }

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(URL url) {
        this.dataSource = new URLDataSource(url);
    }
}