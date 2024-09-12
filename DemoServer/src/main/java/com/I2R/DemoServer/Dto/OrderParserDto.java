package com.I2R.DemoServer.Dto;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderParserDto {

    public Map<String, String> parseXml(String xmlFilePath) throws Exception {
        Map<String, String> dataMap = new HashMap<>();

        // Set up document builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(xmlFilePath));

        // Start recursive node processing from the root element
        Element root = doc.getDocumentElement();
        processNodeRecursively(root, dataMap, "");

        return dataMap;
    }

    // Recursive method to process nodes and attributes
    private void processNodeRecursively(Node node, Map<String, String> dataMap, String parentPath) {
        if (node instanceof Element) {
            String nodeName = node.getNodeName();
            String nodePath = parentPath.isEmpty() ? nodeName : parentPath + "." + nodeName;

            // Append attributes to the node path if present
            NamedNodeMap attributes = node.getAttributes();
            if (attributes != null && attributes.getLength() > 0) {
                for (int i = 0; i < attributes.getLength(); i++) {
                    Node attr = attributes.item(i);
                    nodePath += "[" + attr.getNodeName() + "=" + attr.getNodeValue() + "]";
                }
            }

            // Check if this element has child elements
            NodeList childNodes = node.getChildNodes();
            boolean hasChildElements = false;

            for (int i = 0; i < childNodes.getLength(); i++) {
                Node childNode = childNodes.item(i);
                if (childNode instanceof Element) {
                    hasChildElements = true;
                    processNodeRecursively(childNode, dataMap, nodePath); // Recurse for each child node
                }
            }

            // If there are no child elements, store the value of this node
            if (!hasChildElements) {
                String nodeValue = node.getTextContent().trim();
                dataMap.put(nodePath, nodeValue);
            }
        }
    }
}
