package edu.escuelaing.arsw.ASE.app;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the isValidURL method in URLInfoPrinter.
 */
public class URLInfoPrinterTest {

    /**
     * Test cases for valid URLs.
     * Verifies that isValidURL correctly recognizes valid URLs.
     */
    @Test
    public void testValidURL() {
        assertTrue(URLInfoPrinter.isValidURL("https://www.example.com"));
        assertTrue(URLInfoPrinter.isValidURL("http://localhost:8080/test"));
        assertTrue(URLInfoPrinter.isValidURL("ftp://ftp.example.com/file.txt"));
        assertTrue(URLInfoPrinter.isValidURL("http://example.com/path/to/resource?query=1"));
    }

    /**
     * Test cases for invalid URLs.
     * Verifies that isValidURL correctly rejects invalid URLs.
     */
    @Test
    public void testInvalidURL() {
        assertFalse(URLInfoPrinter.isValidURL("invalid-url"));
        assertFalse(URLInfoPrinter.isValidURL(""));
        assertFalse(URLInfoPrinter.isValidURL(null));
        assertFalse(URLInfoPrinter.isValidURL("htp://example.com"));
        assertFalse(URLInfoPrinter.isValidURL("http://example.com:port"));
    }
}
