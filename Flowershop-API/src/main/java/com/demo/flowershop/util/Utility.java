package com.demo.flowershop.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class Utility {

    private Utility() {
    }

    private static Logger logger = Logger.getLogger("Utility.java");

    public static byte[] compressBytes(final byte[] data) {
        final Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        final byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            final int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (final IOException e) {
            logger.warning("Exception in outputStream: "+ e);
        }
        logger.info("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
            logger.warning("IOException in outputStream: "+ ioe);
        } catch (DataFormatException e) {
            logger.warning("DataFormatException in outputStream: "+ e);
        }
        return outputStream.toByteArray();
    }
}