package com.ppx.qrcode;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Test {
	public static void main(String[] args) {
		
        // 存在白边 001  
        String qr = createQr("P77756860316684", false);
        // 去除白边
        // String qr1 = createQr("https://workorder.hr-soft.cn/workorder/demand/project_demand_detail.html?demandId=hzcctech_610", true);

        System.err.println(qr);
        // System.err.println(qr1);
	}
	
	public static String createQr(String link, Boolean isRemoveSide) {
        if (link == null || link.length() <= 0) {
            System.err.println("链接地址不能为空");
            return "";
        }

        try {
            Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
            // 指定编码格式
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            // 指定纠错级别(L--7%,M--15%,Q--25%,H--30%)
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            // 编码内容,编码类型(这里指定为二维码),生成图片宽度,生成图片高度,设置参数
            BitMatrix bitMatrix = new MultiFormatWriter().encode(link, BarcodeFormat.QR_CODE, 350, 350, hints);

            //去除白边
            if (isRemoveSide) {
                bitMatrix = deleteWhite(bitMatrix);
            }

            //以我的电脑为例
            String path = "D://tmp//qr" + System.currentTimeMillis() + ".png";

            //设置请求头
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            MatrixToImageWriter.writeToStream(bitMatrix, "png", fileOutputStream);
            fileOutputStream.close();

            return path;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    // 去除二维码白边
    private static BitMatrix deleteWhite(BitMatrix matrix) {
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHeight = rec[3] + 1;

        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (matrix.get(i + rec[0], j + rec[1])) resMatrix.set(i, j);
            }
        }
        return resMatrix;
    }
}
