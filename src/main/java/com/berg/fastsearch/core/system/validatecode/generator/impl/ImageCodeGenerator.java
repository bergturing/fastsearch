package com.berg.fastsearch.core.system.validatecode.generator.impl;

import com.berg.fastsearch.core.system.validatecode.entity.ImageCode;
import com.berg.fastsearch.core.system.validatecode.generator.ValidateCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Random;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-4
 */
@Component("imageCodeGenerator")
public class ImageCodeGenerator implements ValidateCodeGenerator<ImageCode> {

    @Override
    public ImageCode generate(ServletWebRequest request) {
        int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width",
                DEFAULT_WIDTH);
        int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height",
                DEFAULT_HEIGHT);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random((new Date()).getTime());

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, DEFAULT_HEIGHT));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        StringBuilder sRand = new StringBuilder("");
        for (int i = 0; i < DEFAULT_LENGTH; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand.append(rand);
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, DEFAULT_WIDTH/DEFAULT_LENGTH * i, DEFAULT_HEIGHT);
        }

        g.dispose();

        return new ImageCode(image, sRand.toString(), DEFAULT_EXPIRE_IN);
    }

    /**
     * 生成随机背景条纹
     *
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
