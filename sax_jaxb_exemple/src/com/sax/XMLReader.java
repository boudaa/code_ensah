package com.sax;

import java.io.IOException;

import javax.xml.parsers.*;
import javax.xml.transform.*;

import org.w3c.dom.*;
import org.xml.sax.*;

public class XMLReader {

	public static void main(String[] args) {

		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();

			parser.parse("deplacement.xml", new XMLHandler());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
