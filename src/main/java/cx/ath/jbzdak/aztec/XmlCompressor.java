package cx.ath.jbzdak.aztec;

import com.sun.xml.internal.fastinfoset.stax.factory.StAXOutputFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;
import java.io.OutputStream;

import cx.ath.jbzdak.aztec.compressedPoints.CompressedPoints;
import cx.ath.jbzdak.aztec.compressedPoints.PlateauCompressedPoints;
import cx.ath.jbzdak.aztec.compressedPoints.SlopeCompressed;

/**
 * Created by IntelliJ IDEA.
 * User: yaster
 * Date: Mar 16, 2010
 * Time: 6:04:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class XmlCompressor implements Compressor{

    private static final JAXBContext context;
    static {
        try {
            context = JAXBContext.newInstance(SlopeCompressed.class, PlateauCompressedPoints.class);
        } catch (JAXBException e) {
            throw  new RuntimeException(e);
        }
    }

    private OutputStream outputStream;

    private XMLStreamWriter xmlStreamWriter;

    private Marshaller marshaller;

    public void addPoints(CompressedPoints points) throws IOException{
        try {
            marshaller.marshal(points, xmlStreamWriter);
        } catch (JAXBException e) {
            throw new IOException(e);
        }
    }

    public void start(OutputStream outputStream) throws IOException{
        this.outputStream = outputStream;
        try {
            xmlStreamWriter = StAXOutputFactory.newInstance().createXMLStreamWriter(outputStream);
            xmlStreamWriter.writeStartDocument();
            xmlStreamWriter.writeStartElement("compressed");
            xmlStreamWriter.writeCharacters(" ");
        } catch (XMLStreamException e) {
            throw new IOException(e); 
        }

        try {
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
           
        } catch (JAXBException e) {
            throw new IOException(e);
        }
    }

    public void finish() throws IOException {
        try {
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndDocument();
            xmlStreamWriter.flush();
        } catch (XMLStreamException e) {
            throw  new IOException(e);
        }

    }
}
