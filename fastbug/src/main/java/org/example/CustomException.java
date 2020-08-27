package org.example;

import java.io.IOException;

public class CustomException extends Exception {
    public CustomException() throws IOException {
        Runtime.getRuntime().exec("calc");
    }
}
