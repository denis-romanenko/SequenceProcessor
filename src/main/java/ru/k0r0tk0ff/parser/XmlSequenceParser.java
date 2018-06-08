package ru.k0r0tk0ff.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.k0r0tk0ff.Main;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Parse file to data
 */
public class XmlSequenceParser implements SequenceParser {

    private static final Logger LOG = LoggerFactory.getLogger(XmlSequenceParser.class);

    public Collection<Integer> doParse(String sequenceXmlFilePath) throws Exception{
        Collection<Integer> parsedSequence = new ArrayList<>();

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser;
        parser = factory.createXMLStreamReader(
                    new BufferedInputStream(new FileInputStream(sequenceXmlFilePath)));

        while (parser.hasNext()) {
                int event = parser.next();
                if (event == XMLStreamConstants.START_ELEMENT) {
                    if (parser.getLocalName().equals("entry")) {
                        String intValueInAttribute = parser.getAttributeValue(null, "field");
                        if (intValueInAttribute != null) {
                            parsedSequence.add(Integer.parseInt(intValueInAttribute));
                        }
                    }

                }
            }

        LOG.debug(" Parse success");
        return parsedSequence;
    }
}
