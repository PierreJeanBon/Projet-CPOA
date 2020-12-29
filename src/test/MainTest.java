package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import application.Produit;



public class MainTest {

    @Test
    public void testcorrigerReel() {
        assertEquals("1.1", Produit.corrigerReel("a1.1"));
        assertEquals("1.1", Produit.corrigerReel("1,1"));
        assertEquals(".1", Produit.corrigerReel(",1"));
        assertEquals(".1", Produit.corrigerReel(".1"));
        assertEquals("1", Produit.corrigerReel("a1"));
        assertEquals("1.123", Produit.corrigerReel("a1b.a1b2c3"));
        assertEquals("1.2345", Produit.corrigerReel("a1b.c2d3e4f5g"));
        assertEquals("1.2345", Produit.corrigerReel("a1b,c2d3e4f5g"));
        assertEquals("1.2345", Produit.corrigerReel("a1b,c????...,,,2d3.e4.f..5....g.....,,,???.."));


    }

}
