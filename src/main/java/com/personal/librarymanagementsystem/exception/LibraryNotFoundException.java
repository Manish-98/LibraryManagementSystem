package com.personal.librarymanagementsystem.exception;

public class LibraryNotFoundException extends Exception {
    public LibraryNotFoundException() {
        super("Library not found");
    }
}
