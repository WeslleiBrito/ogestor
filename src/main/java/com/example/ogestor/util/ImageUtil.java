package com.example.ogestor.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.util.Base64;

public class ImageUtil {

    private static final Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    public static Image converterBase64ParaImage(String base64) {
        try {
            byte[] dados = Base64.getDecoder().decode(base64);
            ByteArrayInputStream bis = new ByteArrayInputStream(dados);
            return ImageIO.read(bis);
        } catch (Exception e) {
            logger.error("Erro ao converter imagem base64", e); // aqui é o log
            return null;
        }
    }
}
